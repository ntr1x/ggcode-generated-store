<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectCategoryPageFilter,
  type QuerySelectCategoryPageSorter,
  useQuerySelectCategoryPage
} from '../../queries/useQuerySelectCategoryPage';
import SectionHeading from '../partials/SectionHeading.vue';
import GridCategories from '../grids/GridCategories.vue';

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

const selectCategoryPageFilter = reactive<QuerySelectCategoryPageFilter>({
})

const selectCategoryPageSort = reactive<QuerySelectCategoryPageSorter>({
})

const selectCategoryPageSelection = ref([])

const selectCategoryPageQuery = useQuerySelectCategoryPage(
  props,
  selectCategoryPageSort,
  selectCategoryPageFilter
)

const handleRefreshSelectCategoryPage = () => {
  selectCategoryPageQuery.refresh()
  selectCategoryPageSelection.value = []
}

watch(
  [selectCategoryPageFilter, selectCategoryPageSort],
  handleRefreshSelectCategoryPage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Categories"
    />
    <GridCategories
      :state="selectCategoryPageQuery.state"
      v-model:selection="selectCategoryPageSelection"
    />
  </div>
</template>
