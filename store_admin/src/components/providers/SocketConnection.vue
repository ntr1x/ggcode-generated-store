<script setup lang="ts">
import { useWebSocket } from '@vueuse/core';
import { EventEmitter } from 'events';
import { useSocketStore } from '../../store/socketStore';
import { v4 } from 'uuid';
import { useAuthStore } from '../../store/authStore';
import { onMounted } from 'vue';
import { onBeforeUnmount } from 'vue';

const authStore = useAuthStore()
const socketStore = useSocketStore()

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
  autoReconnect: false,
  heartbeat: {
    message: '{"@type":"HELLO"}',
    interval: 30000,
    pongTimeout: 15000,
  },

  async onConnected(_) {
    try {
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

  onError(_, event) {
    console.log(event)
  },

  onMessage(_, event) {
    emitter.emit('message', event);
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

</script>

<template>
  <slot v-if="status == 'OPEN'"></slot>
</template>
