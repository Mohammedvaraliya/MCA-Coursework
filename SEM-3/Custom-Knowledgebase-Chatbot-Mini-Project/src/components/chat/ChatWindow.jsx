import { useMemo, useRef, useState } from "react";
import MessageBubble from "./MessageBubble.jsx";
import { Loader2, Send } from "lucide-react";

const ROLES = ["student", "faculty", "staff", "guest"];

export default function ChatWindow() {
  const [role, setRole] = useState("student");
  const [input, setInput] = useState("");
  const [loading, setLoading] = useState(false);
  const [messages, setMessages] = useState([
    {
      id: "welcome",
      role: "assistant",
      content:
        "Hello! I am your Institutional AI Assistant. Ask me about admissions, library hours, parking, student services, and more.",
    },
  ]);

  const listRef = useRef(null);

  const canSend = useMemo(
    () => input.trim().length > 0 && !loading,
    [input, loading]
  );

  async function onSend(e) {
    e.preventDefault();
    if (!canSend) return;

    const userMsg = {
      id: crypto.randomUUID(),
      role: "user",
      content: input.trim(),
    };
    const pending = [
      ...messages,
      userMsg,
      { id: `thinking-${Date.now()}`, role: "assistant", content: "Thinking…" },
    ];
    setMessages(pending);
    setInput("");
    setLoading(true);

    try {
      const res = await fetch("/api/chat", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ messages: [...messages, userMsg], role }),
      });
      if (!res.ok) {
        throw new Error("Request failed");
      }
      const data = await res.json();
      const answerText = data.answer || "I could not generate a response.";
      const sources = Array.isArray(data.sources) ? data.sources : [];
      const sourceText =
        sources.length > 0
          ? "\n\nSources:\n" +
            sources.map((s, i) => `${i + 1}. ${s.title}`).join("\n")
          : "";
      setMessages((prev) => [
        ...prev.slice(0, prev.length - 1), // remove "Thinking…"
        {
          id: crypto.randomUUID(),
          role: "assistant",
          content: answerText + sourceText,
        },
      ]);
      // Scroll to bottom
      queueMicrotask(() => {
        listRef.current?.lastElementChild?.scrollIntoView({
          behavior: "smooth",
          block: "end",
        });
      });
    } catch (err) {
      setMessages((prev) => [
        ...prev.slice(0, prev.length - 1),
        {
          id: crypto.randomUUID(),
          role: "assistant",
          content: "Sorry, something went wrong.",
        },
      ]);
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="mx-auto w-full max-w-3xl">
      {/* Card */}
      <div className="rounded-3xl border border-slate-200 dark:border-slate-800 bg-white dark:bg-slate-950 shadow-sm overflow-hidden">
        {/* Top bar */}
        <div className="flex items-center justify-between px-4 py-3 border-b border-slate-200 dark:border-slate-800">
          <div>
            <h2 className="text-base font-semibold">Chat</h2>
            <p className="text-xs text-slate-600 dark:text-slate-400">
              Ask anything about the institution
            </p>
          </div>
          <div className="flex items-center gap-2">
            <label htmlFor="role" className="sr-only">
              Audience
            </label>
            <select
              id="role"
              value={role}
              onChange={(e) => setRole(e.target.value)}
              className="rounded-lg border border-slate-200 dark:border-slate-800 bg-white dark:bg-slate-900 text-sm px-3 py-2"
            >
              {ROLES.map((r) => (
                <option key={r} value={r}>
                  {r.charAt(0).toUpperCase() + r.slice(1)}
                </option>
              ))}
            </select>
          </div>
        </div>

        {/* Messages */}
        <div
          className="h-[60vh] overflow-y-auto px-4 py-4 space-y-4"
          ref={listRef}
        >
          {messages.map((m) => (
            <MessageBubble key={m.id} role={m.role} content={m.content} />
          ))}
          {loading && (
            <div className="flex justify-start">
              <div className="inline-flex items-center gap-2 text-sm text-slate-500 dark:text-slate-400">
                <Loader2 className="animate-spin" size={16} />
                Generating…
              </div>
            </div>
          )}
        </div>

        {/* Input */}
        <form
          onSubmit={onSend}
          className="border-t border-slate-200 dark:border-slate-800 p-3"
        >
          <div className="flex items-end gap-2">
            <textarea
              value={input}
              onChange={(e) => setInput(e.target.value)}
              placeholder="Type your question…"
              rows={1}
              className="flex-1 resize-none rounded-xl border border-slate-200 dark:border-slate-800 bg-white dark:bg-slate-900 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-600/30"
            />
            <button
              type="submit"
              disabled={!canSend}
              className="inline-flex items-center gap-2 rounded-xl bg-blue-600 text-white px-4 py-2 text-sm font-medium shadow-sm hover:bg-blue-700 disabled:opacity-50 disabled:cursor-not-allowed"
            >
              <Send size={16} />
              Send
            </button>
          </div>
          <p className="mt-2 text-[11px] text-slate-500 dark:text-slate-400">
            This chat does not save history. Each visit starts fresh.
          </p>
        </form>
      </div>
    </div>
  );
}
