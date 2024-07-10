<script setup lang="ts" generic="T">
import { ref, shallowRef, reactive } from 'vue';
import Menubar from 'primevue/menubar'
import Button from 'primevue/button'
import Chip from 'primevue/chip'
import Menu from 'primevue/menu';
import { type Option } from '../dialogs/FilterDialog.vue';

import { useModalStore } from '../../store/modalStore';
import SelectPickerPromotionType from '../controls/SelectPickerPromotionType.vue'

const modalStore = useModalStore()

const filterByPromotionType = defineModel('filterByPromotionType')

const filters = reactive<Record<string, Option | undefined>>({
  promotionType: undefined,
})

const filtersMenuRef = ref()

const filtersMenuItems = ref([
  {
    label: 'By Property',
    items: [
      {
        label: 'Promotion Type',
        icon: 'pi pi-plus-circle',
        command: () => [
          modalStore.openModal(() => ({
            component: shallowRef(SelectPickerPromotionType),
            props: {},
            handlers: {
              ['update:model-value'](value: string) {
                filterByPromotionType.value = value
              },
              ['change:option'](option: Option) {
                filters.promotionType = option
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
            <Chip v-if="filterByPromotionType !== undefined" removable @remove="filterByPromotionType = undefined" class="p-1 ms-2 my-1 whitespace-nowrap">
              <span><b>Promotion Type: </b><span v-text="filters.promotionType?.title || filterByPromotionType"></span></span>
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
