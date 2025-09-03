const express = require("express");
const cors = require("cors");
const dotenv = require("dotenv");
const fs = require("fs");
const path = require("path");
const { GoogleGenerativeAI } = require("@google/generative-ai");

dotenv.config();

const PORT = process.env.PORT || 8787;
const app = express();
app.use(cors({ origin: true }));
app.use(express.json({ limit: "1mb" }));

if (!process.env.GOOGLE_GENERATIVE_AI_API_KEY) {
  console.warn(
    "[server] GOOGLE_GENERATIVE_AI_API_KEY is missing. Set it in .env"
  );
}

const genAI = new GoogleGenerativeAI(process.env.GOOGLE_GENERATIVE_AI_API_KEY);

const kbPath = path.join(__dirname, "data", "kb.json");

let KB = [];
let KB_WITH_EMBEDS = [];
let EMBED_DIM = 0;

function cosineSim(a, b) {
  let dot = 0,
    na = 0,
    nb = 0;
  for (let i = 0; i < a.length; i++) {
    dot += a[i] * b[i];
    na += a[i] * a[i];
    nb += b[i] * b[i];
  }
  const denom = Math.sqrt(na) * Math.sqrt(nb) || 1;
  return dot / denom;
}

async function embedText(text) {
  const embedModel = genAI.getGenerativeModel({ model: "text-embedding-004" });
  const r = await embedModel.embedContent(text);
  const v = r.embedding.values;
  if (!EMBED_DIM) EMBED_DIM = v.length;
  return v;
}

function normalizeAudience(a) {
  if (a == null) return [];
  if (Array.isArray(a)) return a.map((x) => String(x).toLowerCase());
  const s = String(a).toLowerCase();
  return s === "all" ? ["all"] : [s];
}

async function loadKb() {
  try {
    const raw = fs.readFileSync(kbPath, "utf-8");
    KB = JSON.parse(raw);
    // Precompute embeddings (title + content + tags)
    KB_WITH_EMBEDS = [];
    for (const item of KB) {
      const audience = normalizeAudience(item.audience);
      const text = `${item.title}\n${item.tags?.join(", ") || ""}\n${
        item.content
      }`;
      const vec = await embedText(text);
      KB_WITH_EMBEDS.push({ ...item, audience, vector: vec });
    }
    console.log(
      `[server] KB loaded with ${KB.length} items (dim=${EMBED_DIM})`
    );
  } catch (e) {
    console.error("[server] Failed to load KB:", e.message);
  }
}

function retrieve(queryVec, audience = "guest", k = 5) {
  const aud = String(audience).toLowerCase();
  const pool = KB_WITH_EMBEDS.filter((it) => {
    if (!it.audience || it.audience.length === 0) return true;
    if (it.audience.includes("all")) return true;
    return it.audience.includes(aud);
  });
  const scored = pool.map((it) => ({
    item: it,
    score: cosineSim(queryVec, it.vector),
  }));
  scored.sort((a, b) => b.score - a.score);
  return scored.slice(0, k).map((s) => s.item);
}

function buildPrompt({ question, audience, contexts }) {
  const contextText = contexts
    .map((c, i) => `# Source ${i + 1}: ${c.title}\n${c.content}`)
    .join("\n\n");
  return `
You are an institutional assistant answering questions for an academic institution.
Audience: ${audience}.
Use ONLY the information from the "CONTEXT" below. If the answer is not present in the context, say:
"I don't have that information in my knowledge base."
Be concise, professional, and helpful. Include policy-sensitive disclaimers if relevant.

CONTEXT:
${contextText}

QUESTION:
${question}

RESPONSE:
`;
}

app.post("/api/chat", async (req, res) => {
  try {
    const { messages, role = "guest" } = req.body || {};
    if (!Array.isArray(messages) || messages.length === 0) {
      return res.status(400).json({ error: "messages array required" });
    }
    const lastUser = [...messages].reverse().find((m) => m.role === "user");
    if (!lastUser?.content) {
      return res.status(400).json({ error: "last user message required" });
    }

    const queryVec = await embedText(String(lastUser.content));
    const contexts = retrieve(queryVec, role, 5);

    const model = genAI.getGenerativeModel({ model: "gemini-1.5-flash" });
    const prompt = buildPrompt({
      question: lastUser.content,
      audience: role,
      contexts,
    });
    const result = await model.generateContent([{ text: prompt }]);
    const text =
      (await result.response.text()) || "I could not generate a response.";

    return res.json({
      answer: text,
      sources: contexts.map((c) => ({ id: c.id, title: c.title })),
    });
  } catch (e) {
    console.error("[server] /api/chat error:", e);
    return res.status(500).json({ error: "Server error generating answer." });
  }
});

app.get("/api/health", (_, res) => res.json({ ok: true }));

app.listen(PORT, async () => {
  console.log(`[server] listening on http://localhost:${PORT}`);
  await loadKb();
});
