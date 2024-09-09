<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectRegionPageFilter,
  type QuerySelectRegionPageSorter,
  useQuerySelectRegionPage
} from '../../queries/useQuerySelectRegionPage';
import SectionHeading from '../partials/SectionHeading.vue';
import GridRegions from '../grids/GridRegions.vue';

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

const selectRegionPageFilter = reactive<QuerySelectRegionPageFilter>({
})

const selectRegionPageSort = reactive<QuerySelectRegionPageSorter>({
})

const selectRegionPageSelection = ref([])

const selectRegionPageQuery = useQuerySelectRegionPage(
  props,
  selectRegionPageSort,
  selectRegionPageFilter
)

const handleRefreshSelectRegionPage = () => {
  selectRegionPageQuery.refresh()
  selectRegionPageSelection.value = []
}

watch(
  [selectRegionPageFilter, selectRegionPageSort],
  handleRefreshSelectRegionPage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Regions"
    />
    <GridRegions
      :state="selectRegionPageQuery.state"
      v-model:selection="selectRegionPageSelection"
    />
  </div>
</template>
