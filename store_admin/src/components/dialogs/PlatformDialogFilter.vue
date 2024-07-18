<script setup lang="ts" generic="T">
import Dialog from 'primevue/dialog';
import type { ModalContext } from '../../store/modalStore';
import InputText from 'primevue/inputtext';
import { watch, ref, inject, computed } from 'vue';

export type Option = {
  key: string
  title: string
  description?: string
  value: any
}

const props = defineProps<{
  title: string
  isLoaded: boolean,
  isLoading: boolean,
  isFailed: boolean,
  options: Option[]
}>()

const model = defineModel<T>()

const emit = defineEmits<{
  (e: 'change:option', value: Option): void
}>()

const context = inject<ModalContext>('modalContext')!

const query = ref<string>('')

const filteredOptions = computed(() => {
  const value = query.value.toLowerCase()
  if (value.trim() == '') {
    return [
      { key: 'system-null', title: '<EMPTY>', value: null },
      ...props.options,
    ]
  }
  return value.trim() == ''
    ? props.options
    : props.options.filter(option => option.title.toLowerCase().includes(value))
})

const selectedIndex = ref<number>(0)

watch([query], () => {
  selectedIndex.value = 0
})

function handleKeyup(key: KeyboardEvent) {
  const options = filteredOptions.value
  if (options.length > 0) {
    if (key.code == 'ArrowUp') {
      selectedIndex.value = (options.length + selectedIndex.value - 1) % options.length
    } else if (key.code == 'ArrowDown') {
      selectedIndex.value = (selectedIndex.value + 1) % options.length
    } else if (key.code == 'Enter') {
      handleSelect(options[selectedIndex.value])
    }
  }
}

function handleSelect(option: Option) {
  model.value = option.value
  emit('change:option', option)
  context.doClose()
}

</script>
<template>
  <Dialog :visible="true" @update:visible="context.doClose" modal maximizable :header="title" class="w-full sm:max-w-[35rem]">
    <div class="relative h-full flex flex-col" @keyup="handleKeyup">
      <div class="gap-3 mb-3 mt-3">
        <div class="relative w-full">
          <i class="pi pi-search absolute top-2/4 -mt-2 right-4 text-surface-400 dark:text-surface-600" />
          <InputText autofocus v-model="query" placeholder="Search" size="large" class="pr-10 w-full" />
        </div>
      </div>
      <div class="my-3 overflow-y-auto" :style="{ minHeight: '25rem' }">
        <div v-for="(option, index) in filteredOptions" :key="option.key" @click="() => handleSelect(option)">
          <div class="flex p-2 rounded cursor-pointer hover:bg-slate-100" :class="{
            'bg-slate-200': index == selectedIndex,
            'hover:bg-slate-200': index == selectedIndex
          }">
            <div class="flex-1">
              <div v-text="option.title" class="text-black font-semibold" :class="{
                'italic': option.value == null,
                'text-opacity-50': option.value == null,
              }"></div>
              <div v-text="option.description"></div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </Dialog>
</template>
