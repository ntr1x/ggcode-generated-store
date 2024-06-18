<script setup lang="ts">
import Panel from 'primevue/panel';
import { type State } from '../../hooks/useAxiosRequest';
import SymbolCustomer from '../symbols/SymbolCustomer.vue'

export type ResponseData = {
  content: any
}

defineProps<{
  state: State<ResponseData>
}>()
</script>

<template>
  <Panel
    header="Agent Info"
    pt:header:class="flex items-center justify-between text-surface-700 dark:text-surface-0/80 bg-surface-50 dark:bg-surface-900 border-b border-surface-200 dark:border-surface-700 rounded-tr-lg p-5"
    pt:content:class="p-5 border-b border-surface-200 dark:border-surface-700 bg-surface-0 dark:bg-surface-900 text-surface-700 dark:text-surface-0/80"
  >
    <table class="table-auto border-separate border-spacing-x-3" v-if="state.isLoaded">
    <tr>
      <th class="text-left">Title:</th>
      <td>
        <div v-if="state.data.title" v-html="state.data.title"></div>
        <div v-else><i class="text-slate-500">Empty</i></div>
      </td>
    </tr>
    <tr>
      <th class="text-left">Customer:</th>
      <td>
        <SymbolCustomer :value="state.data.customer" />
      </td>
    </tr>
    </table>
  </Panel>
</template>
