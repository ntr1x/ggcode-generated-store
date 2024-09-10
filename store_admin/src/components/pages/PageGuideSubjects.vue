<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectGuideSubjectPageFilter,
  type QuerySelectGuideSubjectPageSorter,
  useQuerySelectGuideSubjectPage
} from '../../queries/useQuerySelectGuideSubjectPage';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarGuideSubjects from '../toolbars/ToolbarGuideSubjects.vue';
import GridGuideSubjects from '../grids/GridGuideSubjects.vue';

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

const selectGuideSubjectPageFilter = reactive<QuerySelectGuideSubjectPageFilter>({
})

const selectGuideSubjectPageSort = reactive<QuerySelectGuideSubjectPageSorter>({
  id: undefined,
  name: undefined,
  position: undefined,
  createdAt: undefined,
  updatedAt: undefined,
})

const selectGuideSubjectPageSelection = ref([])

const selectGuideSubjectPageQuery = useQuerySelectGuideSubjectPage(
  props,
  selectGuideSubjectPageSort,
  selectGuideSubjectPageFilter
)

const handleRefreshSelectGuideSubjectPage = () => {
  selectGuideSubjectPageQuery.refresh()
  selectGuideSubjectPageSelection.value = []
}

watch(
  [selectGuideSubjectPageFilter, selectGuideSubjectPageSort],
  handleRefreshSelectGuideSubjectPage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Guide Subjects"
    />
    <ToolbarGuideSubjects
      class="rounded-none border-0 border-b"
      v-model:selection="selectGuideSubjectPageSelection"
      v-model:sort-by-id = selectGuideSubjectPageSort.id
      v-model:sort-by-name = selectGuideSubjectPageSort.name
      v-model:sort-by-position = selectGuideSubjectPageSort.position
      @refresh="handleRefreshSelectGuideSubjectPage"
    />
    <GridGuideSubjects
      :state="selectGuideSubjectPageQuery.state"
      v-model:selection="selectGuideSubjectPageSelection"
      @refresh="handleRefreshSelectGuideSubjectPage"
    />
  </div>
</template>
