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
  "message:info": "rounded bg-red-800 text-white",
  "order:canceled": "rounded bg-blue-800 text-white",
  "order:complete": "rounded bg-blue-800 text-white",
  "order:created": "rounded bg-blue-800 text-white",
  "order:failed": "rounded bg-blue-800 text-white",
  "order:packaged": "rounded bg-blue-800 text-white",
  "order:paid": "rounded bg-blue-800 text-white",
  "order:ready": "rounded bg-blue-800 text-white",
  "profile:updated": "rounded bg-green-800 text-white",
  "security:signin": "rounded bg-orange-800 text-white",
  "security:signup": "rounded bg-orange-800 text-white"
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