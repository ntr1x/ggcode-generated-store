<script setup lang="ts" generic="T">
import { ref, shallowRef, reactive } from 'vue';
import Menubar from 'primevue/menubar'
import Button from 'primevue/button'
import Chip from 'primevue/chip'
import Menu from 'primevue/menu';
import { type Option } from '../dialogs/PlatformDialogFilter.vue';

import { useModalStore } from '../../store/modalStore';
import SelectPickerTemplateType from '../controls/SelectPickerTemplateType.vue'
import SelectPickerTemplateShape from '../controls/SelectPickerTemplateShape.vue'

const modalStore = useModalStore()

const emit = defineEmits<{
  (e: 'refresh'): void
}>()

const filterByTypeId = defineModel('filterByTypeId')
const filterByShapeId = defineModel('filterByShapeId')
const sortById = defineModel<'asc' | 'desc' | undefined>('sortById')
const sortByName = defineModel<'asc' | 'desc' | undefined>('sortByName')
const sortByTypeId = defineModel<'asc' | 'desc' | undefined>('sortByTypeId')
const sortByShapeId = defineModel<'asc' | 'desc' | undefined>('sortByShapeId')

const filters = reactive<Record<string, Option | undefined>>({
  typeId: undefined,
  shapeId: undefined,
})

const sortings = reactive<Record<string, 'asc' | 'desc' | undefined>>({
  id: undefined,
  name: undefined,
  typeId: undefined,
  shapeId: undefined,
})

const filtersMenuRef = ref()

const filtersMenuItems = ref([
  {
    label: 'By Property',
    items: [
      {
        label: 'Type Id',
        icon: 'pi pi-plus-circle',
        command: () => [
          modalStore.openModal(() => ({
            component: shallowRef(SelectPickerTemplateType),
            props: {},
            handlers: {
              ['update:model-value'](value: string) {
                filterByTypeId.value = value
              },
              ['change:option'](option: Option) {
                filters.typeId = option
              }
            }
          }))
        ]
      },
      {
        label: 'Shape Id',
        icon: 'pi pi-plus-circle',
        command: () => [
          modalStore.openModal(() => ({
            component: shallowRef(SelectPickerTemplateShape),
            props: {},
            handlers: {
              ['update:model-value'](value: string) {
                filterByShapeId.value = value
              },
              ['change:option'](option: Option) {
                filters.shapeId = option
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
      {
        label: 'Type Id (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.typeId = 'asc'
          sortByTypeId.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'Type Id (Descending)',
        command: () => {
          sortings.typeId = 'desc'
          sortByTypeId.value = 'desc'
        }
      },
      {
        label: 'Shape Id (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.shapeId = 'asc'
          sortByShapeId.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'Shape Id (Descending)',
        command: () => {
          sortings.shapeId = 'desc'
          sortByShapeId.value = 'desc'
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
            <Chip v-if="filterByTypeId !== undefined" removable @remove="filterByTypeId = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>Type Id: </b><span v-text="filters.typeId?.title || filterByTypeId"></span></span>
            </Chip>
            <Chip v-if="filterByShapeId !== undefined" removable @remove="filterByShapeId = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>Shape Id: </b><span v-text="filters.shapeId?.title || filterByShapeId"></span></span>
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
            <Chip v-if="sortByTypeId !== undefined" removable @remove="sortByTypeId = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>Type Id: </b><span v-text="sortings.typeId || sortByTypeId"></span></span>
            </Chip>
            <Chip v-if="sortByShapeId !== undefined" removable @remove="sortByShapeId = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>Shape Id: </b><span v-text="sortings.shapeId || sortByShapeId"></span></span>
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
