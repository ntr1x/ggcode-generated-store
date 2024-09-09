<script setup lang="ts">
import Column from 'primevue/column';
import DataTable from 'primevue/datatable';
import { type State } from '../../hooks/useAxiosRequest';
import SymbolId from '../symbols/SymbolId.vue'
import SymbolCustomer from '../symbols/SymbolCustomer.vue'
import SymbolPaymentType from '../symbols/SymbolPaymentType.vue'
import SymbolPaymentStatus from '../symbols/SymbolPaymentStatus.vue'
import SymbolPrice from '../symbols/SymbolPrice.vue'
import SymbolDatetime from '../symbols/SymbolDatetime.vue'

export type GridPaymentsRecord = Record<string, any>

export type GridPaymentsPage = {
  content: GridPaymentsRecord[]
}

export type GridPaymentsProps = {
  state: State<GridPaymentsPage>,
  scrollable?: boolean,
  scrollHeight?: string,
  hideId?: boolean,
  hideCustomer?: boolean,
  hideOrder?: boolean,
  hidePaymentType?: boolean,
  hidePaymentStatus?: boolean,
  hideRegion?: boolean,
  hideValue?: boolean,
  hideCreatedAt?: boolean,
}

const selection = defineModel<GridPaymentsRecord[]>('selection')

withDefaults(defineProps<GridPaymentsProps>(), {
  scrollable: true,
  scrollHeight: 'flex',
})

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
        <SymbolId :value="slotProps.data.id" :label="'REF'" :route="`/admin/payments/${slotProps.data.id}`"/>
      </template>
    </Column>
    <Column v-if="!hideCustomer" header="Customer">
      <template #body="slotProps">
        <SymbolCustomer :value="slotProps.data.customer"/>
      </template>
    </Column>
    <Column v-if="!hideOrder" header="Order">
      <template #body="slotProps">
        <SymbolId :value="slotProps.data.order.id" :label="'ORDER REF'" :route="`/admin/orders/${slotProps.data.order.id}`"/>
      </template>
    </Column>
    <Column v-if="!hidePaymentType" header="Payment Type">
      <template #body="slotProps">
        <SymbolPaymentType :value="slotProps.data.paymentType"/>
      </template>
    </Column>
    <Column v-if="!hidePaymentStatus" header="Payment Status">
      <template #body="slotProps">
        <SymbolPaymentStatus :value="slotProps.data.paymentStatus"/>
      </template>
    </Column>
    <Column v-if="!hideRegion" field="order.region.name" header="Region" />
    <Column v-if="!hideValue" header="Value">
      <template #body="slotProps">
        <SymbolPrice :value="slotProps.data.value"/>
      </template>
    </Column>
    <Column v-if="!hideCreatedAt" header="Created At">
      <template #body="slotProps">
        <SymbolDatetime :value="slotProps.data.createdAt"/>
      </template>
    </Column>
  </DataTable>
</template>
