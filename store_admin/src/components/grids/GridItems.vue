<script setup lang="ts" generic="T">
import Column from 'primevue/column';
import DataTable from 'primevue/datatable';
import { type State } from '../../hooks/useAxiosRequest';
import SymbolId from '../symbols/SymbolId.vue'
import SymbolPrice from '../symbols/SymbolPrice.vue'

export type ResponseData = {
  content: []
}

export type GridItemsProps = {
  state: State<ResponseData>,
  scrollable?: boolean,
  scrollHeight?: string,
  hideId?: boolean,
  hideProduct?: boolean,
  hidePrice?: boolean,
  hideCategory?: boolean,
  hideRegion?: boolean,
  hideShop?: boolean,
}

const selection = defineModel<T[]>('selection')

withDefaults(defineProps<GridItemsProps>(), {
  scrollable: true,
  scrollHeight: 'flex',
})

</script>

<template>
  <DataTable
    v-if="state.isLoaded"
    v-model:selection="selection"
    :value="state.data?.content || []"
    :scrollable="scrollable"
    :scrollHeight="scrollHeight"
    :class="{
      'overflow-hidden': scrollable
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
