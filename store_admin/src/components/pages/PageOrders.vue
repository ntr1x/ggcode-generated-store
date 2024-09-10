<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectOrderPageFilter,
  type QuerySelectOrderPageSorter,
  useQuerySelectOrderPage
} from '../../queries/useQuerySelectOrderPage';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarOrders from '../toolbars/ToolbarOrders.vue';
import GridOrders from '../grids/GridOrders.vue';

const props = defineProps<{
  // yet nothing
}>()

onMounted(() => {
  console.trace(props)
})

// @ts-ignore
const authStore = useAuthStore()

// @ts-ignore
const security = useSecurityContext()

const selectOrderPageFilter = reactive<QuerySelectOrderPageFilter>({
  customerId: undefined,
  orderType: undefined,
  orderStatus: undefined,
  sourceType: undefined,
  regionId: undefined,
})

const selectOrderPageSort = reactive<QuerySelectOrderPageSorter>({
  orderTypeId: undefined,
  orderStatusId: undefined,
  id: undefined,
  customerId: undefined,
  createdAt: undefined,
  updatedAt: undefined,
})

const selectOrderPageSelection = ref([])

const selectOrderPageQuery = useQuerySelectOrderPage(
  props,
  selectOrderPageSort,
  selectOrderPageFilter
)

const handleRefreshSelectOrderPage = () => {
  selectOrderPageQuery.refresh()
  selectOrderPageSelection.value = []
}

watch(
  [selectOrderPageFilter, selectOrderPageSort],
  handleRefreshSelectOrderPage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Orders"
    />
    <ToolbarOrders
      class="rounded-none border-0 border-b"
      v-model:selection="selectOrderPageSelection"
      v-model:filter-by-customer-id = selectOrderPageFilter.customerId
      v-model:filter-by-source-type = selectOrderPageFilter.sourceType
      v-model:filter-by-order-type = selectOrderPageFilter.orderType
      v-model:filter-by-order-status = selectOrderPageFilter.orderStatus
      v-model:filter-by-region-id = selectOrderPageFilter.regionId
      v-model:sort-by-id = selectOrderPageSort.id
      v-model:sort-by-customer-id = selectOrderPageSort.customerId
      v-model:sort-by-order-type-id = selectOrderPageSort.orderTypeId
      v-model:sort-by-order-status-id = selectOrderPageSort.orderStatusId
      v-model:sort-by-created-at = selectOrderPageSort.createdAt
      v-model:sort-by-updated-at = selectOrderPageSort.updatedAt
      @refresh="handleRefreshSelectOrderPage"
    />
    <GridOrders
      :state="selectOrderPageQuery.state"
      v-model:selection="selectOrderPageSelection"
      @refresh="handleRefreshSelectOrderPage"
    />
  </div>
</template>
