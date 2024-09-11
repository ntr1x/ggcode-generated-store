<script setup lang="ts">
import { computed, onMounted, onBeforeUnmount } from 'vue';
import { v4 } from 'uuid';
import { useSocketStore, type SocketSubscription, type MessageSelector } from '../../store/socketStore';

const props = defineProps<{
  socketName: string,
  selectors: MessageSelector[]
}>()

const subscription = computed<SocketSubscription>(() => ({
  id: v4(),
  selectors: props.selectors
}))

const socketStore = useSocketStore()

onMounted(() => {
  socketStore.createSubscription(props.socketName, subscription.value)
})

onBeforeUnmount(() => {
  socketStore.removeSubscription(props.socketName, subscription.value)
})

</script>

<template>
  <slot></slot>
</template>
