<script setup lang="ts">
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useRoute } from 'vue-router';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { paymentsRemote } from '../../remotes/paymentsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarPayments from '../toolbars/ToolbarPayments.vue';
import GridPayments from '../grids/GridPayments.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()

const paymentSelectFilter = reactive({
  orderId: route.params.orderId,
  customerId: route.params.customerId,
  paymentType: undefined,
  paymentStatus: undefined,
  orderType: undefined,
  orderStatus: undefined,
})

const paymentSelectSort = reactive({
})

const paymentSelectSelection = ref([])

const paymentSelectQuery = useAxiosRequest<any>(paymentsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  setProperty(data, 'orderId', paymentSelectFilter.orderId)
  setProperty(data, 'customerId', paymentSelectFilter.customerId)
  setProperty(data, 'paymentTypeId', paymentSelectFilter.paymentType)
  setProperty(data, 'paymentStatusId', paymentSelectFilter.paymentStatus)
  setProperty(data, 'order.orderTypeId', paymentSelectFilter.orderType)
  setProperty(data, 'order.orderStatusId', paymentSelectFilter.orderStatus)
  const params = {
  "size": 50,
  "sort": "id,asc"
}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : undefined)

  return {
    method: 'POST',
    url: `/system/public_payment/select`,
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
  [paymentSelectFilter, paymentSelectSort],
  () => {
    paymentSelectQuery.refresh()
    paymentSelectSelection.value = []
  }
)
</script>

<template>
  <div class="flex flex-col overflow-hidden">
    <SectionHeading tag="h1" title="Payments" />
    <ToolbarPayments
      class="rounded-none border-0 border-b"
      v-model:selection="paymentSelectSelection"
      v-model:filter-by-payment-type = paymentSelectFilter.paymentType
      v-model:filter-by-payment-status = paymentSelectFilter.paymentStatus
      v-model:filter-by-order-type = paymentSelectFilter.orderType
      v-model:filter-by-order-status = paymentSelectFilter.orderStatus
    />
    <GridPayments
      :state="paymentSelectQuery.state"
      v-model:selection="paymentSelectSelection"
    />
  </div>
</template>
