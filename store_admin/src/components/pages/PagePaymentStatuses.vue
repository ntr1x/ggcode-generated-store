<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectPaymentStatusPageFilter,
  type QuerySelectPaymentStatusPageSorter,
  useQuerySelectPaymentStatusPage
} from '../../queries/useQuerySelectPaymentStatusPage';
import SectionHeading from '../partials/SectionHeading.vue';
import GridPaymentStatuses from '../grids/GridPaymentStatuses.vue';

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

const selectPaymentStatusPageFilter = reactive<QuerySelectPaymentStatusPageFilter>({
})

const selectPaymentStatusPageSort = reactive<QuerySelectPaymentStatusPageSorter>({
})

const selectPaymentStatusPageSelection = ref([])

const selectPaymentStatusPageQuery = useQuerySelectPaymentStatusPage(
  props,
  selectPaymentStatusPageSort,
  selectPaymentStatusPageFilter
)

const handleRefreshSelectPaymentStatusPage = () => {
  selectPaymentStatusPageQuery.refresh()
  selectPaymentStatusPageSelection.value = []
}

watch(
  [selectPaymentStatusPageFilter, selectPaymentStatusPageSort],
  handleRefreshSelectPaymentStatusPage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Payment Statuses"
    />
    <GridPaymentStatuses
      :state="selectPaymentStatusPageQuery.state"
      v-model:selection="selectPaymentStatusPageSelection"
    />
  </div>
</template>
