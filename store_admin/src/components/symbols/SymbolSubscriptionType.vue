<script setup lang="ts">
import { computed } from 'vue';

const props = defineProps<{
  value: any,
  label?: string,
  route?: string,
  icon?: string,
  decoration?: string,
}>()

const decorations = {
  "FCM": "rounded bg-green-800 text-white",
  "HMS": "rounded bg-indigo-800 text-white"
}
const label = computed(() => {
  return props.label != undefined
    ? props.label
    : props.value.description
})

const route = computed(() => {
  return props.route != undefined
    ? props.route
    : undefined
})

const icon = computed(() => {
  return props.icon != undefined
    ? props.icon
    : undefined
})

const decoration = computed(() => {
  return props.decoration != undefined
    ? props.decoration
    : decorations[props.value.name]
})
</script>

<template>
  <div class="inline-flex items-center gap-2 px-2" :class="decoration">
    <i v-if="icon != null" class="text-xs" :class="icon"></i>
    <template v-if="route != null">
      <router-link :to="route" class="text-ellipsis whitespace-nowrap overflow-hidden text-indigo-600 hover:underline hover:text-indigo-800 visited:text-purple-600">
        <span v-if="label != null" class="text-ellipsis whitespace-nowrap" v-html="label"></span>
        <span v-else><i class="text-slate-500">Empty</i></span>
      </router-link>
    </template>
    <template v-else>
      <span class="text-ellipsis whitespace-nowrap overflow-hidden">
        <span v-if="label != null" class="text-base" v-html="label"></span>
        <span v-else><i class="text-slate-500">Empty</i></span>
      </span>
    </template>
  </div>
</template>
