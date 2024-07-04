<script setup lang="ts">

import type { ModalContext } from '../../store/modalStore';
import type { ActionItem } from "../../collections/menus.d";
import { inject, ref, computed, watch } from 'vue';
import { useRouter } from 'vue-router';
import { useAuthStore } from "../../store/authStore";
import Dialog from 'primevue/dialog';
import InputText from 'primevue/inputtext';
import { menuPersonal } from '../../collections/menus/menuPersonal'
import { menuOrders } from '../../collections/menus/menuOrders'
import { menuProfiles } from '../../collections/menus/menuProfiles'
import { menuCatalogue } from '../../collections/menus/menuCatalogue'
import { menuNotifications } from '../../collections/menus/menuNotifications'
import { menuDictionaries } from '../../collections/menus/menuDictionaries'

const context = inject<ModalContext>('modalContext')!

const query = ref<string>('')

const router = useRouter()
const authStore = useAuthStore()

const pages: ActionItem[] = [
  ...menuPersonal(router).items,
  ...menuOrders(router).items,
  ...menuProfiles(router).items,
  ...menuCatalogue(router).items,
  ...menuNotifications(router).items,
  ...menuDictionaries(router).items,
]

const actions: ActionItem[] = [
  { type: 'action', label: 'Sign Out', description: 'Sign Out from the application', execute: () => { authStore.doLogout() } },
]

const filteredPages = computed(() => {
  const items = pages
  const value = query.value.toLowerCase()
  return value.trim() == ''
    ? items
    : items.filter(item => item.label.toLowerCase().includes(value))
})

const filteredActions = computed(() => {
  const items = actions
  const value = query.value.toLowerCase()
  return value.trim() == ''
    ? items
    : items.filter(item => item.label.toLowerCase().includes(value))
})

const groups = computed(() => {
  const groups = [
    {
      key: 'pages',
      label: 'Page',
      items: filteredPages.value
        .slice(0, 3)
        .map((item, index) => ({
          ...item,
          key: `item-${index}`
        }))
    },
    {
      key: 'actions',
      label: 'Action',
      items: filteredActions.value
        .slice(0, 3)
        .map((item, index) => ({
          ...item,
          key: `item-${index}`
        }))
    },
  ]

  return groups.filter(group => group.items.length > 0)
})

const items = computed(() => {
  const items = []
  let index = 0
  for (const group of groups.value) {
    for (const item of group.items) {
      items[index] = {
        index,
        item,
        group,
        key: `${group.key}.${item.key}`
      }
      index++
    }
  }
  return items
})

const selectedIndex = ref<number>(0)

watch([query], () => {
  selectedIndex.value = 0
})

function handleKeyup(key: KeyboardEvent) {
  if (items.value.length > 0) {
    if (key.code == 'ArrowUp') {
      selectedIndex.value = (items.value.length + selectedIndex.value - 1) % items.value.length
    } else if (key.code == 'ArrowDown') {
      selectedIndex.value = (selectedIndex.value + 1) % items.value.length
    } else if (key.code == 'Enter') {
      handleAction(items.value[selectedIndex.value].item)
    }
  }
}

function handleAction(item: ActionItem) {
  context.doClose()
  item.execute()
}

</script>
<template>
  <Dialog :visible="true" @update:visible="context.doClose" modal header="Execute Action" :style="{ maxWidth: '35rem' }">
    <div class="relative h-full flex flex-col" @keyup="handleKeyup">
      <div class="gap-3 mb-3 mt-3">
        <div class="relative w-full">
          <i class="pi pi-search absolute top-2/4 -mt-2 right-4 text-surface-400 dark:text-surface-600" />
          <InputText autofocus v-model="query" placeholder="Search" size="large" class="pr-10 w-full" />
        </div>
      </div>
      <div class="my-3 overflow-y-auto" :style="{ minHeight: '25rem' }">
        <div v-for="(item, index) in items" :key="item.key" @click="() => handleAction(item.item)">
          <div class="flex p-2 rounded cursor-pointer hover:bg-slate-100" :class="{
            'bg-slate-200': index == selectedIndex,
            'hover:bg-slate-200': index == selectedIndex
          }">
            <div class="flex-1">
              <div v-text="item.item.label" class="font-semibold"></div>
              <div v-text="item.item.description"></div>
            </div>
            <div class="flex-none"><span class="p-1 rounded bg-blue-600 text-white font-semibold text-xs" v-html="item.group.label"></span></div>
          </div>
        </div>
      </div>
    </div>
  </Dialog>
</template>
