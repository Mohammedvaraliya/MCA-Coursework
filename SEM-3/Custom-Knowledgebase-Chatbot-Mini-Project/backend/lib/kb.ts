import kb from "../data/kb.json"
import type { KBItem } from "./types"

export function getKB(): KBItem[] {
  return (kb as KBItem[]).map((d, i) => ({
    id: d.id ?? String(i + 1),
    title: d.title,
    audience: d.audience,
    content: d.content,
    tags: d.tags ?? [],
  }))
}
