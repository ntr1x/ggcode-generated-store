<script setup lang="ts" generic="T">
import { ref, reactive, shallowRef } from 'vue';
import Menubar from 'primevue/menubar'
import Button from 'primevue/button'
import Chip from 'primevue/chip'
import Menu from 'primevue/menu';
import { type Option } from '../dialogs/PlatformDialogFilter.vue';
import { useModalStore } from '../../store/modalStore';
import SelectPickerShopId from '../controls/SelectPickerShopId.vue'

export type SelectionItemsRecord = Record<string, any>

const modalStore = useModalStore()

const emit = defineEmits<{
  (e: 'refresh'): void
}>()

const filterByShopId = defineModel('filterByShopId')

const filters = reactive<Record<string, Option | undefined>>({
  shopId: undefined,
})

const filtersMenuRef = ref()

const filtersMenuItems = ref([
  {
    label: 'By Property',
    items: [
      {
        label: 'Shop Id',
        icon: 'pi pi-plus-circle',
        command: () => [
          modalStore.openModal(() => ({
            component: shallowRef(SelectPickerShopId),
            props: {},
            handlers: {
              ['update:model-value'](value: string) {
                filterByShopId.value = value
              },
              ['change:option'](option: Option) {
                filters.shopId = option
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
            <Chip v-if="filterByShopId !== undefined" removable @remove="filterByShopId = undefined" class="p-1 ms-2 my-1 md:whitespace-nowrap">
              <span><b>Shop Id: </b><span v-text="filters.shopId?.title || filterByShopId"></span></span>
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
