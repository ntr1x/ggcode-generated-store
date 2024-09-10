<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectOrderStatusPageFilter,
  type QuerySelectOrderStatusPageSorter,
  useQuerySelectOrderStatusPage
} from '../../queries/useQuerySelectOrderStatusPage';
import SectionHeading from '../partials/SectionHeading.vue';
import GridOrderStatuses from '../grids/GridOrderStatuses.vue';

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

const selectOrderStatusPageFilter = reactive<QuerySelectOrderStatusPageFilter>({
})

const selectOrderStatusPageSort = reactive<QuerySelectOrderStatusPageSorter>({
})

const selectOrderStatusPageSelection = ref([])

const selectOrderStatusPageQuery = useQuerySelectOrderStatusPage(
  props,
  selectOrderStatusPageSort,
  selectOrderStatusPageFilter
)

const handleRefreshSelectOrderStatusPage = () => {
  selectOrderStatusPageQuery.refresh()
  selectOrderStatusPageSelection.value = []
}

watch(
  [selectOrderStatusPageFilter, selectOrderStatusPageSort],
  handleRefreshSelectOrderStatusPage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Order Statuses"
    />
    <GridOrderStatuses
      :state="selectOrderStatusPageQuery.state"
      v-model:selection="selectOrderStatusPageSelection"
      @refresh="handleRefreshSelectOrderStatusPage"
    />
  </div>
</template>
