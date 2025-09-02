import { embed } from "ai"
import { google } from "@ai-sdk/google"
import { getKB } from "./kb"
import type { Audience, KBEmbedding, KBItem } from "./types"

const EMBEDDING_MODEL = "text-embedding-004"

let cache: {
  items: KBItem[]
  embeddings: KBEmbedding[]
} | null = null

function cosineSimilarity(a: number[], b: number[]): number {
  let dot = 0
  let na = 0
  let nb = 0
  for (let i = 0; i < a.length; i++) {
    dot += a[i] * b[i]
    na += a[i] * a[i]
    nb += b[i] * b[i]
  }
  return dot / (Math.sqrt(na) * Math.sqrt(nb) + 1e-8)
}

export async function getOrBuildEmbeddings() {
  if (cache) return cache

  const items = getKB()
  const model = google(EMBEDDING_MODEL)

  const embeddings: KBEmbedding[] = []
  for (const it of items) {
    const { embedding } = await embed({
      model,
      value: `${it.title}\n\n${it.content}`,
    })
    embeddings.push({ id: it.id, embedding })
  }

  cache = { items, embeddings }
  return cache
}

export async function retrieveRelevant(query: string, audience: Audience, k = 4) {
  const { items, embeddings } = await getOrBuildEmbeddings()
  const model = google(EMBEDDING_MODEL)
  const { embedding: q } = await embed({ model, value: query })

  const scored = embeddings
    .map((e) => {
      const item = items.find((it) => it.id === e.id)!
      const audienceMatch =
        item.audience === "all" || (Array.isArray(item.audience) && item.audience.includes(audience))
      const bonus = audienceMatch ? 0.05 : 0
      return { item, score: cosineSimilarity(q, e.embedding) + bonus }
    })
    .sort((a, b) => b.score - a.score)
    .slice(0, k)
    .map((x) => x.item)

  return scored
}
