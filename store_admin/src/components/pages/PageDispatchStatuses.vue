<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectDispatchStatusPageFilter,
  type QuerySelectDispatchStatusPageSorter,
  useQuerySelectDispatchStatusPage
} from '../../queries/useQuerySelectDispatchStatusPage';
import SectionHeading from '../partials/SectionHeading.vue';
import GridDispatchStatuses from '../grids/GridDispatchStatuses.vue';

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

const selectDispatchStatusPageFilter = reactive<QuerySelectDispatchStatusPageFilter>({
})

const selectDispatchStatusPageSort = reactive<QuerySelectDispatchStatusPageSorter>({
  id: undefined,
  name: undefined,
  description: undefined,
})

const selectDispatchStatusPageSelection = ref([])

const selectDispatchStatusPageQuery = useQuerySelectDispatchStatusPage(
  props,
  selectDispatchStatusPageSort,
  selectDispatchStatusPageFilter
)

const handleRefreshSelectDispatchStatusPage = () => {
  selectDispatchStatusPageQuery.refresh()
  selectDispatchStatusPageSelection.value = []
}

watch(
  [selectDispatchStatusPageFilter, selectDispatchStatusPageSort],
  handleRefreshSelectDispatchStatusPage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Dispatch Statuses"
    />
    <GridDispatchStatuses
      :state="selectDispatchStatusPageQuery.state"
      v-model:selection="selectDispatchStatusPageSelection"
    />
  </div>
</template>
