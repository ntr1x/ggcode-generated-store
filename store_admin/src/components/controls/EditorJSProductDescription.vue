<script setup lang="ts">
import { ref, onMounted, onBeforeUnmount } from 'vue';
import EditorJS from '@editorjs/editorjs';
import Header from '@editorjs/header';
import List from '@editorjs/list';

const model = defineModel<string | undefined>()

const editorRef = ref<HTMLElement | undefined>()
const editor = ref<EditorJS | undefined>()

onMounted(() => {
  console.log()
  editor.value = new EditorJS({
    holder: editorRef.value,
    minHeight: 0,
    placeholder: 'Product description',
    data: JSON.parse(model.value || '{}'),
    tools: {
      header: Header,
      list: List
    },
  });
})

onBeforeUnmount(() => {
  editor.value?.destroy()
  editor.value = undefined
})
</script>

<template>
  <div ref="w-full">
    <div class="w-full font-sans leading-none m-0 p-3 rounded-md text-surface-600 dark:text-surface-200 placeholder:text-surface-400 dark:placeholder:text-surface-500 bg-surface-0 dark:bg-surface-900 border border-surface-300 dark:border-surface-600 hover:border-primary-500 dark:hover:border-primary-400 focus:outline-none focus:outline-offset-0 focus:ring focus:ring-primary-500/50 dark:focus:ring-primary-400/50 focus:z-10 appearance-none transition-colors duration-200">
      <div ref="editorRef" class="with-typography"></div>
    </div>
  </div>
</template>
