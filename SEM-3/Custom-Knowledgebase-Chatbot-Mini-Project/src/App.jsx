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
    <div className="min-h-dvh flex flex-col bg-white text-slate-900 dark:bg-slate-950 dark:text-slate-50">
      {/* Header */}
      <header className="border-b border-slate-200 dark:border-slate-800 bg-white/80 dark:bg-slate-950/80 backdrop-blur">
        <div className="mx-auto max-w-6xl px-6 py-4 flex items-center justify-between">
          <div className="flex items-center gap-3">
            <div className="h-11 w-11 rounded-2xl bg-blue-600 text-white flex items-center justify-center shadow-md">
              <Bot size={22} />
            </div>
            <div>
              <h1 className="text-xl font-semibold leading-none">
                Institutional AI Assistant
              </h1>
              <p className="text-sm text-slate-600 dark:text-slate-400">
                Custom knowledge base • No chat history
              </p>
            </div>
          </div>
          <ThemeToggle theme={theme} setTheme={setTheme} />
        </div>
      </header>

      {/* Main */}
      <main className="flex-1 mx-auto w-full max-w-6xl px-4 py-6">
        <ChatWindow />
      </main>

      {/* Footer */}
      <footer className="mx-auto max-w-6xl px-4 py-3 text-center text-xs text-slate-500 dark:text-slate-400 border-t border-slate-200 dark:border-slate-800">
        Answers are generated from the institution’s knowledge base. If
        information is missing, contact support.
      </footer>
    </div>
  );
}
