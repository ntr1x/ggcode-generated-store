import { defineStore } from "pinia"
import { v4 } from "uuid"
import { Component, ShallowRef } from "vue"

export type ModalProps = Record<string, any>
export type ModalHandlers = Record<string, any>

export type ModalStoreState = {
  modals: ModalItem[],
}

export type ModalItem = {
  key: string
  props?: ModalProps
  handlers?: ModalHandlers
  component: ShallowRef<Component>
}

export type ModalOpenProps = {
  key?: string
  props?: ModalProps
  handlers?: ModalHandlers
  component: ShallowRef<Component>
}

export type ModalCloseProps = {
  key: string
}

export type ModalContext = {
  modal: ModalItem
  doClose: () => void
}

export type ModalFactory = () => ModalOpenProps

export const useModalStore = defineStore('modal', {
  state: (): ModalStoreState => ({
    modals: []
  }),
  actions: {
    openModal(factory: ModalFactory) {
      const modalOpenProps = factory()
      const modal: ModalItem = {
        ...modalOpenProps,
        key: modalOpenProps.key != null ? modalOpenProps.key : v4()
      }
      this.modals = [...this.modals, modal]
    },

    closeModal(props: ModalCloseProps) {
      if (props?.key != null) {
        this.modals = this.modals.filter((item) => item.key !== props.key)
      }
    },

    popModal() {
      if (this.modals.length > 0) {
        this.modals = this.modals.slice(0, this.modals.length - 1)
      }
    }
  }
})
