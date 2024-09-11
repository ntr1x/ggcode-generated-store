<script setup lang="ts">
import Toolbar from 'primevue/toolbar';

export type SectionHeadingProps = {
  title: string
  tag?: 'h1' | 'h2' | 'h3'
}

withDefaults(defineProps<SectionHeadingProps>(), {
  tag: 'h1'
})
</script>

<template>
  <Toolbar class="rounded-none border-0 border-b">
      <template #start>
        <component
          :is="tag"
          class="font-bold"
          :class="{
            'text-2xl': tag === 'h1',
            'text-xl': tag === 'h2',
            'text-base': tag === 'h3',
          }"
          v-html="title"
        ></component>
      </template>
    </Toolbar>
</template>