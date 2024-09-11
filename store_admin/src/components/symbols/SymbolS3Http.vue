<script setup lang="ts">
import { computed } from 'vue';

const props = defineProps<{
  value: any,
  label?: string,
  route?: string,
  icon?: string,
  decoration?: string,
}>()


const label = computed(() => {
  switch (true) {
    case props.label != undefined: return props.label
    case props.value != undefined: return (props.value == null || props.value == null) ? null : `s3://app-public-document/${props.value}`
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
    case props.value != undefined: return "pi pi-download"
    default: return null
  }
})

const decoration = computed(() => {
  switch (true) {
    case props.decoration != undefined: return props.decoration
    case props.value != undefined: return null
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
