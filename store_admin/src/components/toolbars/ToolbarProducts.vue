<script setup lang="ts" generic="T">
import { ref, shallowRef, reactive } from 'vue';
import Menubar from 'primevue/menubar'
import Button from 'primevue/button'
import Chip from 'primevue/chip'
import Menu from 'primevue/menu';
import { type Option } from '../dialogs/PlatformDialogFilter.vue';

import { useModalStore } from '../../store/modalStore';
import SelectPickerCategoryId from '../controls/SelectPickerCategoryId.vue'

const modalStore = useModalStore()

const emit = defineEmits<{
  (e: 'refresh'): void
}>()

const filterByCategoryId = defineModel('filterByCategoryId')
const sortById = defineModel<'asc' | 'desc' | undefined>('sortById')
const sortByName = defineModel<'asc' | 'desc' | undefined>('sortByName')

const filters = reactive<Record<string, Option | undefined>>({
  categoryId: undefined,
})

const sortings = reactive<Record<string, 'asc' | 'desc' | undefined>>({
  id: undefined,
  name: undefined,
})

const filtersMenuRef = ref()

const filtersMenuItems = ref([
  {
    label: 'By Property',
    items: [
      {
        label: 'Category Id',
        icon: 'pi pi-plus-circle',
        command: () => [
          modalStore.openModal(() => ({
            component: shallowRef(SelectPickerCategoryId),
            props: {},
            handlers: {
              ['update:model-value'](value: string) {
                filterByCategoryId.value = value
              },
              ['change:option'](option: Option) {
                filters.categoryId = option
              }
            }
          }))
        ]
      },
    ]
  }
])

const sortersMenuRef = ref()

const sortersMenuItems = ref([
  {
    label: 'By Column',
    items: [
      {
        label: 'Id (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.id = 'asc'
          sortById.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'Id (Descending)',
        command: () => {
          sortings.id = 'desc'
          sortById.value = 'desc'
        }
      },
      {
        label: 'Name (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.name = 'asc'
          sortByName.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'Name (Descending)',
        command: () => {
          sortings.name = 'desc'
          sortByName.value = 'desc'
        }
      },
    ]
  }
])
</script>

<template>
  <Menubar>
    <template #start>
      <div class="flex flex-wrap">
        <div class="flex items-center group-filters">
          <div class="flex flex-none">
            <Button text label="Filters" icon="pi pi-filter" @click="filtersMenuRef.toggle($event)" />
            <Menu ref="filtersMenuRef" id="overlay_menu" :model="filtersMenuItems" :popup="true" />
          </div>
          <div class="flex flex-wrap flex-1">
            <Chip v-if="filterByCategoryId !== undefined" removable @remove="filterByCategoryId = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>Category Id: </b><span v-text="filters.categoryId?.title || filterByCategoryId"></span></span>
            </Chip>
          </div>
        </div>
        <div class="flex items-center group-sort">
          <div class="flex flex-none">
            <Button text label="Sort" icon="pi pi-sort" @click="sortersMenuRef.toggle($event)" />
            <Menu ref="sortersMenuRef" id="overlay_menu" :model="sortersMenuItems" :popup="true" />
          </div>
          <div class="flex flex-wrap flex-1">
            <Chip v-if="sortById !== undefined" removable @remove="sortById = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>Id: </b><span v-text="sortings.id || sortById"></span></span>
            </Chip>
            <Chip v-if="sortByName !== undefined" removable @remove="sortByName = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>Name: </b><span v-text="sortings.name || sortByName"></span></span>
            </Chip>
          </div>
        </div>
        <div class="flex items-center group-sort">
          <div class="flex flex-none">
            <Button text label="Refresh" icon="pi pi-refresh" @click="emit('refresh')" />
          </div>
        </div>
      </div>
    </template>
    <template #end>
      <div class="flex flex-wrap">
      </div>
    </template>
  </Menubar>
</template>
