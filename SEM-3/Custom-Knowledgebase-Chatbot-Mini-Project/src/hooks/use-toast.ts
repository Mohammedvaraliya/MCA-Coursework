import { toast as sonnerToast } from "sonner"

type ToastAction = {
  label: string
  onClick: () => void
}

type ToastOptions = {
  title?: string
  description?: string
  duration?: number
  action?: ToastAction
  important?: boolean
}

function useToast() {
  const toast = ({
    title = "",
    description,
    duration = 3000,
    action,
    important = false,
  }: ToastOptions) => {
    sonnerToast(title, {
      description,
      duration,
      action,
    })
  }

  const dismiss = (toastId?: string) => {
    if (toastId) {
      sonnerToast.dismiss(toastId)
    } else {
      sonnerToast.dismiss() // Dismiss all
    }
  }

  return {
    toast,
    dismiss,
    // Optional: expose promise toast
    promise: sonnerToast.promise,
  }
}

export { useToast }
