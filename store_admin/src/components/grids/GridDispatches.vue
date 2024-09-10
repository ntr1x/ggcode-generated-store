<script setup lang="ts">
import Column from 'primevue/column';
import DataTable from 'primevue/datatable';
import { type State } from '../../hooks/useAxiosRequest';
import SymbolId from '../symbols/SymbolId.vue'
import SymbolCustomer from '../symbols/SymbolCustomer.vue'
import SymbolDispatchType from '../symbols/SymbolDispatchType.vue'
import SymbolDispatchStatus from '../symbols/SymbolDispatchStatus.vue'
import SymbolSessionId from '../symbols/SymbolSessionId.vue'
import SymbolDatetime from '../symbols/SymbolDatetime.vue'
import { StructurePublicDispatchPage } from '../../structures/StructurePublicDispatchPage'

export type GridDispatchesRecord = StructurePublicDispatchPage['content'][0]

export type GridDispatchesPage = {
  content: GridDispatchesRecord[]
}

export type GridDispatchesProps = {
  state: State<GridDispatchesPage>,
  scrollable?: boolean,
  scrollHeight?: string,
  hideId?: boolean,
  hideCustomer?: boolean,
  hideType?: boolean,
  hideStatus?: boolean,
  hideSessionId?: boolean,
  hideCreatedAt?: boolean,
  hideUpdatedAt?: boolean,
}

const selection = defineModel<GridDispatchesRecord[]>('selection')

withDefaults(defineProps<GridDispatchesProps>(), {
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
        <SymbolId :value="slotProps.data.id" :label="'REF'" :route="`/admin/dispatches/${slotProps.data.id}`"/>
      </template>
    </Column>
    <Column v-if="!hideCustomer" header="Customer">
      <template #body="slotProps">
        <SymbolCustomer :value="slotProps.data.customer"/>
      </template>
    </Column>
    <Column v-if="!hideType" header="Type">
      <template #body="slotProps">
        <SymbolDispatchType :value="slotProps.data.type"/>
      </template>
    </Column>
    <Column v-if="!hideStatus" header="Status">
      <template #body="slotProps">
        <SymbolDispatchStatus :value="slotProps.data.status"/>
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
    <Column v-if="!hideUpdatedAt" header="Updated At">
      <template #body="slotProps">
        <SymbolDatetime :value="slotProps.data.updatedAt"/>
      </template>
    </Column>
  </DataTable>
</template>
