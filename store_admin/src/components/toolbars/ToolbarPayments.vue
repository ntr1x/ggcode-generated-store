<script setup lang="ts" generic="T">
import { ref, shallowRef, reactive } from 'vue';
import Menubar from 'primevue/menubar'
import Button from 'primevue/button'
import Chip from 'primevue/chip'
import Menu from 'primevue/menu';
import { type Option } from '../dialogs/FilterDialog.vue';

import { useModalStore } from '../../store/modalStore';
import PickerPaymentType from '../controls/PickerPaymentType.vue'
import PickerPaymentStatus from '../controls/PickerPaymentStatus.vue'
import PickerOrderType from '../controls/PickerOrderType.vue'
import PickerOrderStatus from '../controls/PickerOrderStatus.vue'

const modalStore = useModalStore()

const filterByPaymentType = defineModel('filterByPaymentType')
const filterByPaymentStatus = defineModel('filterByPaymentStatus')
const filterByOrderType = defineModel('filterByOrderType')
const filterByOrderStatus = defineModel('filterByOrderStatus')

const selection = defineModel<T[]>('selection')

const filters = reactive<Record<string, Option | undefined>>({
  paymentType: undefined,
  paymentStatus: undefined,
  orderType: undefined,
  orderStatus: undefined,
})

const filtersMenuRef = ref()

const filtersMenuItems = ref([
  {
    label: 'By Property',
    items: [
      {
        label: 'Payment Type',
        icon: 'pi pi-plus-circle',
        command: () => [
          modalStore.openModal(() => ({
            component: shallowRef(PickerPaymentType),
            props: {},
            handlers: {
              ['update:model-value'](value: string) {
                filterByPaymentType.value = value
              },
              ['change:option'](option: Option) {
                filters.paymentType = option
              }
            }
          }))
        ]
      },
      {
        label: 'Payment Status',
        icon: 'pi pi-plus-circle',
        command: () => [
          modalStore.openModal(() => ({
            component: shallowRef(PickerPaymentStatus),
            props: {},
            handlers: {
              ['update:model-value'](value: string) {
                filterByPaymentStatus.value = value
              },
              ['change:option'](option: Option) {
                filters.paymentStatus = option
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
            <Chip v-if="filterByPaymentType !== undefined" removable @remove="filterByPaymentType = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>Payment Type: </b><span v-text="filters.paymentType?.title || filterByPaymentType"></span></span>
            </Chip>
            <Chip v-if="filterByPaymentStatus !== undefined" removable @remove="filterByPaymentStatus = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>Payment Status: </b><span v-text="filters.paymentStatus?.title || filterByPaymentStatus"></span></span>
            </Chip>
            <Chip v-if="filterByOrderType !== undefined" removable @remove="filterByOrderType = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>Order Type: </b><span v-text="filters.orderType?.title || filterByOrderType"></span></span>
            </Chip>
            <Chip v-if="filterByOrderStatus !== undefined" removable @remove="filterByOrderStatus = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>Order Status: </b><span v-text="filters.orderStatus?.title || filterByOrderStatus"></span></span>
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
