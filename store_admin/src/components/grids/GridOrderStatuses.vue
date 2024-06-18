<script setup lang="ts" generic="T">
import Column from 'primevue/column';
import DataTable from 'primevue/datatable';
import { type State } from '../../hooks/useAxiosRequest';
import SymbolId from '../symbols/SymbolId.vue'

export type ResponseData = {
  content: T[]
}

export type GridOrderStatusesProps = {
  state: State<ResponseData>,
  scrollable?: boolean,
  scrollHeight?: string,
  hideId?: boolean,
  hideName?: boolean,
  hideDescription?: boolean,
}

const selection = defineModel<T[]>('selection')

withDefaults(defineProps<GridOrderStatusesProps>(), {
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
        <SymbolId :value="slotProps.data.id"/>
      </template>
    </Column>
    <Column v-if="!hideName" field="name" header="Name" />
    <Column v-if="!hideDescription" field="description" header="Description" />
  </DataTable>
</template>
