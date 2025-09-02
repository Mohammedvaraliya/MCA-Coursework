import type { NextRequest } from "next/server"
import { streamText } from "ai"
import { google } from "@ai-sdk/google"
import { retrieveRelevant } from "@/lib/embedding"
import type { Audience } from "@/lib/types"

export async function POST(req: NextRequest) {
  if (!process.env.GOOGLE_GENERATIVE_AI_API_KEY) {
    return new Response(
      "Server misconfiguration: GOOGLE_GENERATIVE_AI_API_KEY is missing. Add it in Project Settings.",
      { status: 500 },
    )
  }

  const { messages, audience = "student" } = await req.json()
  const latest = Array.isArray(messages) && messages.length ? messages[messages.length - 1] : null
  const userPrompt: string = latest?.content ?? ""

  const relevant = await retrieveRelevant(userPrompt, audience as Audience, 5)

  const system = [
    "You are an institutional assistant that answers clearly, concisely, and accurately.",
    "Only answer using the provided knowledge base context. If unknown, say you don't know and suggest who to contact.",
    "Cite relevant policy names or sections when useful, but keep responses brief and actionable.",
    "Be friendly and professional. Use short paragraphs and bullets when helpful.",
    `Audience: ${String(audience)}.`,
  ].join("\n")

  const contextBlock =
    relevant.length > 0
      ? relevant.map((r, i) => `[#${i + 1}] ${r.title}\n${r.content}`).join("\n\n")
      : "No context found."

  const model = google("gemini-1.5-flash")

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
  })

  return result.toAIStreamResponse()
}
