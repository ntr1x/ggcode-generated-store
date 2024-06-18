<script setup lang="ts">
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useRoute } from 'vue-router';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { paymentsRemote } from '../../remotes/paymentsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarOrders from '../toolbars/ToolbarOrders.vue';
import GridOrders from '../grids/GridOrders.vue';

const route = useRoute()
const authStore = useAuthStore()

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
</script>

<template>
  <div class="flex flex-col overflow-hidden">
    <SectionHeading tag="h1" title="Orders" />
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
    />
  </div>
</template>
