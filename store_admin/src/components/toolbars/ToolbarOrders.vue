<script setup lang="ts" generic="T">
import { ref, shallowRef, reactive } from 'vue';
import Menubar from 'primevue/menubar'
import Button from 'primevue/button'
import Chip from 'primevue/chip'
import Menu from 'primevue/menu';
import { type Option } from '../dialogs/FilterDialog.vue';

import { useModalStore } from '../../store/modalStore';
import PickerSourceType from '../controls/PickerSourceType.vue'
import PickerOrderType from '../controls/PickerOrderType.vue'
import PickerOrderStatus from '../controls/PickerOrderStatus.vue'
import PickerRegionId from '../controls/PickerRegionId.vue'

const modalStore = useModalStore()

const filterBySourceType = defineModel('filterBySourceType')
const filterByOrderType = defineModel('filterByOrderType')
const filterByOrderStatus = defineModel('filterByOrderStatus')
const filterByRegionId = defineModel('filterByRegionId')
const sortById = defineModel<'asc' | 'desc' | undefined>('sortById')
const sortByCustomerId = defineModel<'asc' | 'desc' | undefined>('sortByCustomerId')
const sortByOrderTypeId = defineModel<'asc' | 'desc' | undefined>('sortByOrderTypeId')
const sortByOrderStatusId = defineModel<'asc' | 'desc' | undefined>('sortByOrderStatusId')
const sortByCreatedAt = defineModel<'asc' | 'desc' | undefined>('sortByCreatedAt')
const sortByUpdatedAt = defineModel<'asc' | 'desc' | undefined>('sortByUpdatedAt')

const selection = defineModel<T[]>('selection')

const filters = reactive<Record<string, Option | undefined>>({
  sourceType: undefined,
  orderType: undefined,
  orderStatus: undefined,
  regionId: undefined,
})

const sortings = reactive<Record<string, 'asc' | 'desc' | undefined>>({
  id: undefined,
  customerId: undefined,
  orderTypeId: undefined,
  orderStatusId: undefined,
  createdAt: undefined,
  updatedAt: undefined,
})

const filtersMenuRef = ref()

const filtersMenuItems = ref([
  {
    label: 'By Property',
    items: [
      {
        label: 'Source Type',
        icon: 'pi pi-plus-circle',
        command: () => [
          modalStore.openModal(() => ({
            component: shallowRef(PickerSourceType),
            props: {},
            handlers: {
              ['update:model-value'](value: string) {
                filterBySourceType.value = value
              },
              ['change:option'](option: Option) {
                filters.sourceType = option
              }
            }
          }))
        ]
      },
      {
        label: 'Order Type',
        icon: 'pi pi-plus-circle',
        command: () => [
          modalStore.openModal(() => ({
            component: shallowRef(PickerOrderType),
            props: {},
            handlers: {
              ['update:model-value'](value: string) {
                filterByOrderType.value = value
              },
              ['change:option'](option: Option) {
                filters.orderType = option
              }
            }
          }))
        ]
      },
      {
        label: 'Order Status',
        icon: 'pi pi-plus-circle',
        command: () => [
          modalStore.openModal(() => ({
            component: shallowRef(PickerOrderStatus),
            props: {},
            handlers: {
              ['update:model-value'](value: string) {
                filterByOrderStatus.value = value
              },
              ['change:option'](option: Option) {
                filters.orderStatus = option
              }
            }
          }))
        ]
      },
      {
        label: 'Region Id',
        icon: 'pi pi-plus-circle',
        command: () => [
          modalStore.openModal(() => ({
            component: shallowRef(PickerRegionId),
            props: {},
            handlers: {
              ['update:model-value'](value: string) {
                filterByRegionId.value = value
              },
              ['change:option'](option: Option) {
                filters.regionId = option
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
        label: 'CustomerId (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.customerId = 'asc'
          sortByCustomerId.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'CustomerId (Descending)',
        command: () => {
          sortings.customerId = 'desc'
          sortByCustomerId.value = 'desc'
        }
      },
      {
        label: 'OrderTypeId (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.orderTypeId = 'asc'
          sortByOrderTypeId.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'OrderTypeId (Descending)',
        command: () => {
          sortings.orderTypeId = 'desc'
          sortByOrderTypeId.value = 'desc'
        }
      },
      {
        label: 'OrderStatusId (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.orderStatusId = 'asc'
          sortByOrderStatusId.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'OrderStatusId (Descending)',
        command: () => {
          sortings.orderStatusId = 'desc'
          sortByOrderStatusId.value = 'desc'
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
            <Chip v-if="filterBySourceType !== undefined" removable @remove="filterBySourceType = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>Source Type: </b><span v-text="filters.sourceType?.title || filterBySourceType"></span></span>
            </Chip>
            <Chip v-if="filterByOrderType !== undefined" removable @remove="filterByOrderType = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>Order Type: </b><span v-text="filters.orderType?.title || filterByOrderType"></span></span>
            </Chip>
            <Chip v-if="filterByOrderStatus !== undefined" removable @remove="filterByOrderStatus = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>Order Status: </b><span v-text="filters.orderStatus?.title || filterByOrderStatus"></span></span>
            </Chip>
            <Chip v-if="filterByRegionId !== undefined" removable @remove="filterByRegionId = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>Region Id: </b><span v-text="filters.regionId?.title || filterByRegionId"></span></span>
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
            <Chip v-if="sortByCustomerId !== undefined" removable @remove="sortByCustomerId = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>CustomerId: </b><span v-text="sortings.customerId || sortByCustomerId"></span></span>
            </Chip>
            <Chip v-if="sortByOrderTypeId !== undefined" removable @remove="sortByOrderTypeId = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>OrderTypeId: </b><span v-text="sortings.orderTypeId || sortByOrderTypeId"></span></span>
            </Chip>
            <Chip v-if="sortByOrderStatusId !== undefined" removable @remove="sortByOrderStatusId = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>OrderStatusId: </b><span v-text="sortings.orderStatusId || sortByOrderStatusId"></span></span>
            </Chip>
            <Chip v-if="sortByCreatedAt !== undefined" removable @remove="sortByCreatedAt = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>CreatedAt: </b><span v-text="sortings.createdAt || sortByCreatedAt"></span></span>
            </Chip>
            <Chip v-if="sortByUpdatedAt !== undefined" removable @remove="sortByUpdatedAt = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>UpdatedAt: </b><span v-text="sortings.updatedAt || sortByUpdatedAt"></span></span>
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
