<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QueryGetCustomerRecordFilter,
  type QueryGetCustomerRecordSorter,
  useQueryGetCustomerRecord
} from '../../queries/useQueryGetCustomerRecord';
import {
  type QuerySelectOrderPageFilter,
  type QuerySelectOrderPageSorter,
  useQuerySelectOrderPage
} from '../../queries/useQuerySelectOrderPage';
import {
  type QuerySelectAgentPageFilter,
  type QuerySelectAgentPageSorter,
  useQuerySelectAgentPage
} from '../../queries/useQuerySelectAgentPage';
import {
  type QuerySelectSubscriptionPageFilter,
  type QuerySelectSubscriptionPageSorter,
  useQuerySelectSubscriptionPage
} from '../../queries/useQuerySelectSubscriptionPage';
import {
  type QuerySelectDispatchPageFilter,
  type QuerySelectDispatchPageSorter,
  useQuerySelectDispatchPage
} from '../../queries/useQuerySelectDispatchPage';
import SectionHeading from '../partials/SectionHeading.vue';
import FieldsetCustomerInfo from '../fieldsets/FieldsetCustomerInfo.vue';
import ToolbarOrders from '../toolbars/ToolbarOrders.vue';
import GridOrders from '../grids/GridOrders.vue';
import GridAgents from '../grids/GridAgents.vue';
import ToolbarSubscriptions from '../toolbars/ToolbarSubscriptions.vue';
import GridSubscriptions from '../grids/GridSubscriptions.vue';
import ToolbarDispatches from '../toolbars/ToolbarDispatches.vue';
import GridDispatches from '../grids/GridDispatches.vue';

const props = defineProps<{
  customerId: string
}>()

onMounted(() => {
  console.trace(props)
})

// @ts-ignore
const authStore = useAuthStore()

// @ts-ignore
const security = useSecurityContext()

const getCustomerRecordFilter = reactive<QueryGetCustomerRecordFilter>({
})

const getCustomerRecordSort = reactive<QueryGetCustomerRecordSorter>({
})

const getCustomerRecordSelection = ref([])

const getCustomerRecordQuery = useQueryGetCustomerRecord(
  props,
  getCustomerRecordSort,
  getCustomerRecordFilter
)

const handleRefreshGetCustomerRecord = () => {
  getCustomerRecordQuery.refresh()
  getCustomerRecordSelection.value = []
}

watch(
  [getCustomerRecordFilter, getCustomerRecordSort],
  handleRefreshGetCustomerRecord
)

const selectOrderPageFilter = reactive<QuerySelectOrderPageFilter>({
  customerId: props.customerId,
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

const selectAgentPageFilter = reactive<QuerySelectAgentPageFilter>({
  customerId: props.customerId,
})

const selectAgentPageSort = reactive<QuerySelectAgentPageSorter>({
})

const selectAgentPageSelection = ref([])

const selectAgentPageQuery = useQuerySelectAgentPage(
  props,
  selectAgentPageSort,
  selectAgentPageFilter
)

const handleRefreshSelectAgentPage = () => {
  selectAgentPageQuery.refresh()
  selectAgentPageSelection.value = []
}

watch(
  [selectAgentPageFilter, selectAgentPageSort],
  handleRefreshSelectAgentPage
)

const selectSubscriptionPageFilter = reactive<QuerySelectSubscriptionPageFilter>({
  customerId: props.customerId,
  typeId: undefined,
})

const selectSubscriptionPageSort = reactive<QuerySelectSubscriptionPageSorter>({
  id: undefined,
  createdAt: undefined,
  typeId: undefined,
})

const selectSubscriptionPageSelection = ref([])

const selectSubscriptionPageQuery = useQuerySelectSubscriptionPage(
  props,
  selectSubscriptionPageSort,
  selectSubscriptionPageFilter
)

const handleRefreshSelectSubscriptionPage = () => {
  selectSubscriptionPageQuery.refresh()
  selectSubscriptionPageSelection.value = []
}

watch(
  [selectSubscriptionPageFilter, selectSubscriptionPageSort],
  handleRefreshSelectSubscriptionPage
)

const selectDispatchPageFilter = reactive<QuerySelectDispatchPageFilter>({
  customerId: props.customerId,
  typeId: undefined,
  statusId: undefined,
})

const selectDispatchPageSort = reactive<QuerySelectDispatchPageSorter>({
  id: undefined,
  typeId: undefined,
  statusId: undefined,
  createdAt: undefined,
  updatedAt: undefined,
})

const selectDispatchPageSelection = ref([])

const selectDispatchPageQuery = useQuerySelectDispatchPage(
  props,
  selectDispatchPageSort,
  selectDispatchPageFilter
)

const handleRefreshSelectDispatchPage = () => {
  selectDispatchPageQuery.refresh()
  selectDispatchPageSelection.value = []
}

watch(
  [selectDispatchPageFilter, selectDispatchPageSort],
  handleRefreshSelectDispatchPage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-auto">
    <SectionHeading
      tag="h1"
      title="Customer"
    />
    <FieldsetCustomerInfo
      :state="getCustomerRecordQuery.state"
    />
    <SectionHeading
      tag="h3"
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
      :scrollable="false"
      :hide-customer="true"
      @refresh="handleRefreshSelectOrderPage"
    />
    <SectionHeading
      tag="h3"
      title="Agents"
    />
    <GridAgents
      :state="selectAgentPageQuery.state"
      v-model:selection="selectAgentPageSelection"
      :scrollable="false"
      :hide-customer="true"
      @refresh="handleRefreshSelectAgentPage"
    />
    <SectionHeading
      tag="h3"
      title="Subscriptions"
    />
    <ToolbarSubscriptions
      class="rounded-none border-0 border-b"
      v-model:selection="selectSubscriptionPageSelection"
      v-model:filter-by-customer-id = selectSubscriptionPageFilter.customerId
      v-model:filter-by-type-id = selectSubscriptionPageFilter.typeId
      v-model:sort-by-id = selectSubscriptionPageSort.id
      v-model:sort-by-created-at = selectSubscriptionPageSort.createdAt
      v-model:sort-by-type-id = selectSubscriptionPageSort.typeId
      @refresh="handleRefreshSelectSubscriptionPage"
    />
    <GridSubscriptions
      :state="selectSubscriptionPageQuery.state"
      v-model:selection="selectSubscriptionPageSelection"
      :scrollable="false"
      :hide-customer="true"
      @refresh="handleRefreshSelectSubscriptionPage"
    />
    <SectionHeading
      tag="h3"
      title="Dispatches"
    />
    <ToolbarDispatches
      class="rounded-none border-0 border-b"
      v-model:selection="selectDispatchPageSelection"
      v-model:filter-by-customer-id = selectDispatchPageFilter.customerId
      v-model:filter-by-type-id = selectDispatchPageFilter.typeId
      v-model:filter-by-status-id = selectDispatchPageFilter.statusId
      v-model:sort-by-id = selectDispatchPageSort.id
      v-model:sort-by-created-at = selectDispatchPageSort.createdAt
      v-model:sort-by-updated-at = selectDispatchPageSort.updatedAt
      v-model:sort-by-type-id = selectDispatchPageSort.typeId
      v-model:sort-by-status-id = selectDispatchPageSort.statusId
      @refresh="handleRefreshSelectDispatchPage"
    />
    <GridDispatches
      :state="selectDispatchPageQuery.state"
      v-model:selection="selectDispatchPageSelection"
      :scrollable="false"
      :hide-customer="true"
      @refresh="handleRefreshSelectDispatchPage"
    />
  </div>
</template>
