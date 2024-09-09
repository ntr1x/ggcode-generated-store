<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectTemplatePageFilter,
  type QuerySelectTemplatePageSorter,
  useQuerySelectTemplatePage
} from '../../queries/useQuerySelectTemplatePage';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarTemplates from '../toolbars/ToolbarTemplates.vue';
import GridTemplates from '../grids/GridTemplates.vue';

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

const selectTemplatePageFilter = reactive<QuerySelectTemplatePageFilter>({
  typeId: undefined,
  shapeId: undefined,
})

const selectTemplatePageSort = reactive<QuerySelectTemplatePageSorter>({
  id: undefined,
  name: undefined,
  typeId: undefined,
  shapeId: undefined,
})

const selectTemplatePageSelection = ref([])

const selectTemplatePageQuery = useQuerySelectTemplatePage(
  props,
  selectTemplatePageSort,
  selectTemplatePageFilter
)

const handleRefreshSelectTemplatePage = () => {
  selectTemplatePageQuery.refresh()
  selectTemplatePageSelection.value = []
}

watch(
  [selectTemplatePageFilter, selectTemplatePageSort],
  handleRefreshSelectTemplatePage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Templates"
    />
    <ToolbarTemplates
      class="rounded-none border-0 border-b"
      v-model:selection="selectTemplatePageSelection"
      v-model:filter-by-type-id = selectTemplatePageFilter.typeId
      v-model:filter-by-shape-id = selectTemplatePageFilter.shapeId
      v-model:sort-by-id = selectTemplatePageSort.id
      v-model:sort-by-name = selectTemplatePageSort.name
      v-model:sort-by-type-id = selectTemplatePageSort.typeId
      v-model:sort-by-shape-id = selectTemplatePageSort.shapeId
      @refresh="handleRefreshSelectTemplatePage"
    />
    <GridTemplates
      :state="selectTemplatePageQuery.state"
      v-model:selection="selectTemplatePageSelection"
    />
  </div>
</template>
