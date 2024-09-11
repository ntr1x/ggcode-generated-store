<script setup lang="ts">
import Column from 'primevue/column';
import DataTable from 'primevue/datatable';
import { type State } from '../../hooks/useAxiosRequest';
import SymbolId from '../symbols/SymbolId.vue'
import SymbolCustomer from '../symbols/SymbolCustomer.vue'
import SymbolOrderType from '../symbols/SymbolOrderType.vue'
import SymbolOrderStatus from '../symbols/SymbolOrderStatus.vue'
import SymbolSourceType from '../symbols/SymbolSourceType.vue'
import SymbolPrice from '../symbols/SymbolPrice.vue'
import SymbolDatetime from '../symbols/SymbolDatetime.vue'

export type GridOrdersRecord = Record<string, any>

export type GridOrdersPage = {
  content: GridOrdersRecord[]
}

export type GridOrdersProps = {
  state: State<GridOrdersPage>,
  scrollable?: boolean,
  scrollHeight?: string,
  hideId?: boolean,
  hideCustomer?: boolean,
  hideOrderType?: boolean,
  hideOrderStatus?: boolean,
  hideRegion?: boolean,
  hideSourceType?: boolean,
  hidePrice?: boolean,
  hideCreatedAt?: boolean,
}

const selection = defineModel<GridOrdersRecord[]>('selection')

withDefaults(defineProps<GridOrdersProps>(), {
  scrollable: true,
  scrollHeight: 'flex',
})

// @ts-ignore
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
        <SymbolId :value="slotProps.data.id" :label="'REF'" :route="`/admin/orders/${slotProps.data.id}`"/>
      </template>
    </Column>
    <Column v-if="!hideCustomer" header="Customer">
      <template #body="slotProps">
        <SymbolCustomer :value="slotProps.data.customer"/>
      </template>
    </Column>
    <Column v-if="!hideOrderType" header="Order Type">
      <template #body="slotProps">
        <SymbolOrderType :value="slotProps.data.orderType"/>
      </template>
    </Column>
    <Column v-if="!hideOrderStatus" header="Order Status">
      <template #body="slotProps">
        <SymbolOrderStatus :value="slotProps.data.orderStatus"/>
      </template>
    </Column>
    <Column v-if="!hideRegion" field="region.name" header="Region" />
    <Column v-if="!hideSourceType" header="Source Type">
      <template #body="slotProps">
        <SymbolSourceType :value="slotProps.data.sourceType"/>
      </template>
    </Column>
    <Column v-if="!hidePrice" header="Price">
      <template #body="slotProps">
        <SymbolPrice :value="slotProps.data.price"/>
      </template>
    </Column>
    <Column v-if="!hideCreatedAt" header="Created At">
      <template #body="slotProps">
        <SymbolDatetime :value="slotProps.data.createdAt"/>
      </template>
    </Column>
  </DataTable>
</template>
