<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectGuidePageFilter,
  type QuerySelectGuidePageSorter,
  useQuerySelectGuidePage
} from '../../queries/useQuerySelectGuidePage';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarGuides from '../toolbars/ToolbarGuides.vue';
import GridGuides from '../grids/GridGuides.vue';

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

const selectGuidePageFilter = reactive<QuerySelectGuidePageFilter>({
  subjectId: undefined,
})

const selectGuidePageSort = reactive<QuerySelectGuidePageSorter>({
  id: undefined,
  name: undefined,
  position: undefined,
  subjectId: undefined,
  createdAt: undefined,
  updatedAt: undefined,
})

const selectGuidePageSelection = ref([])

const selectGuidePageQuery = useQuerySelectGuidePage(
  props,
  selectGuidePageSort,
  selectGuidePageFilter
)

const handleRefreshSelectGuidePage = () => {
  selectGuidePageQuery.refresh()
  selectGuidePageSelection.value = []
}

watch(
  [selectGuidePageFilter, selectGuidePageSort],
  handleRefreshSelectGuidePage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Guides"
    />
    <ToolbarGuides
      class="rounded-none border-0 border-b"
      v-model:selection="selectGuidePageSelection"
      v-model:filter-by-subject-id = selectGuidePageFilter.subjectId
      v-model:sort-by-id = selectGuidePageSort.id
      v-model:sort-by-name = selectGuidePageSort.name
      v-model:sort-by-position = selectGuidePageSort.position
      v-model:sort-by-subject-id = selectGuidePageSort.subjectId
      @refresh="handleRefreshSelectGuidePage"
    />
    <GridGuides
      :state="selectGuidePageQuery.state"
      v-model:selection="selectGuidePageSelection"
    />
  </div>
</template>
