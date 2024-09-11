import { type EventEmitter } from 'events';
import { defineStore } from "pinia"

export type SelectorFilter = {
  type: 'EQUAL' | 'MATCH'
  value: string
}

export type MessageSelector = {
  headers?: Record<string, SelectorFilter>
  properties?: Record<string, SelectorFilter>
}

export type SocketSubscription = {
  id: string
  selectors: MessageSelector[]
}

export type SocketHolder = {
  emitter?: EventEmitter,
  subscriptions: Record<string, SocketSubscription>
}

export type SocketStoreState = {
  registry: Record<string, SocketHolder>
}

export const useSocketStore = defineStore('socket', {
  state: (): SocketStoreState => ({
    registry: {}
  }),
  actions: {
    registerEmitter(name: string, emitter: EventEmitter) {
      this.registry[name] = {
        emitter,
        subscriptions: {}
      }
    },

    unregisterEmitter(name: string) {
      delete this.registry[name]
    },

    createSubscription(socketName: string, subscription: SocketSubscription) {
      const holder = this.registry[socketName]
      if (holder != null) {
        holder.subscriptions[subscription.id] = subscription
        holder.emitter?.emit('subscribe', subscription)
      }
    },

    removeSubscription(socketName: string, subscription: SocketSubscription) {
      const holder = this.registry[socketName]
      if (holder != null) {
        delete holder.subscriptions[subscription.id]
        holder.emitter?.emit('unsubscribe', subscription)
      }
    }
  },
  getters: {
    getSubscriptions() {
      return (name: string) => {
        return this.registry[name]?.subscriptions || []
      }
    },
    getEmitter() {
      return (name: string) => {
        return this.registry[name]?.emitter
      }
    }
  }
})
