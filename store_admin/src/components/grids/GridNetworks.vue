<script setup lang="ts">
import Column from 'primevue/column';
import DataTable from 'primevue/datatable';
import { type State } from '../../hooks/useAxiosRequest';
import SymbolId from '../symbols/SymbolId.vue'
import SymbolNetworkType from '../symbols/SymbolNetworkType.vue'
import SymbolShop from '../symbols/SymbolShop.vue'
import { StructurePublicNetworkPage } from '../../structures/StructurePublicNetworkPage'

export type GridNetworksRecord = StructurePublicNetworkPage['content'][0]

export type GridNetworksPage = {
  content: GridNetworksRecord[]
}

export type GridNetworksProps = {
  state: State<GridNetworksPage>,
  scrollable?: boolean,
  scrollHeight?: string,
  hideId?: boolean,
  hideType?: boolean,
  hideShop?: boolean,
  hideHidden?: boolean,
  hideName?: boolean,
}

const selection = defineModel<GridNetworksRecord[]>('selection')

withDefaults(defineProps<GridNetworksProps>(), {
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
        <SymbolId :value="slotProps.data.id" :label="'REF'" :route="`/admin/networks/${slotProps.data.id}`"/>
      </template>
    </Column>
    <Column v-if="!hideType" header="Type">
      <template #body="slotProps">
        <SymbolNetworkType :value="slotProps.data.type"/>
      </template>
    </Column>
    <Column v-if="!hideShop" header="Shop">
      <template #body="slotProps">
        <SymbolShop :value="slotProps.data.shop"/>
      </template>
    </Column>
    <Column v-if="!hideHidden" field="hidden" header="Hidden" />
    <Column v-if="!hideName" field="name" header="Name" />
  </DataTable>
</template>
