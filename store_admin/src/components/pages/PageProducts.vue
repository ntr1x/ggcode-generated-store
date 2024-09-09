<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectProductPageFilter,
  type QuerySelectProductPageSorter,
  useQuerySelectProductPage
} from '../../queries/useQuerySelectProductPage';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarProducts from '../toolbars/ToolbarProducts.vue';
import GridProducts from '../grids/GridProducts.vue';

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

const selectProductPageFilter = reactive<QuerySelectProductPageFilter>({
  categoryId: undefined,
})

const selectProductPageSort = reactive<QuerySelectProductPageSorter>({
  id: undefined,
  name: undefined,
})

const selectProductPageSelection = ref([])

const selectProductPageQuery = useQuerySelectProductPage(
  props,
  selectProductPageSort,
  selectProductPageFilter
)

const handleRefreshSelectProductPage = () => {
  selectProductPageQuery.refresh()
  selectProductPageSelection.value = []
}

watch(
  [selectProductPageFilter, selectProductPageSort],
  handleRefreshSelectProductPage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Products"
    />
    <ToolbarProducts
      class="rounded-none border-0 border-b"
      v-model:selection="selectProductPageSelection"
      v-model:filter-by-category-id = selectProductPageFilter.categoryId
      v-model:sort-by-id = selectProductPageSort.id
      v-model:sort-by-name = selectProductPageSort.name
      @refresh="handleRefreshSelectProductPage"
    />
    <GridProducts
      :state="selectProductPageQuery.state"
      v-model:selection="selectProductPageSelection"
    />
  </div>
</template>
