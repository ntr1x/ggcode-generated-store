<script setup lang="ts">
import { computed } from 'vue';

const props = defineProps<{
  value: any,
  label?: string,
  route?: string,
  icon?: string,
  decoration?: string,
}>()

const decorations: Record<string, any> = {
  "message:info": "rounded text-red-700 dark:text-red-300 bg-red-100 dark:bg-red-500/20",
  "order:canceled": "rounded text-fuchsia-700 dark:text-fuchsia-300 bg-fuchsia-100 dark:bg-fuchsia-500/20",
  "order:complete": "rounded text-purple-700 dark:text-purple-300 bg-purple-100 dark:bg-purple-500/20",
  "order:created": "rounded text-blue-700 dark:text-blue-300 bg-blue-100 dark:bg-blue-500/20",
  "order:failed": "rounded text-rose-700 dark:text-rose-300 bg-rose-100 dark:bg-rose-500/20",
  "order:packaged": "rounded text-emerald-700 dark:text-emerald-300 bg-emerald-100 dark:bg-emerald-500/20",
  "order:paid": "rounded text-teal-700 dark:text-teal-300 bg-teal-100 dark:bg-teal-500/20",
  "order:ready": "rounded text-sky-700 dark:text-sky-300 bg-sky-100 dark:bg-sky-500/20",
  "profile:updated": "rounded text-green-700 dark:text-green-300 bg-green-100 dark:bg-green-500/20",
  "security:signin": "rounded text-orange-700 dark:text-orange-300 bg-orange-100 dark:bg-orange-500/20",
  "security:signup": "rounded text-amber-700 dark:text-amber-300 bg-amber-100 dark:bg-amber-500/20"
}

const label = computed(() => {
  switch (true) {
    case props.label != undefined: return props.label
    case props.value != undefined: return props.value.description
    default: return null
  }
})

const route = computed(() => {
  switch (true) {
    case props.route != undefined: return props.route
    case props.value != undefined: return null
    default: return null
  }
})

const icon = computed(() => {
  switch (true) {
    case props.icon != undefined: return props.icon
    case props.value != undefined: return null
    default: return null
  }
})

const decoration = computed(() => {
  switch (true) {
    case props.decoration != undefined: return props.decoration
    case props.value != undefined: return decorations[props.value.name]
    default: return null
  }
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
