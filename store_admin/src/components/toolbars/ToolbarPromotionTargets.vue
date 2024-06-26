<script setup lang="ts" generic="T">
import { ref, shallowRef, reactive } from 'vue';
import Menubar from 'primevue/menubar'
import Button from 'primevue/button'
import Chip from 'primevue/chip'
import Menu from 'primevue/menu';
import { type Option } from '../dialogs/FilterDialog.vue';

import { useModalStore } from '../../store/modalStore';
import PickerPromotionId from '../controls/PickerPromotionId.vue'

const modalStore = useModalStore()

const filterByPromotionId = defineModel('filterByPromotionId')

const filters = reactive<Record<string, Option | undefined>>({
  promotionId: undefined,
})

const filtersMenuRef = ref()

const filtersMenuItems = ref([
  {
    label: 'By Property',
    items: [
      {
        label: 'Promotion Id',
        icon: 'pi pi-plus-circle',
        command: () => [
          modalStore.openModal(() => ({
            component: shallowRef(PickerPromotionId),
            props: {},
            handlers: {
              ['update:model-value'](value: string) {
                filterByPromotionId.value = value
              },
              ['change:option'](option: Option) {
                filters.promotionId = option
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
            <Chip v-if="filterByPromotionId !== undefined" removable @remove="filterByPromotionId = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>Promotion Id: </b><span v-text="filters.promotionId?.title || filterByPromotionId"></span></span>
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
