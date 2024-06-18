<script setup lang="ts" generic="T">
import Column from 'primevue/column';
import DataTable from 'primevue/datatable';
import { type State } from '../../hooks/useAxiosRequest';
import SymbolId from '../symbols/SymbolId.vue'
import SymbolSubscriptionType from '../symbols/SymbolSubscriptionType.vue'
import SymbolCustomer from '../symbols/SymbolCustomer.vue'
import SymbolSessionId from '../symbols/SymbolSessionId.vue'
import SymbolDatetime from '../symbols/SymbolDatetime.vue'

export type ResponseData = {
  content: T[]
}

export type GridSubscriptionsProps = {
  state: State<ResponseData>,
  scrollable?: boolean,
  scrollHeight?: string,
  hideId?: boolean,
  hideType?: boolean,
  hideCustomer?: boolean,
  hideSessionId?: boolean,
  hideCreatedAt?: boolean,
}

const selection = defineModel<T[]>('selection')

withDefaults(defineProps<GridSubscriptionsProps>(), {
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
        <SymbolId :value="slotProps.data.id" :label="'REF'" :route="`/admin/subscriptions/${slotProps.data.id}`"/>
      </template>
    </Column>
    <Column v-if="!hideType" header="Type">
      <template #body="slotProps">
        <SymbolSubscriptionType :value="slotProps.data.type"/>
      </template>
    </Column>
    <Column v-if="!hideCustomer" header="Customer">
      <template #body="slotProps">
        <SymbolCustomer :value="slotProps.data.customer"/>
      </template>
    </Column>
    <Column v-if="!hideSessionId" header="Session Id">
      <template #body="slotProps">
        <SymbolSessionId :value="slotProps.data.sessionId"/>
      </template>
    </Column>
    <Column v-if="!hideCreatedAt" header="Created At">
      <template #body="slotProps">
        <SymbolDatetime :value="slotProps.data.createdAt"/>
      </template>
    </Column>
  </DataTable>
</template>
