<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import { paymentsRemote } from '../../remotes/paymentsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import FieldsetOrderInfo from '../fieldsets/FieldsetOrderInfo.vue';
import ToolbarPayments from '../toolbars/ToolbarPayments.vue';
import GridPayments from '../grids/GridPayments.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()
// @ts-ignore
const security = useSecurityContext()

const orderGetFilter = reactive({
})

const orderGetSort = reactive({
})

const orderGetSelection = ref([])

const orderGetQuery = useAxiosAutoRequest<any>(paymentsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params: Record<string, any> = {}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

  return {
    method: 'GET',
    url: `/system/public_order/i/${route.params.orderId}`,
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

const handleRefreshOrderGet = () => {
  orderGetQuery.refresh()
  orderGetSelection.value = []
}

watch(
  [orderGetFilter, orderGetSort],
  handleRefreshOrderGet
)
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

const paymentSelectQuery = useAxiosAutoRequest<any>(paymentsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  setProperty(data, 'orderId', paymentSelectFilter.orderId)
  setProperty(data, 'customerId', paymentSelectFilter.customerId)
  setProperty(data, 'paymentTypeId', paymentSelectFilter.paymentType)
  setProperty(data, 'paymentStatusId', paymentSelectFilter.paymentStatus)
  setProperty(data, 'order.orderTypeId', paymentSelectFilter.orderType)
  setProperty(data, 'order.orderStatusId', paymentSelectFilter.orderStatus)
  const params: Record<string, any> = {"size":50,"sort":"id,asc"}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

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

const handleRefreshPaymentSelect = () => {
  paymentSelectQuery.refresh()
  paymentSelectSelection.value = []
}

watch(
  [paymentSelectFilter, paymentSelectSort],
  handleRefreshPaymentSelect
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-auto">
    <SectionHeading
      tag="h1"
      title="Order"
    />
    <FieldsetOrderInfo
      :state="orderGetQuery.state"
    />
    <SectionHeading
      tag="h3"
      title="Payments"
    />
    <ToolbarPayments
      class="rounded-none border-0 border-b"
      v-model:selection="paymentSelectSelection"
      v-model:filter-by-payment-type = paymentSelectFilter.paymentType
      v-model:filter-by-payment-status = paymentSelectFilter.paymentStatus
      v-model:filter-by-order-type = paymentSelectFilter.orderType
      v-model:filter-by-order-status = paymentSelectFilter.orderStatus
      @refresh="handleRefreshPaymentSelect"
    />
    <GridPayments
      :state="paymentSelectQuery.state"
      v-model:selection="paymentSelectSelection"
      :scrollable="false"
      :hide-order="true"
    />
  </div>
</template>
