<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectDispatchPageFilter,
  type QuerySelectDispatchPageSorter,
  useQuerySelectDispatchPage
} from '../../queries/useQuerySelectDispatchPage';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarDispatches from '../toolbars/ToolbarDispatches.vue';
import GridDispatches from '../grids/GridDispatches.vue';

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

const selectDispatchPageFilter = reactive<QuerySelectDispatchPageFilter>({
  customerId: undefined,
  typeId: undefined,
  statusId: undefined,
})

const selectDispatchPageSort = reactive<QuerySelectDispatchPageSorter>({
  id: undefined,
  typeId: undefined,
  statusId: undefined,
  createdAt: undefined,
  updatedAt: undefined,
})

const selectDispatchPageSelection = ref([])

const selectDispatchPageQuery = useQuerySelectDispatchPage(
  props,
  selectDispatchPageSort,
  selectDispatchPageFilter
)

const handleRefreshSelectDispatchPage = () => {
  selectDispatchPageQuery.refresh()
  selectDispatchPageSelection.value = []
}

watch(
  [selectDispatchPageFilter, selectDispatchPageSort],
  handleRefreshSelectDispatchPage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Dispatches"
    />
    <ToolbarDispatches
      class="rounded-none border-0 border-b"
      v-model:selection="selectDispatchPageSelection"
      v-model:filter-by-customer-id = selectDispatchPageFilter.customerId
      v-model:filter-by-type-id = selectDispatchPageFilter.typeId
      v-model:filter-by-status-id = selectDispatchPageFilter.statusId
      v-model:sort-by-id = selectDispatchPageSort.id
      v-model:sort-by-created-at = selectDispatchPageSort.createdAt
      v-model:sort-by-updated-at = selectDispatchPageSort.updatedAt
      v-model:sort-by-type-id = selectDispatchPageSort.typeId
      v-model:sort-by-status-id = selectDispatchPageSort.statusId
      @refresh="handleRefreshSelectDispatchPage"
    />
    <GridDispatches
      :state="selectDispatchPageQuery.state"
      v-model:selection="selectDispatchPageSelection"
      @refresh="handleRefreshSelectDispatchPage"
    />
  </div>
</template>
