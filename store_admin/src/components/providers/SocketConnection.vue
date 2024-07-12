<script setup lang="ts">
import { onMounted, onBeforeUnmount } from 'vue';
import { EventEmitter } from 'events';
import { v4 } from 'uuid';
import { useWebSocket } from '@vueuse/core';
import { useToast } from "primevue/usetoast";
import { useSocketStore } from '../../store/socketStore';
import { useAuthStore } from '../../store/authStore';

const authStore = useAuthStore()
const socketStore = useSocketStore()
const toast = useToast();

const emitter = new EventEmitter()

const props = defineProps<{
  name: string
  uri: string
}>()

onMounted(() => {
  socketStore.registerEmitter(props.name, emitter)
})

onBeforeUnmount(() => {
  socketStore.unregisterEmitter(props.name)
})

const { status, send } = useWebSocket(props.uri, {
  autoReconnect: true,
  heartbeat: {
    message: '{"@type":"HELLO"}',
    interval: 30000,
    pongTimeout: 15000,
  },

  async onConnected(_) {
    try {
      console.log('On connected')
      const subscriptions = socketStore.getSubscriptions(props.name)
      const accessToken = await authStore.requireToken()
      for (const subscription of Object.values(subscriptions)) {
        const message = {
          '@type': 'SUBSCRIBE',
          accessToken,
          messageId: v4(),
          subscriptionId: subscription.id,
          selectors: subscription.selectors,
        }
        send(JSON.stringify(message))
      }
    } catch (e) {
      console.log('e', e)
    }
  },

  onDisconnected() {
    console.log('On disconnected')
  },

  onError(_, event) {
    console.log(event)
  },

  onMessage(_, event) {
    const message = JSON.parse(event.data)
    if (message['@type'] == 'MESSAGE') {
      emitter.emit('message', message)
    }
  },
})

emitter.on('subscribe', async (subscription) => {
  const accessToken = await authStore.requireToken()
  const message = {
    '@type': 'SUBSCRIBE',
    accessToken,
    messageId: v4(),
    subscriptionId: subscription.id,
    selectors: subscription.selectors,
  }
  if (status.value == 'OPEN') {
    send(JSON.stringify(message))
  }
})

emitter.on('unsubscribe', async (subscription) => {
  const accessToken = await authStore.requireToken()
  const message = {
    '@type': 'UNSUBSCRIBE',
    accessToken,
    messageId: v4(),
    subscriptionId: subscription.id,
  }
  if (status.value == 'OPEN') {
    send(JSON.stringify(message))
  }
})

emitter.on('message', (message) => {
  const payload = message?.payload?.payload
  toast.add({
    severity: 'info',
    summary: payload?.title || 'Info',
    detail: payload?.detail || 'Info Description',
    life: 3000
  });
})

</script>

<template>
  <slot v-if="status == 'OPEN'"></slot>
</template>
