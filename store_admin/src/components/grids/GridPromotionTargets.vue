<script setup lang="ts">
import Column from 'primevue/column';
import DataTable from 'primevue/datatable';
import { type State } from '../../hooks/useAxiosRequest';
import SymbolId from '../symbols/SymbolId.vue'
import SymbolPromotionType from '../symbols/SymbolPromotionType.vue'

export type GridPromotionTargetsRecord = Record<string, any>

export type GridPromotionTargetsPage = {
  content: GridPromotionTargetsRecord[]
}

export type GridPromotionTargetsProps = {
  state: State<GridPromotionTargetsPage>,
  scrollable?: boolean,
  scrollHeight?: string,
  hideId?: boolean,
  hidePromotionType?: boolean,
  hidePromotion?: boolean,
}

const selection = defineModel<GridPromotionTargetsRecord[]>('selection')

withDefaults(defineProps<GridPromotionTargetsProps>(), {
  scrollable: true,
  scrollHeight: 'flex',
})

const emit = defineEmits<{
  (e: 'refresh'): void
}>()

</script>

<template>
  <DataTable
    :loading="state.isLoading"
    v-model:selection="selection"
    :value="state.data?.content || []"
    :scrollable="scrollable"
    :scrollHeight="scrollHeight"
    :class="{
      'overflow-hidden': scrollable
    }"
    :pt="{
      column: {
        bodyCell: {
          class: 'text-left border-0 border-b border-solid border-r border-b p-2 border-surface-200 dark:border-surface-700'
        },
        headerCell: {
          class: 'font-normal text-sm text-left border-t-0 border-y border-r border-0 border-b border-solid p-2 bg-surface-50 text-surface-700 dark:text-white/80 dark:bg-surface-800 border-surface-200 dark:border-surface-700 focus-visible:outline-none focus-visible:outline-offset-0 focus-visible:ring focus-visible:ring-inset focus-visible:ring-primary-400/50 dark:focus-visible:ring-primary-300/50'
        }
      }
    }"
  >
    <Column selectionMode="multiple" headerStyle="width: 3rem" />
    <Column header="#" headerStyle="width:3rem">
      <template #body="slotProps">
        <div v-text="slotProps.index + 1"></div>
      </template>
    </Column>
    <Column v-if="!hideId" header="Id">
      <template #body="slotProps">
        <SymbolId :value="slotProps.data.id" :label="'REF'" :route="`/admin/promotion_target/${slotProps.data.id}`"/>
      </template>
    </Column>
    <Column v-if="!hidePromotionType" header="Promotion Type">
      <template #body="slotProps">
        <SymbolPromotionType :value="slotProps.data.promotion.type"/>
      </template>
    </Column>
    <Column v-if="!hidePromotion" field="promotion.name" header="Promotion" />
  </DataTable>
</template>
