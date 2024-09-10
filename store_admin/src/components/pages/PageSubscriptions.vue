<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectSubscriptionPageFilter,
  type QuerySelectSubscriptionPageSorter,
  useQuerySelectSubscriptionPage
} from '../../queries/useQuerySelectSubscriptionPage';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarSubscriptions from '../toolbars/ToolbarSubscriptions.vue';
import GridSubscriptions from '../grids/GridSubscriptions.vue';

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

const selectSubscriptionPageFilter = reactive<QuerySelectSubscriptionPageFilter>({
  customerId: undefined,
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

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
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
      @refresh="handleRefreshSelectSubscriptionPage"
    />
  </div>
</template>
