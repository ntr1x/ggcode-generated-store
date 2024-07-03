<script setup lang="ts" generic="T">
import Column from 'primevue/column';
import DataTable from 'primevue/datatable';
import { type State } from '../../hooks/useAxiosRequest';
import SymbolId from '../symbols/SymbolId.vue'
import SymbolCustomer from '../symbols/SymbolCustomer.vue'

export type ResponseData = {
  content: []
}

export type GridCustomersProps = {
  state: State<ResponseData>,
  scrollable?: boolean,
  scrollHeight?: string,
  hideId?: boolean,
  hideName?: boolean,
  hideEmail?: boolean,
  hidePhone?: boolean,
}

const selection = defineModel<T[]>('selection')

withDefaults(defineProps<GridCustomersProps>(), {
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
        <SymbolId :value="slotProps.data.id" :label="'REF'" :route="`/admin/customers/${slotProps.data.id}`"/>
      </template>
    </Column>
    <Column v-if="!hideName" header="Name">
      <template #body="slotProps">
        <SymbolCustomer :value="slotProps.data" />
      </template>
    </Column>
    <Column v-if="!hideEmail" field="email" header="Email" />
    <Column v-if="!hidePhone" field="phone" header="Phone" />
  </DataTable>
</template>
