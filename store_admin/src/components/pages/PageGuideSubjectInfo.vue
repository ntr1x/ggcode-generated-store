<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QueryGetGuideSubjectRecordFilter,
  type QueryGetGuideSubjectRecordSorter,
  useQueryGetGuideSubjectRecord
} from '../../queries/useQueryGetGuideSubjectRecord';
import {
  type QuerySelectGuidePageFilter,
  type QuerySelectGuidePageSorter,
  useQuerySelectGuidePage
} from '../../queries/useQuerySelectGuidePage';
import SectionHeading from '../partials/SectionHeading.vue';
import FieldsetGuideSubjectInfo from '../fieldsets/FieldsetGuideSubjectInfo.vue';
import ToolbarGuides from '../toolbars/ToolbarGuides.vue';
import GridGuides from '../grids/GridGuides.vue';

const props = defineProps<{
  subjectId: string
}>()

onMounted(() => {
  console.trace(props)
})

// @ts-ignore
const authStore = useAuthStore()

// @ts-ignore
const security = useSecurityContext()

const getGuideSubjectRecordFilter = reactive<QueryGetGuideSubjectRecordFilter>({
})

const getGuideSubjectRecordSort = reactive<QueryGetGuideSubjectRecordSorter>({
})

const getGuideSubjectRecordSelection = ref([])

const getGuideSubjectRecordQuery = useQueryGetGuideSubjectRecord(
  props,
  getGuideSubjectRecordSort,
  getGuideSubjectRecordFilter
)

const handleRefreshGetGuideSubjectRecord = () => {
  getGuideSubjectRecordQuery.refresh()
  getGuideSubjectRecordSelection.value = []
}

watch(
  [getGuideSubjectRecordFilter, getGuideSubjectRecordSort],
  handleRefreshGetGuideSubjectRecord
)

const selectGuidePageFilter = reactive<QuerySelectGuidePageFilter>({
  subjectId: props.subjectId,
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
  <div class="flex flex-col flex-1 overflow-auto">
    <SectionHeading
      tag="h1"
      title="Guide Subject"
    />
    <FieldsetGuideSubjectInfo
      :state="getGuideSubjectRecordQuery.state"
    />
    <SectionHeading
      tag="h3"
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
      :scrollable="false"
      :hide-customer="true"
      @refresh="handleRefreshSelectGuidePage"
    />
  </div>
</template>
