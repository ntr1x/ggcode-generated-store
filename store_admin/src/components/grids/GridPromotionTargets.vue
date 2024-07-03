<script setup lang="ts" generic="T">
import Column from 'primevue/column';
import DataTable from 'primevue/datatable';
import { type State } from '../../hooks/useAxiosRequest';
import SymbolId from '../symbols/SymbolId.vue'
import SymbolPromotionType from '../symbols/SymbolPromotionType.vue'

export type ResponseData = {
  content: []
}

export type GridPromotionTargetsProps = {
  state: State<ResponseData>,
  scrollable?: boolean,
  scrollHeight?: string,
  hideId?: boolean,
  hidePromotionType?: boolean,
  hidePromotion?: boolean,
}

const selection = defineModel<T[]>('selection')

withDefaults(defineProps<GridPromotionTargetsProps>(), {
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
        <SymbolId :value="slotProps.data.id" :label="'REF'" :route="`/admin/promotion_target/${slotProps.data.id}`"/>
      </template>
    </Column>
    <Column v-if="!hidePromotionType" header="Promotion Type">
      <template #body="slotProps">
        <SymbolPromotionType :value="slotProps.data.promotion.type"/>
      </template>
    </Column>
    <Column v-if="!hidePromotion" field="promotion.name" header="Promotion" />
  </DataTable>
</template>
