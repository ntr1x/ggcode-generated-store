<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectEventTypePageFilter,
  type QuerySelectEventTypePageSorter,
  useQuerySelectEventTypePage
} from '../../queries/useQuerySelectEventTypePage';
import SectionHeading from '../partials/SectionHeading.vue';
import GridEventTypes from '../grids/GridEventTypes.vue';

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

const selectEventTypePageFilter = reactive<QuerySelectEventTypePageFilter>({
})

const selectEventTypePageSort = reactive<QuerySelectEventTypePageSorter>({
  name: undefined,
  description: undefined,
})

const selectEventTypePageSelection = ref([])

const selectEventTypePageQuery = useQuerySelectEventTypePage(
  props,
  selectEventTypePageSort,
  selectEventTypePageFilter
)

const handleRefreshSelectEventTypePage = () => {
  selectEventTypePageQuery.refresh()
  selectEventTypePageSelection.value = []
}

watch(
  [selectEventTypePageFilter, selectEventTypePageSort],
  handleRefreshSelectEventTypePage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Event Types"
    />
    <GridEventTypes
      :state="selectEventTypePageQuery.state"
      v-model:selection="selectEventTypePageSelection"
      @refresh="handleRefreshSelectEventTypePage"
    />
  </div>
</template>
