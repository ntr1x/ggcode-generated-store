<script setup lang="ts">
import Column from 'primevue/column';
import DataTable from 'primevue/datatable';
import { type State } from '../../hooks/useAxiosRequest';
import SymbolId from '../symbols/SymbolId.vue'
import SymbolPrice from '../symbols/SymbolPrice.vue'

export type GridItemsRecord = Record<string, any>

export type GridItemsPage = {
  content: GridItemsRecord[]
}

export type GridItemsProps = {
  state: State<GridItemsPage>,
  scrollable?: boolean,
  scrollHeight?: string,
  hideId?: boolean,
  hideProduct?: boolean,
  hidePrice?: boolean,
  hideCategory?: boolean,
  hideRegion?: boolean,
  hideShop?: boolean,
}

const selection = defineModel<GridItemsRecord[]>('selection')

withDefaults(defineProps<GridItemsProps>(), {
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
        <SymbolId :value="slotProps.data.id" :label="'REF'" :route="`/admin/items/${slotProps.data.id}`"/>
      </template>
    </Column>
    <Column v-if="!hideProduct" field="product.name" header="Product" />
    <Column v-if="!hidePrice" header="Price">
      <template #body="slotProps">
        <SymbolPrice :value="slotProps.data.product.price"/>
      </template>
    </Column>
    <Column v-if="!hideCategory" field="product.category.name" header="Category" />
    <Column v-if="!hideRegion" field="shop.region.name" header="Region" />
    <Column v-if="!hideShop" field="shop.name" header="Shop" />
  </DataTable>
</template>
