import { useStorage } from "@vueuse/core"
import { defineStore } from "pinia"
import { MaybeRef } from "vue"

export type UiStoreState = {
  isMenuSidebarPinned: MaybeRef<boolean>,
}

export const useUiStore = defineStore('ui', {
  state: () => ({
    isMenuSidebarPinned: useStorage('pinia/ui/isMenuSidebarPinned', false),
    modals: []
  }),
  actions: {
    toggleMenuSidebar() { this.isMenuSidebarPinned = !this.isMenuSidebarPinned },
    pinMenuSidebar() { this.isMenuSidebarPinned = true },
    unpinMenuSidebar() { this.isMenuSidebarPinned = false },
  }
})
