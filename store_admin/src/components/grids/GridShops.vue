<script setup lang="ts">
import Column from 'primevue/column';
import DataTable from 'primevue/datatable';
import { type State } from '../../hooks/useAxiosRequest';
import SymbolId from '../symbols/SymbolId.vue'

export type GridShopsRecord = Record<string, any>

export type GridShopsPage = {
  content: GridShopsRecord[]
}

export type GridShopsProps = {
  state: State<GridShopsPage>,
  scrollable?: boolean,
  scrollHeight?: string,
  hideId?: boolean,
  hideName?: boolean,
  hideRegion?: boolean,
  hideDescription?: boolean,
}

const selection = defineModel<GridShopsRecord[]>('selection')

withDefaults(defineProps<GridShopsProps>(), {
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
        <SymbolId :value="slotProps.data.id" :label="'REF'" :route="`/admin/shops/${slotProps.data.id}`"/>
      </template>
    </Column>
    <Column v-if="!hideName" field="name" header="Name" />
    <Column v-if="!hideRegion" field="region.name" header="Region" />
    <Column v-if="!hideDescription" field="description" header="Description" />
  </DataTable>
</template>
