import { streamText } from "ai";
import { google } from "@ai-sdk/google";
import { retrieveRelevant } from "../../lib/embedding.js";

async function handler(req, res) {
  if (req.method !== "POST") {
    return res.status(405).send("Method Not Allowed");
  }

  if (!process.env.GOOGLE_GENERATIVE_AI_API_KEY) {
    return res
      .status(500)
      .send(
        "Server misconfiguration: GOOGLE_GENERATIVE_AI_API_KEY is missing. Add it in Project Settings."
      );
  }

  try {
    const { messages, audience = "student" } = req.body;
    const latest =
      Array.isArray(messages) && messages.length
        ? messages[messages.length - 1]
        : null;
    const userPrompt = latest?.content || "";

    const relevant = await retrieveRelevant(userPrompt, audience, 5);

    const system = [
      "You are an institutional assistant that answers clearly, concisely, and accurately.",
      "Only answer using the provided knowledge base context. If unknown, say you don't know and suggest who to contact.",
      "Cite relevant policy names or sections when useful, but keep responses brief and actionable.",
      "Be friendly and professional. Use short paragraphs and bullets when helpful.",
      `Audience: ${audience}.`,
    ].join("\n");

    const contextBlock =
      relevant.length > 0
        ? relevant
            .map((r, i) => `[#${i + 1}] ${r.title}\n${r.content}`)
            .join("\n\n")
        : "No context found.";

    const model = google("gemini-1.5-flash");

    const result = await streamText({
      model,
      system,
      prompt: [
        "Use the following institutional knowledge base context to answer the user's question.",
        "If the question is unrelated or the answer is not present, politely say you don't have that information.",
        "",
        "=== Knowledge Base Context ===",
        contextBlock,
        "=== End Context ===",
        "",
        "User Question:",
        userPrompt,
      ].join("\n"),
    });

    const stream = result.toAIStreamResponse(); // Returns a ReadableStream

    res.writeHead(200, {
      "Content-Type": "text/event-stream",
      "Cache-Control": "no-cache",
      Connection: "keep-alive",
    });

    stream.pipe(res);
  } catch (err) {
    console.error("Error:", err);
    res.status(500).send("An error occurred.");
  }
}

module.exports = handler;
