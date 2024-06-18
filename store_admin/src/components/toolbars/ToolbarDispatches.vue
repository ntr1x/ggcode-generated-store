<script setup lang="ts" generic="T">
import { ref, shallowRef, reactive } from 'vue';
import Menubar from 'primevue/menubar'
import Button from 'primevue/button'
import Chip from 'primevue/chip'
import Menu from 'primevue/menu';
import { type Option } from '../dialogs/FilterDialog.vue';

import { useModalStore } from '../../store/modalStore';
import PickerDispatchType from '../controls/PickerDispatchType.vue'
import PickerDispatchStatus from '../controls/PickerDispatchStatus.vue'

const modalStore = useModalStore()

const filterByTypeId = defineModel('filterByTypeId')
const filterByStatusId = defineModel('filterByStatusId')
const sortById = defineModel<'asc' | 'desc' | undefined>('sortById')
const sortByCreatedAt = defineModel<'asc' | 'desc' | undefined>('sortByCreatedAt')
const sortByUpdatedAt = defineModel<'asc' | 'desc' | undefined>('sortByUpdatedAt')
const sortByTypeId = defineModel<'asc' | 'desc' | undefined>('sortByTypeId')
const sortByStatusId = defineModel<'asc' | 'desc' | undefined>('sortByStatusId')

const selection = defineModel<T[]>('selection')

const filters = reactive<Record<string, Option | undefined>>({
  typeId: undefined,
  statusId: undefined,
})

const sortings = reactive<Record<string, 'asc' | 'desc' | undefined>>({
  id: undefined,
  createdAt: undefined,
  updatedAt: undefined,
  typeId: undefined,
  statusId: undefined,
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
            component: shallowRef(PickerDispatchType),
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
        label: 'Status Id',
        icon: 'pi pi-plus-circle',
        command: () => [
          modalStore.openModal(() => ({
            component: shallowRef(PickerDispatchStatus),
            props: {},
            handlers: {
              ['update:model-value'](value: string) {
                filterByStatusId.value = value
              },
              ['change:option'](option: Option) {
                filters.statusId = option
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
        label: 'CreatedAt (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.createdAt = 'asc'
          sortByCreatedAt.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'CreatedAt (Descending)',
        command: () => {
          sortings.createdAt = 'desc'
          sortByCreatedAt.value = 'desc'
        }
      },
      {
        label: 'UpdatedAt (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.updatedAt = 'asc'
          sortByUpdatedAt.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'UpdatedAt (Descending)',
        command: () => {
          sortings.updatedAt = 'desc'
          sortByUpdatedAt.value = 'desc'
        }
      },
      {
        label: 'TypeId (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.typeId = 'asc'
          sortByTypeId.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'TypeId (Descending)',
        command: () => {
          sortings.typeId = 'desc'
          sortByTypeId.value = 'desc'
        }
      },
      {
        label: 'StatusId (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.statusId = 'asc'
          sortByStatusId.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'StatusId (Descending)',
        command: () => {
          sortings.statusId = 'desc'
          sortByStatusId.value = 'desc'
        }
      },
    ]
  }
])

function handleRemoveSelected() {
  console.log('remove_selected')
}
function handleCreateDispatch() {
  console.log('create_dispatch')
}
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
            <Chip v-if="filterByStatusId !== undefined" removable @remove="filterByStatusId = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>Status Id: </b><span v-text="filters.statusId?.title || filterByStatusId"></span></span>
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
            <Chip v-if="sortByCreatedAt !== undefined" removable @remove="sortByCreatedAt = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>CreatedAt: </b><span v-text="sortings.createdAt || sortByCreatedAt"></span></span>
            </Chip>
            <Chip v-if="sortByUpdatedAt !== undefined" removable @remove="sortByUpdatedAt = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>UpdatedAt: </b><span v-text="sortings.updatedAt || sortByUpdatedAt"></span></span>
            </Chip>
            <Chip v-if="sortByTypeId !== undefined" removable @remove="sortByTypeId = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>TypeId: </b><span v-text="sortings.typeId || sortByTypeId"></span></span>
            </Chip>
            <Chip v-if="sortByStatusId !== undefined" removable @remove="sortByStatusId = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>StatusId: </b><span v-text="sortings.statusId || sortByStatusId"></span></span>
            </Chip>
          </div>
        </div>
      </div>
    </template>
    <template #end>
      <div class="flex flex-wrap">
        <div class="flex items-center group-actions">
          <Button
            v-if="selection.length > 0"
            text
            label="Remove Selected"
            class="ms-2"
            severity="danger"
            @click="handleRemoveSelected"
          />
          <Button
            text
            label="Create Dispatch"
            class="ms-2"
            @click="handleCreateDispatch"
          />
        </div>
      </div>
    </template>
  </Menubar>
</template>
