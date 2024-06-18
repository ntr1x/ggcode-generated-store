export type ActionItem = {
  type: string
  label: string
  description: string
  execute: () => void
}

export type Menu = {
  title: string
  items: ActionItem[]
}
