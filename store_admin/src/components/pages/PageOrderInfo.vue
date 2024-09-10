<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QueryGetOrderRecordFilter,
  type QueryGetOrderRecordSorter,
  useQueryGetOrderRecord
} from '../../queries/useQueryGetOrderRecord';
import {
  type QuerySelectPaymentPageFilter,
  type QuerySelectPaymentPageSorter,
  useQuerySelectPaymentPage
} from '../../queries/useQuerySelectPaymentPage';
import SectionHeading from '../partials/SectionHeading.vue';
import FieldsetOrderInfo from '../fieldsets/FieldsetOrderInfo.vue';
import ToolbarPayments from '../toolbars/ToolbarPayments.vue';
import GridPayments from '../grids/GridPayments.vue';

const props = defineProps<{
  orderId: string
}>()

onMounted(() => {
  console.trace(props)
})

// @ts-ignore
const authStore = useAuthStore()

// @ts-ignore
const security = useSecurityContext()

const getOrderRecordFilter = reactive<QueryGetOrderRecordFilter>({
})

const getOrderRecordSort = reactive<QueryGetOrderRecordSorter>({
})

const getOrderRecordSelection = ref([])

const getOrderRecordQuery = useQueryGetOrderRecord(
  props,
  getOrderRecordSort,
  getOrderRecordFilter
)

const handleRefreshGetOrderRecord = () => {
  getOrderRecordQuery.refresh()
  getOrderRecordSelection.value = []
}

watch(
  [getOrderRecordFilter, getOrderRecordSort],
  handleRefreshGetOrderRecord
)

const selectPaymentPageFilter = reactive<QuerySelectPaymentPageFilter>({
  orderId: props.orderId,
  customerId: undefined,
  paymentType: undefined,
  paymentStatus: undefined,
  orderType: undefined,
  orderStatus: undefined,
})

const selectPaymentPageSort = reactive<QuerySelectPaymentPageSorter>({
})

const selectPaymentPageSelection = ref([])

const selectPaymentPageQuery = useQuerySelectPaymentPage(
  props,
  selectPaymentPageSort,
  selectPaymentPageFilter
)

const handleRefreshSelectPaymentPage = () => {
  selectPaymentPageQuery.refresh()
  selectPaymentPageSelection.value = []
}

watch(
  [selectPaymentPageFilter, selectPaymentPageSort],
  handleRefreshSelectPaymentPage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-auto">
    <SectionHeading
      tag="h1"
      title="Order"
    />
    <FieldsetOrderInfo
      :state="getOrderRecordQuery.state"
    />
    <SectionHeading
      tag="h3"
      title="Payments"
    />
    <ToolbarPayments
      class="rounded-none border-0 border-b"
      v-model:selection="selectPaymentPageSelection"
      v-model:filter-by-payment-type = selectPaymentPageFilter.paymentType
      v-model:filter-by-payment-status = selectPaymentPageFilter.paymentStatus
      v-model:filter-by-order-type = selectPaymentPageFilter.orderType
      v-model:filter-by-order-status = selectPaymentPageFilter.orderStatus
      @refresh="handleRefreshSelectPaymentPage"
    />
    <GridPayments
      :state="selectPaymentPageQuery.state"
      v-model:selection="selectPaymentPageSelection"
      :scrollable="false"
      :hide-order="true"
      @refresh="handleRefreshSelectPaymentPage"
    />
  </div>
</template>
