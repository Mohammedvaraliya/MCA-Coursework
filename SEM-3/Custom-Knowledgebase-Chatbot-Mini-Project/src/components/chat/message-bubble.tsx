import { Avatar, AvatarFallback } from "@/components/ui/avatar"
import { cn } from "@/lib/utils"

export function MessageBubble({
  role,
  content,
}: {
  role: "user" | "assistant" | "system" | "tool"
  content: string
}) {
  const isUser = role === "user"
  return (
    <div className={cn("flex items-start gap-3", isUser ? "justify-end" : "justify-start")}>
      {!isUser && (
        <Avatar className="h-8 w-8">
          <AvatarFallback className="bg-primary/10 text-primary">AI</AvatarFallback>
        </Avatar>
      )}

      <div
        className={cn(
          "max-w-[85%] rounded-2xl border px-3 py-2 text-sm shadow-sm",
          isUser ? "bg-primary text-primary-foreground" : "bg-card",
        )}
      >
        <div className="whitespace-pre-wrap text-pretty leading-6">{content}</div>
      </div>

      {isUser && (
        <Avatar className="h-8 w-8">
          <AvatarFallback className="bg-muted">U</AvatarFallback>
        </Avatar>
      )}
    </div>
  )
}
