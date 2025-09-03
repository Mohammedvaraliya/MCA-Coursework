import { useMemo, useState } from "react";
import { ChatWindow } from "@/components/chat/chat-window";
import { ThemeToggle } from "@/components/chat/theme-toggle";
import { Button } from "@/components/ui/button";
import { cn } from "@/lib/utils";
import { GraduationCap, Library, MessageSquare } from "lucide-react";

type Audience = "student" | "faculty" | "staff" | "visitor";

export default function Page() {
  const [audience, setAudience] = useState < Audience > "student";

  const audienceOptions: { key: Audience, label: string, desc: string }[] =
    useMemo(
      () => [
        {
          key: "student",
          label: "Student",
          desc: "Admissions, courses, exams, scholarships",
        },
        {
          key: "faculty",
          label: "Faculty",
          desc: "Policies, research, schedules",
        },
        { key: "staff", label: "Staff", desc: "HR, operations, facilities" },
        {
          key: "visitor",
          label: "Visitor",
          desc: "Campus, events, general info",
        },
      ],
      []
    );

  return (
    <main className="min-h-dvh bg-background text-foreground">
      <header className="border-b bg-background/60 backdrop-blur supports-[backdrop-filter]:bg-background/40">
        <div className="mx-auto flex max-w-5xl items-center justify-between px-4 py-3">
          <div className="flex items-center gap-2">
            <div className="inline-flex h-9 w-9 items-center justify-center rounded-md border bg-card">
              <GraduationCap className="h-5 w-5" aria-hidden />
            </div>
            <div>
              <h1 className="text-balance text-lg font-semibold">
                Institution Knowledge Chat
              </h1>
              <p className="text-sm text-muted-foreground">
                AI answers for students, faculty, staff and visitors
              </p>
            </div>
          </div>
          <div className="flex items-center gap-2">
            <ThemeToggle />
            <a
              href="#kb"
              className={cn(
                "hidden rounded-md border px-3 py-2 text-sm hover:bg-accent hover:text-accent-foreground md:inline-block"
              )}
            >
              Knowledge Base
            </a>
          </div>
        </div>
      </header>

      <section className="mx-auto grid max-w-5xl gap-6 px-4 py-6 md:grid-cols-[280px_1fr]">
        <aside className="space-y-4">
          <div className="rounded-xl border bg-card p-4">
            <div className="mb-2 flex items-center gap-2">
              <MessageSquare className="h-4 w-4 text-primary" />
              <h2 className="text-sm font-medium">Audience</h2>
            </div>
            <div className="grid gap-2">
              {audienceOptions.map((opt) => (
                <Button
                  key={opt.key}
                  variant={audience === opt.key ? "default" : "outline"}
                  className="justify-start"
                  onClick={() => setAudience(opt.key)}
                >
                  <span className="font-medium">{opt.label}</span>
                </Button>
              ))}
            </div>
            <p className="mt-3 text-xs text-muted-foreground">
              Tailors answers for the selected group. No history is saved across
              visits.
            </p>
          </div>

          <div id="kb" className="rounded-xl border bg-card p-4">
            <div className="mb-2 flex items-center gap-2">
              <Library className="h-4 w-4 text-primary" />
              <h2 className="text-sm font-medium">Knowledge Base</h2>
            </div>
            <p className="text-sm text-muted-foreground">
              This demo uses a built-in institutional KB. Replace the file at
              data/kb.json with your content.
            </p>
          </div>
        </aside>

        <div>
          <ChatWindow audience={audience} />
        </div>
      </section>

      <footer className="mx-auto max-w-5xl px-4 py-6 text-center text-xs text-muted-foreground">
        Answers are AI-generated from your institutional knowledge. Verify
        critical information.
      </footer>
    </main>
  );
}
