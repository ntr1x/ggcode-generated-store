<script setup lang="ts">
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useRoute } from 'vue-router';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { customersRemote } from '../../remotes/customersRemote';
import { paymentsRemote } from '../../remotes/paymentsRemote';
import { eventsRemote } from '../../remotes/eventsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import FieldsetCustomerInfo from '../fieldsets/FieldsetCustomerInfo.vue';
import ToolbarOrders from '../toolbars/ToolbarOrders.vue';
import GridOrders from '../grids/GridOrders.vue';
import GridAgents from '../grids/GridAgents.vue';
import ToolbarSubscriptions from '../toolbars/ToolbarSubscriptions.vue';
import GridSubscriptions from '../grids/GridSubscriptions.vue';
import ToolbarDispatches from '../toolbars/ToolbarDispatches.vue';
import GridDispatches from '../grids/GridDispatches.vue';

const route = useRoute()
const authStore = useAuthStore()

const customerGetFilter = reactive({
})

const customerGetSort = reactive({
})

const customerGetSelection = ref([])

const customerGetQuery = useAxiosRequest<any>(customersRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params = {}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : undefined)

  return {
    method: 'GET',
    url: `/system/public_customer/i/${route.params.customerId}`,
    params,
    data,
    paramsSerializer: {
      indexes: null
    },
    headers: {
      Authorization: `Bearer ${token}`
    }
  }
})

watch(
  [customerGetFilter, customerGetSort],
  () => {
    customerGetQuery.refresh()
    customerGetSelection.value = []
  }
)
const orderSelectFilter = reactive({
  customerId: route.params.customerId,
  orderType: undefined,
  orderStatus: undefined,
  sourceType: undefined,
  regionId: undefined,
})

const orderSelectSort = reactive({
  orderTypeId: undefined,
  orderStatusId: undefined,
})

const orderSelectSelection = ref([])

const orderSelectQuery = useAxiosRequest<any>(paymentsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  setProperty(data, 'customerId', orderSelectFilter.customerId)
  setProperty(data, 'orderTypeId', orderSelectFilter.orderType)
  setProperty(data, 'orderStatusId', orderSelectFilter.orderStatus)
  setProperty(data, 'sourceTypeId', orderSelectFilter.sourceType)
  setProperty(data, 'regionId', orderSelectFilter.regionId)
  const params = {}
  const sort: string[] = []
  if (orderSelectSort.orderTypeId != null) {
    sort.push('orderType,' + orderSelectSort.orderTypeId)
  }
  if (orderSelectSort.orderStatusId != null) {
    sort.push('orderStatus,' + orderSelectSort.orderStatusId)
  }
  setProperty(params, 'sort', sort.length > 0 ? sort : undefined)

  return {
    method: 'POST',
    url: `/system/public_order/select`,
    params,
    data,
    paramsSerializer: {
      indexes: null
    },
    headers: {
      Authorization: `Bearer ${token}`
    }
  }
})

watch(
  [orderSelectFilter, orderSelectSort],
  () => {
    orderSelectQuery.refresh()
    orderSelectSelection.value = []
  }
)
const agentSelectFilter = reactive({
  customerId: route.params.customerId,
})

const agentSelectSort = reactive({
})

const agentSelectSelection = ref([])

const agentSelectQuery = useAxiosRequest<any>(customersRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  setProperty(data, 'customerId', agentSelectFilter.customerId)
  const params = {}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : undefined)

  return {
    method: 'POST',
    url: `/system/public_agent/select`,
    params,
    data,
    paramsSerializer: {
      indexes: null
    },
    headers: {
      Authorization: `Bearer ${token}`
    }
  }
})

watch(
  [agentSelectFilter, agentSelectSort],
  () => {
    agentSelectQuery.refresh()
    agentSelectSelection.value = []
  }
)
const subscriptionSelectFilter = reactive({
  customerId: route.params.customerId,
  typeId: undefined,
})

const subscriptionSelectSort = reactive({
  id: undefined,
  createdAt: undefined,
})

const subscriptionSelectSelection = ref([])

const subscriptionSelectQuery = useAxiosRequest<any>(eventsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  setProperty(data, 'customerId', subscriptionSelectFilter.customerId)
  setProperty(data, 'typeId', subscriptionSelectFilter.typeId)
  const params = {}
  const sort: string[] = []
  if (subscriptionSelectSort.id != null) {
    sort.push('id,' + subscriptionSelectSort.id)
  }
  if (subscriptionSelectSort.createdAt != null) {
    sort.push('createdAt,' + subscriptionSelectSort.createdAt)
  }
  setProperty(params, 'sort', sort.length > 0 ? sort : undefined)

  return {
    method: 'POST',
    url: `/system/public_subscription/select`,
    params,
    data,
    paramsSerializer: {
      indexes: null
    },
    headers: {
      Authorization: `Bearer ${token}`
    }
  }
})

watch(
  [subscriptionSelectFilter, subscriptionSelectSort],
  () => {
    subscriptionSelectQuery.refresh()
    subscriptionSelectSelection.value = []
  }
)
const dispatchSelectFilter = reactive({
  customerId: route.params.customerId,
  typeId: undefined,
  statusId: undefined,
})

const dispatchSelectSort = reactive({
  id: undefined,
  typeId: undefined,
  statusId: undefined,
  createdAt: undefined,
  updatedAt: undefined,
})

const dispatchSelectSelection = ref([])

const dispatchSelectQuery = useAxiosRequest<any>(eventsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  setProperty(data, 'customerId', dispatchSelectFilter.customerId)
  setProperty(data, 'typeId', dispatchSelectFilter.typeId)
  setProperty(data, 'statusId', dispatchSelectFilter.statusId)
  const params = {
  "sort": "created_at,desc"
}
  const sort: string[] = []
  if (dispatchSelectSort.id != null) {
    sort.push('id,' + dispatchSelectSort.id)
  }
  if (dispatchSelectSort.typeId != null) {
    sort.push('typeId,' + dispatchSelectSort.typeId)
  }
  if (dispatchSelectSort.statusId != null) {
    sort.push('statusId,' + dispatchSelectSort.statusId)
  }
  if (dispatchSelectSort.createdAt != null) {
    sort.push('createdAt,' + dispatchSelectSort.createdAt)
  }
  if (dispatchSelectSort.updatedAt != null) {
    sort.push('updatedAt,' + dispatchSelectSort.updatedAt)
  }
  setProperty(params, 'sort', sort.length > 0 ? sort : undefined)

  return {
    method: 'POST',
    url: `/system/public_dispatch/select`,
    params,
    data,
    paramsSerializer: {
      indexes: null
    },
    headers: {
      Authorization: `Bearer ${token}`
    }
  }
})

watch(
  [dispatchSelectFilter, dispatchSelectSort],
  () => {
    dispatchSelectQuery.refresh()
    dispatchSelectSelection.value = []
  }
)
</script>

<template>
  <div class="flex flex-col overflow-auto">
    <SectionHeading tag="h1" title="Customer" />
    <FieldsetCustomerInfo :state="customerGetQuery.state"/>
    <SectionHeading tag="h3" title="Orders" />
    <ToolbarOrders
      class="rounded-none border-0 border-b"
      v-model:selection="orderSelectSelection"
      v-model:filter-by-source-type = orderSelectFilter.sourceType
      v-model:filter-by-order-type = orderSelectFilter.orderType
      v-model:filter-by-order-status = orderSelectFilter.orderStatus
      v-model:filter-by-region-id = orderSelectFilter.regionId
      v-model:sort-by-id = orderSelectSort.id
      v-model:sort-by-customerId = orderSelectSort.customerId
      v-model:sort-by-orderTypeId = orderSelectSort.orderTypeId
      v-model:sort-by-orderStatusId = orderSelectSort.orderStatusId
      v-model:sort-by-createdAt = orderSelectSort.createdAt
      v-model:sort-by-updatedAt = orderSelectSort.updatedAt
    />
    <GridOrders
      :state="orderSelectQuery.state"
      v-model:selection="orderSelectSelection"
      :scrollable="false"
      :hide-customer="true"
    />
    <SectionHeading tag="h3" title="Agents" />
    <GridAgents
      :state="agentSelectQuery.state"
      v-model:selection="agentSelectSelection"
      :scrollable="false"
      :hide-customer="true"
    />
    <SectionHeading tag="h3" title="Subscriptions" />
    <ToolbarSubscriptions
      class="rounded-none border-0 border-b"
      v-model:selection="subscriptionSelectSelection"
      v-model:filter-by-type-id = subscriptionSelectFilter.typeId
      v-model:sort-by-id = subscriptionSelectSort.id
      v-model:sort-by-createdAt = subscriptionSelectSort.createdAt
      v-model:sort-by-typeId = subscriptionSelectSort.typeId
    />
    <GridSubscriptions
      :state="subscriptionSelectQuery.state"
      v-model:selection="subscriptionSelectSelection"
      :scrollable="false"
      :hide-customer="true"
    />
    <SectionHeading tag="h3" title="Dispatches" />
    <ToolbarDispatches
      class="rounded-none border-0 border-b"
      v-model:selection="dispatchSelectSelection"
      v-model:filter-by-type-id = dispatchSelectFilter.typeId
      v-model:filter-by-status-id = dispatchSelectFilter.statusId
      v-model:sort-by-id = dispatchSelectSort.id
      v-model:sort-by-createdAt = dispatchSelectSort.createdAt
      v-model:sort-by-updatedAt = dispatchSelectSort.updatedAt
      v-model:sort-by-typeId = dispatchSelectSort.typeId
      v-model:sort-by-statusId = dispatchSelectSort.statusId
    />
    <GridDispatches
      :state="dispatchSelectQuery.state"
      v-model:selection="dispatchSelectSelection"
      :scrollable="false"
      :hide-customer="true"
    />
  </div>
</template>
