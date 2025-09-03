import { useEffect, useState } from "react";
import ChatWindow from "@/components/chat/ChatWindow.jsx";
import ThemeToggle from "@/components/chat/ThemeToggle.jsx";
import { Bot } from "lucide-react";

export default function App() {
  const [theme, setTheme] = useState("light");

  useEffect(() => {
    const root = document.documentElement;
    if (theme === "dark") root.classList.add("dark");
    else root.classList.remove("dark");
  }, [theme]);

  return (
    <div className="min-h-dvh bg-white text-slate-900 dark:bg-slate-950 dark:text-slate-50">
      <header className="border-b border-slate-200 dark:border-slate-800 bg-white/80 dark:bg-slate-950/80 backdrop-blur">
        <div className="mx-auto max-w-5xl px-4 py-3 flex items-center justify-between">
          <div className="flex items-center gap-3">
            <div className="h-9 w-9 rounded-xl bg-blue-600 text-white flex items-center justify-center shadow-sm">
              <Bot size={18} />
            </div>
            <div>
              <h1 className="text-lg font-semibold leading-none text-balance">
                Institutional AI Assistant
              </h1>
              <p className="text-xs text-slate-600 dark:text-slate-400">
                Custom knowledge base • No chat history
              </p>
            </div>
          </div>
          <ThemeToggle theme={theme} setTheme={setTheme} />
        </div>
      </header>

      <main className="mx-auto max-w-5xl px-4 py-6">
        <ChatWindow />
      </main>

      <footer className="mx-auto max-w-5xl px-4 py-6 text-center text-xs text-slate-500 dark:text-slate-400">
        Answers are generated from the institution’s knowledge base. If
        information is missing, contact support.
      </footer>
    </div>
  );
}
