<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectPaymentPageFilter,
  type QuerySelectPaymentPageSorter,
  useQuerySelectPaymentPage
} from '../../queries/useQuerySelectPaymentPage';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarPayments from '../toolbars/ToolbarPayments.vue';
import GridPayments from '../grids/GridPayments.vue';

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

const selectPaymentPageFilter = reactive<QuerySelectPaymentPageFilter>({
  orderId: undefined,
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
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
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
    />
  </div>
</template>
