<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectEventSourcePageFilter,
  type QuerySelectEventSourcePageSorter,
  useQuerySelectEventSourcePage
} from '../../queries/useQuerySelectEventSourcePage';
import SectionHeading from '../partials/SectionHeading.vue';
import GridEventSources from '../grids/GridEventSources.vue';

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

const selectEventSourcePageFilter = reactive<QuerySelectEventSourcePageFilter>({
})

const selectEventSourcePageSort = reactive<QuerySelectEventSourcePageSorter>({
  name: undefined,
  description: undefined,
})

const selectEventSourcePageSelection = ref([])

const selectEventSourcePageQuery = useQuerySelectEventSourcePage(
  props,
  selectEventSourcePageSort,
  selectEventSourcePageFilter
)

const handleRefreshSelectEventSourcePage = () => {
  selectEventSourcePageQuery.refresh()
  selectEventSourcePageSelection.value = []
}

watch(
  [selectEventSourcePageFilter, selectEventSourcePageSort],
  handleRefreshSelectEventSourcePage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Event Sources"
    />
    <GridEventSources
      :state="selectEventSourcePageQuery.state"
      v-model:selection="selectEventSourcePageSelection"
      @refresh="handleRefreshSelectEventSourcePage"
    />
  </div>
</template>
