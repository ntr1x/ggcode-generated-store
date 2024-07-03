<script setup lang="ts">
import Panel from 'primevue/panel';
import { type State } from '../../hooks/useAxiosRequest';

defineProps<{
  state: State<any>
}>()
</script>

<template>
  <Panel
    header="Customer Info"
    pt:header:class="flex items-center justify-between text-surface-700 dark:text-surface-0/80 bg-surface-50 dark:bg-surface-900 border-b border-surface-200 dark:border-surface-700 rounded-tr-lg p-5"
    pt:content:class="p-5 border-b border-surface-200 dark:border-surface-700 bg-surface-0 dark:bg-surface-900 text-surface-700 dark:text-surface-0/80"
  >
    <table class="table-auto border-separate border-spacing-x-3" v-if="state.isLoaded && state.data != null">
    <tr>
      <th class="text-left">Name:</th>
      <td>
        <div v-if="state.data.name" v-html="state.data.name"></div>
        <div v-else><i class="text-slate-500">Empty</i></div>
      </td>
    </tr>
    <tr>
      <th class="text-left">Patronymic:</th>
      <td>
        <div v-if="state.data.patronymic" v-html="state.data.patronymic"></div>
        <div v-else><i class="text-slate-500">Empty</i></div>
      </td>
    </tr>
    <tr>
      <th class="text-left">Surname:</th>
      <td>
        <div v-if="state.data.surname" v-html="state.data.surname"></div>
        <div v-else><i class="text-slate-500">Empty</i></div>
      </td>
    </tr>
    <tr>
      <th class="text-left">Email:</th>
      <td>
        <div v-if="state.data.email" v-html="state.data.email"></div>
        <div v-else><i class="text-slate-500">Empty</i></div>
      </td>
    </tr>
    <tr>
      <th class="text-left">Phone:</th>
      <td>
        <div v-if="state.data.phone" v-html="state.data.phone"></div>
        <div v-else><i class="text-slate-500">Empty</i></div>
      </td>
    </tr>
    </table>
  </Panel>
</template>
