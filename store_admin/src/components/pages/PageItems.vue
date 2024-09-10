<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectItemPageFilter,
  type QuerySelectItemPageSorter,
  useQuerySelectItemPage
} from '../../queries/useQuerySelectItemPage';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarItems from '../toolbars/ToolbarItems.vue';
import GridItems from '../grids/GridItems.vue';

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

const selectItemPageFilter = reactive<QuerySelectItemPageFilter>({
  shopId: undefined,
})

const selectItemPageSort = reactive<QuerySelectItemPageSorter>({
})

const selectItemPageSelection = ref([])

const selectItemPageQuery = useQuerySelectItemPage(
  props,
  selectItemPageSort,
  selectItemPageFilter
)

const handleRefreshSelectItemPage = () => {
  selectItemPageQuery.refresh()
  selectItemPageSelection.value = []
}

watch(
  [selectItemPageFilter, selectItemPageSort],
  handleRefreshSelectItemPage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Items"
    />
    <ToolbarItems
      class="rounded-none border-0 border-b"
      v-model:selection="selectItemPageSelection"
      v-model:filter-by-shop-id = selectItemPageFilter.shopId
      @refresh="handleRefreshSelectItemPage"
    />
    <GridItems
      :state="selectItemPageQuery.state"
      v-model:selection="selectItemPageSelection"
      @refresh="handleRefreshSelectItemPage"
    />
  </div>
</template>
