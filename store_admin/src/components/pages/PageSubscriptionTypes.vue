<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectSubscriptionTypePageFilter,
  type QuerySelectSubscriptionTypePageSorter,
  useQuerySelectSubscriptionTypePage
} from '../../queries/useQuerySelectSubscriptionTypePage';
import SectionHeading from '../partials/SectionHeading.vue';
import GridSubscriptionTypes from '../grids/GridSubscriptionTypes.vue';

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

const selectSubscriptionTypePageFilter = reactive<QuerySelectSubscriptionTypePageFilter>({
  customerId: undefined,
  typeId: undefined,
})

const selectSubscriptionTypePageSort = reactive<QuerySelectSubscriptionTypePageSorter>({
  id: undefined,
  name: undefined,
  description: undefined,
})

const selectSubscriptionTypePageSelection = ref([])

const selectSubscriptionTypePageQuery = useQuerySelectSubscriptionTypePage(
  props,
  selectSubscriptionTypePageSort,
  selectSubscriptionTypePageFilter
)

const handleRefreshSelectSubscriptionTypePage = () => {
  selectSubscriptionTypePageQuery.refresh()
  selectSubscriptionTypePageSelection.value = []
}

watch(
  [selectSubscriptionTypePageFilter, selectSubscriptionTypePageSort],
  handleRefreshSelectSubscriptionTypePage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Subscription Types"
    />
    <GridSubscriptionTypes
      :state="selectSubscriptionTypePageQuery.state"
      v-model:selection="selectSubscriptionTypePageSelection"
      @refresh="handleRefreshSelectSubscriptionTypePage"
    />
  </div>
</template>
