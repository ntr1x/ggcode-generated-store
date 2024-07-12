<script setup lang="ts" generic="T">
import { ref, shallowRef, reactive } from 'vue';
import Menubar from 'primevue/menubar'
import Button from 'primevue/button'
import Chip from 'primevue/chip'
import Menu from 'primevue/menu';
import { type Option } from '../dialogs/FilterDialog.vue';

import { useModalStore } from '../../store/modalStore';
import SearchPickerCustomerId from '../controls/SearchPickerCustomerId.vue'
import SelectPickerSubscriptionType from '../controls/SelectPickerSubscriptionType.vue'

const modalStore = useModalStore()

const filterByCustomerId = defineModel('filterByCustomerId')
const filterByTypeId = defineModel('filterByTypeId')
const sortById = defineModel<'asc' | 'desc' | undefined>('sortById')
const sortByCreatedAt = defineModel<'asc' | 'desc' | undefined>('sortByCreatedAt')
const sortByTypeId = defineModel<'asc' | 'desc' | undefined>('sortByTypeId')

const filters = reactive<Record<string, Option | undefined>>({
  customerId: undefined,
  typeId: undefined,
})

const sortings = reactive<Record<string, 'asc' | 'desc' | undefined>>({
  id: undefined,
  createdAt: undefined,
  typeId: undefined,
})

const filtersMenuRef = ref()

const filtersMenuItems = ref([
  {
    label: 'By Property',
    items: [
      {
        label: 'Customer Id',
        icon: 'pi pi-plus-circle',
        command: () => [
          modalStore.openModal(() => ({
            component: shallowRef(SearchPickerCustomerId),
            props: {},
            handlers: {
              ['update:model-value'](value: string) {
                filterByCustomerId.value = value
              },
              ['change:option'](option: Option) {
                filters.customerId = option
              }
            }
          }))
        ]
      },
      {
        label: 'Type Id',
        icon: 'pi pi-plus-circle',
        command: () => [
          modalStore.openModal(() => ({
            component: shallowRef(SelectPickerSubscriptionType),
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
        label: 'Created At (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.createdAt = 'asc'
          sortByCreatedAt.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'Created At (Descending)',
        command: () => {
          sortings.createdAt = 'desc'
          sortByCreatedAt.value = 'desc'
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
            <Chip v-if="filterByCustomerId !== undefined" removable @remove="filterByCustomerId = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>Customer Id: </b><span v-text="filters.customerId?.title || filterByCustomerId"></span></span>
            </Chip>
            <Chip v-if="filterByTypeId !== undefined" removable @remove="filterByTypeId = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>Type Id: </b><span v-text="filters.typeId?.title || filterByTypeId"></span></span>
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
              <span><b>Created At: </b><span v-text="sortings.createdAt || sortByCreatedAt"></span></span>
            </Chip>
            <Chip v-if="sortByTypeId !== undefined" removable @remove="sortByTypeId = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>Type Id: </b><span v-text="sortings.typeId || sortByTypeId"></span></span>
            </Chip>
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
