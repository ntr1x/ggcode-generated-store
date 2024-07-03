<script setup lang="ts">
import Panel from 'primevue/panel';
import { type State } from '../../hooks/useAxiosRequest';
import SymbolCustomer from '../symbols/SymbolCustomer.vue'
import SymbolSourceType from '../symbols/SymbolSourceType.vue'
import SymbolOrderType from '../symbols/SymbolOrderType.vue'
import SymbolOrderStatus from '../symbols/SymbolOrderStatus.vue'
import SymbolRegion from '../symbols/SymbolRegion.vue'
import SymbolPrice from '../symbols/SymbolPrice.vue'
import SymbolDatetime from '../symbols/SymbolDatetime.vue'

defineProps<{
  state: State<any>
}>()
</script>

<template>
  <Panel
    header="Order Info"
    pt:header:class="flex items-center justify-between text-surface-700 dark:text-surface-0/80 bg-surface-50 dark:bg-surface-900 border-b border-surface-200 dark:border-surface-700 rounded-tr-lg p-5"
    pt:content:class="p-5 border-b border-surface-200 dark:border-surface-700 bg-surface-0 dark:bg-surface-900 text-surface-700 dark:text-surface-0/80"
  >
    <table class="table-auto border-separate border-spacing-x-3" v-if="state.isLoaded && state.data != null">
    <tr>
      <th class="text-left">Customer:</th>
      <td>
        <SymbolCustomer :value="state.data.customer" />
      </td>
    </tr>
    <tr>
      <th class="text-left">Source Type:</th>
      <td>
        <SymbolSourceType :value="state.data.sourceType" />
      </td>
    </tr>
    <tr>
      <th class="text-left">Order Type:</th>
      <td>
        <SymbolOrderType :value="state.data.orderType" />
      </td>
    </tr>
    <tr>
      <th class="text-left">Order Status:</th>
      <td>
        <SymbolOrderStatus :value="state.data.orderStatus" />
      </td>
    </tr>
    <tr>
      <th class="text-left">Region:</th>
      <td>
        <SymbolRegion :value="state.data.region" />
      </td>
    </tr>
    <tr>
      <th class="text-left">Price:</th>
      <td>
        <SymbolPrice :value="state.data.price" />
      </td>
    </tr>
    <tr>
      <th class="text-left">Discount:</th>
      <td>
        <SymbolPrice :value="state.data.discount" />
      </td>
    </tr>
    <tr>
      <th class="text-left">Spend:</th>
      <td>
        <SymbolPrice :value="state.data.spend" />
      </td>
    </tr>
    <tr>
      <th class="text-left">Bonus:</th>
      <td>
        <SymbolPrice :value="state.data.bonus" />
      </td>
    </tr>
    <tr>
      <th class="text-left">Created At:</th>
      <td>
        <SymbolDatetime :value="state.data.createdAt" />
      </td>
    </tr>
    <tr>
      <th class="text-left">Updated At:</th>
      <td>
        <SymbolDatetime :value="state.data.updatedAt" />
      </td>
    </tr>
    </table>
  </Panel>
</template>
