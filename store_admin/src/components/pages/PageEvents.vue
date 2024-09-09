<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectEventPageFilter,
  type QuerySelectEventPageSorter,
  useQuerySelectEventPage
} from '../../queries/useQuerySelectEventPage';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarEvents from '../toolbars/ToolbarEvents.vue';
import GridEvents from '../grids/GridEvents.vue';

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

const selectEventPageFilter = reactive<QuerySelectEventPageFilter>({
  eventSource: undefined,
  eventType: undefined,
  eventTopic: undefined,
})

const selectEventPageSort = reactive<QuerySelectEventPageSorter>({
  id: undefined,
  ceSource: undefined,
  ceType: undefined,
  topic: undefined,
  createdAt: undefined,
})

const selectEventPageSelection = ref([])

const selectEventPageQuery = useQuerySelectEventPage(
  props,
  selectEventPageSort,
  selectEventPageFilter
)

const handleRefreshSelectEventPage = () => {
  selectEventPageQuery.refresh()
  selectEventPageSelection.value = []
}

watch(
  [selectEventPageFilter, selectEventPageSort],
  handleRefreshSelectEventPage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Events"
    />
    <ToolbarEvents
      class="rounded-none border-0 border-b"
      v-model:selection="selectEventPageSelection"
      v-model:filter-by-event-source = selectEventPageFilter.eventSource
      v-model:filter-by-event-topic = selectEventPageFilter.eventTopic
      v-model:filter-by-event-type = selectEventPageFilter.eventType
      v-model:sort-by-id = selectEventPageSort.id
      v-model:sort-by-ce-source = selectEventPageSort.ceSource
      v-model:sort-by-ce-type = selectEventPageSort.ceType
      v-model:sort-by-topic = selectEventPageSort.topic
      v-model:sort-by-created-at = selectEventPageSort.createdAt
      @refresh="handleRefreshSelectEventPage"
    />
    <GridEvents
      :state="selectEventPageQuery.state"
      v-model:selection="selectEventPageSelection"
    />
  </div>
</template>
