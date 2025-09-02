"use client"

import * as React from "react"
import { Button } from "@/components/ui/button"
import { Textarea } from "@/components/ui/textarea"
import { Card } from "@/components/ui/card"
import { MessageBubble } from "./message-bubble"
import { Loader2, SendHorizontal } from "lucide-react"
import { cn } from "@/lib/utils"

type Audience = "student" | "faculty" | "staff" | "visitor"
type Msg = { id: string; role: "user" | "assistant"; content: string }

export function ChatWindow({ audience }: { audience: Audience }) {
  const [messages, setMessages] = React.useState<Msg[]>([])
  const [input, setInput] = React.useState("")
  const [isLoading, setIsLoading] = React.useState(false)

  const starters = React.useMemo(
    () => [
      "When are the admissions deadlines?",
      "How do I apply for scholarships?",
      "Where can I find the academic calendar?",
      "What are the faculty research areas?",
      "How to book a campus tour?",
    ],
    [],
  )

  async function sendMessage(text: string) {
    if (!text.trim()) return
    const userMsg: Msg = { id: crypto.randomUUID(), role: "user", content: text.trim() }
    const assistantId = crypto.randomUUID()
    setMessages((prev) => [...prev, userMsg, { id: assistantId, role: "assistant", content: "" }])
    setInput("")
    setIsLoading(true)

    try {
      const minimal = [...messages, userMsg].map((m) => ({ role: m.role, content: m.content }))
      const res = await fetch("/api/chat", {
        method: "POST",
        headers: { "Content-Type": "application/json", Accept: "text/plain" },
        body: JSON.stringify({ messages: minimal, audience }),
      })

      if (!res.ok) {
        const errText = await res.text().catch(() => "Unexpected error")
        throw new Error(errText || `Request failed: ${res.status}`)
      }

      // Stream tokens (plain text) and append to the last assistant message
      if (res.body) {
        const reader = res.body.getReader()
        const decoder = new TextDecoder()
        let done = false
        while (!done) {
          const chunk = await reader.read()
          done = !!chunk.done
          const textChunk = decoder.decode(chunk.value || new Uint8Array(), { stream: !done })
          if (textChunk) {
            setMessages((prev) =>
              prev.map((m) => (m.id === assistantId ? { ...m, content: m.content + textChunk } : m)),
            )
          }
        }
      } else {
        // Fallback: non-streaming
        const full = await res.text()
        setMessages((prev) => prev.map((m) => (m.id === assistantId ? { ...m, content: full } : m)))
      }
    } catch (e: any) {
      const msg = e?.message || "Something went wrong"
      setMessages((prev) =>
        prev.map((m) =>
          m.id === assistantId ? { ...m, content: `Sorry, I couldn't complete that request.\n\nDetails: ${msg}` } : m,
        ),
      )
    } finally {
      setIsLoading(false)
    }
  }

  function onSubmit(e: React.FormEvent) {
    e.preventDefault()
    void sendMessage(input)
  }

  function onAsk(q: string) {
    void sendMessage(q)
  }

  return (
    <Card className="relative flex h-[70vh] flex-col overflow-hidden">
      <div className="flex items-center justify-between border-b px-4 py-3">
        <div>
          <h3 className="text-sm font-semibold">Chat</h3>
          <p className="text-xs text-muted-foreground">Ask questions about admissions, courses, policies, and more.</p>
        </div>
        {isLoading && (
          <div className="inline-flex items-center gap-2 rounded-md border px-2 py-1 text-xs">
            <Loader2 className="h-3.5 w-3.5 animate-spin" />
            Thinking
          </div>
        )}
      </div>

      <div className="flex-1 space-y-4 overflow-y-auto p-4">
        {messages.length === 0 && (
          <div className="mx-auto max-w-xl rounded-lg border bg-muted/30 p-4">
            <p className="mb-3 text-sm font-medium">Try asking one of these:</p>
            <div className="grid gap-2 md:grid-cols-2">
              {starters.map((s) => (
                <Button
                  key={s}
                  variant="outline"
                  className="justify-start text-left bg-transparent"
                  onClick={() => onAsk(s)}
                >
                  {s}
                </Button>
              ))}
            </div>
            <p className="mt-3 text-xs text-muted-foreground">
              Tip: The chat tailors answers for the selected audience. No chat history is stored across visits.
            </p>
          </div>
        )}

        {messages.map((m) => (
          <MessageBubble key={m.id} role={m.role} content={m.content} />
        ))}
      </div>

      <form
        onSubmit={onSubmit}
        className={cn("border-t bg-background/60 p-3", "supports-[backdrop-filter]:bg-background/40 backdrop-blur")}
      >
        <div className="flex items-end gap-2">
          <Textarea
            value={input}
            onChange={(e) => setInput(e.target.value)}
            placeholder="Ask anything about the institution..."
            className="min-h-[44px] resize-none"
            rows={2}
          />
          <Button type="submit" className="h-10 w-10 p-0" disabled={isLoading || input.trim().length === 0}>
            <SendHorizontal className="h-4 w-4" />
            <span className="sr-only">Send</span>
          </Button>
        </div>
      </form>
    </Card>
  )
}
