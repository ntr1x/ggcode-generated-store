<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectEventTopicPageFilter,
  type QuerySelectEventTopicPageSorter,
  useQuerySelectEventTopicPage
} from '../../queries/useQuerySelectEventTopicPage';
import SectionHeading from '../partials/SectionHeading.vue';
import GridEventTopics from '../grids/GridEventTopics.vue';

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

const selectEventTopicPageFilter = reactive<QuerySelectEventTopicPageFilter>({
})

const selectEventTopicPageSort = reactive<QuerySelectEventTopicPageSorter>({
  name: undefined,
  description: undefined,
})

const selectEventTopicPageSelection = ref([])

const selectEventTopicPageQuery = useQuerySelectEventTopicPage(
  props,
  selectEventTopicPageSort,
  selectEventTopicPageFilter
)

const handleRefreshSelectEventTopicPage = () => {
  selectEventTopicPageQuery.refresh()
  selectEventTopicPageSelection.value = []
}

watch(
  [selectEventTopicPageFilter, selectEventTopicPageSort],
  handleRefreshSelectEventTopicPage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Event Topics"
    />
    <GridEventTopics
      :state="selectEventTopicPageQuery.state"
      v-model:selection="selectEventTopicPageSelection"
    />
  </div>
</template>
