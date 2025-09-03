import { useMemo, useRef, useState } from "react";
import MessageBubble from "./MessageBubble.jsx";
import { Loader2, Send, UserCircle } from "lucide-react";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";

const ROLES = ["student", "faculty", "staff", "guest"];
const SUGGESTED_QUESTIONS = [
  "Scholarships & Financial Aid Overview",
  "Academic Calendar Highlights",
  "Faculty Research Support",
  "Campus Tours & Visitor Info",
];

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

  async function onSend(e, preset = null) {
    e?.preventDefault();
    if (!canSend && !preset) return;

    const content = preset || input.trim();
    const userMsg = { id: crypto.randomUUID(), role: "user", content };

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
      if (!res.ok) throw new Error("Request failed");

      const data = await res.json();
      const answerText = data.answer || "I could not generate a response.";
      const sources = Array.isArray(data.sources) ? data.sources : [];
      const sourceText =
        sources.length > 0
          ? "\n\nSources:\n" +
            sources.map((s, i) => `${i + 1}. ${s.title}`).join("\n")
          : "";

      setMessages((prev) => [
        ...prev.slice(0, prev.length - 1),
        {
          id: crypto.randomUUID(),
          role: "assistant",
          content: answerText + sourceText,
        },
      ]);

      queueMicrotask(() => {
        listRef.current?.lastElementChild?.scrollIntoView({
          behavior: "smooth",
          block: "end",
        });
      });
    } catch {
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

  const isFirstLoad = messages.length === 1;

  return (
    <div className="mx-auto w-full max-w-5xl h-[calc(90vh-80px)] flex flex-col">
      {/* Card */}
      <div className="flex-1 flex flex-col rounded-3xl border border-slate-200 dark:border-slate-800 bg-white dark:bg-slate-950 shadow-lg overflow-hidden">
        {/* Top bar */}
        <div className="flex items-center justify-between px-6 py-4 border-b border-slate-200 dark:border-slate-800 bg-slate-50 dark:bg-slate-900">
          <div>
            <h2 className="text-lg font-semibold">Chat Assistant</h2>
            <p className="text-sm text-slate-600 dark:text-slate-400">
              Ask anything about the institution
            </p>
          </div>

          {/* Dropdown for Roles */}
          <DropdownMenu>
            <DropdownMenuTrigger asChild>
              <button className="inline-flex items-center gap-2 rounded-xl border border-slate-200 dark:border-slate-800 bg-white dark:bg-slate-950 px-4 py-2 text-sm font-medium hover:bg-slate-100 dark:hover:bg-slate-800 transition shadow-sm">
                <UserCircle className="w-4 h-4" />
                {role.charAt(0).toUpperCase() + role.slice(1)}
              </button>
            </DropdownMenuTrigger>
            <DropdownMenuContent className="w-40">
              <DropdownMenuLabel>Choose Role</DropdownMenuLabel>
              {ROLES.map((r) => (
                <DropdownMenuItem key={r} onClick={() => setRole(r)}>
                  {r.charAt(0).toUpperCase() + r.slice(1)}
                </DropdownMenuItem>
              ))}
            </DropdownMenuContent>
          </DropdownMenu>
        </div>

        {/* Messages / Suggested Questions */}
        <div
          className="flex-1 overflow-y-auto px-6 py-6 space-y-4"
          ref={listRef}
        >
          {isFirstLoad ? (
            <div className="h-full flex flex-col items-center justify-center gap-6 text-center">
              <p className="text-lg text-slate-700 dark:text-slate-300 font-medium">
                Try asking one of these:
              </p>
              <div className="grid grid-cols-1 sm:grid-cols-2 gap-4 w-full max-w-2xl">
                {SUGGESTED_QUESTIONS.map((q) => (
                  <button
                    key={q}
                    onClick={(e) => onSend(e, q)}
                    className="rounded-2xl border border-slate-200 dark:border-slate-800 bg-slate-50 dark:bg-slate-900 px-6 py-4 text-base text-slate-700 dark:text-slate-300 hover:bg-blue-50 dark:hover:bg-slate-800 transition shadow-md text-left"
                  >
                    {q}
                  </button>
                ))}
              </div>
            </div>
          ) : (
            <>
              {messages.map((m) => (
                <MessageBubble key={m.id} role={m.role} content={m.content} />
              ))}
              {loading && (
                <div className="flex justify-start">
                  <div className="inline-flex items-center gap-2 text-base text-slate-500 dark:text-slate-400">
                    <Loader2 className="animate-spin" size={18} />
                    Generating…
                  </div>
                </div>
              )}
            </>
          )}
        </div>

        {/* Input */}
        <form
          onSubmit={onSend}
          className="border-t border-slate-200 dark:border-slate-800 p-4 bg-slate-50 dark:bg-slate-900"
        >
          <div className="flex items-end gap-3">
            <textarea
              value={input}
              onChange={(e) => setInput(e.target.value)}
              placeholder="Type your question…"
              rows={1}
              className="flex-1 resize-none rounded-xl border border-slate-200 dark:border-slate-800 bg-white dark:bg-slate-950 px-4 py-3 text-base focus:outline-none focus:ring-2 focus:ring-blue-600/30 shadow-sm"
            />
            <button
              type="submit"
              disabled={!canSend}
              className="inline-flex items-center gap-2 rounded-xl bg-blue-600 text-white px-6 py-3 text-sm font-medium shadow-md hover:bg-blue-700 disabled:opacity-50 disabled:cursor-not-allowed"
            >
              <Send size={18} />
              Send
            </button>
          </div>
          <p className="mt-2 text-xs text-slate-500 dark:text-slate-400">
            This chat does not save history. Each visit starts fresh.
          </p>
        </form>
      </div>
    </div>
  );
}
