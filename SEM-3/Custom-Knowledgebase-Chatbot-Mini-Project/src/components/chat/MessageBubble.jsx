import { Bot, User } from "lucide-react";

export default function MessageBubble({ role, content }) {
  const isUser = role === "user";
  return (
    <div className={`flex ${isUser ? "justify-end" : "justify-start"} w-full`}>
      <div className={`flex items-start gap-3 max-w-[90%] sm:max-w-[75%]`}>
        {!isUser && (
          <div className="mt-1 shrink-0 h-8 w-8 rounded-full bg-blue-600 text-white flex items-center justify-center">
            <Bot size={16} />
          </div>
        )}
        <div
          className={`rounded-2xl px-4 py-3 text-sm leading-relaxed shadow-sm ${
            isUser
              ? "bg-blue-600 text-white"
              : "bg-slate-100 text-slate-900 dark:bg-slate-900 dark:text-slate-100 border border-slate-200 dark:border-slate-800"
          }`}
        >
          <div className="whitespace-pre-wrap">{content}</div>
        </div>
        {isUser && (
          <div className="mt-1 shrink-0 h-8 w-8 rounded-full bg-slate-200 text-slate-800 dark:bg-slate-800 dark:text-slate-100 flex items-center justify-center">
            <User size={16} />
          </div>
        )}
      </div>
    </div>
  );
}
