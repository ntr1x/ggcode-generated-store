<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectTemplateShapePageFilter,
  type QuerySelectTemplateShapePageSorter,
  useQuerySelectTemplateShapePage
} from '../../queries/useQuerySelectTemplateShapePage';
import SectionHeading from '../partials/SectionHeading.vue';
import GridTemplateShapes from '../grids/GridTemplateShapes.vue';

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

const selectTemplateShapePageFilter = reactive<QuerySelectTemplateShapePageFilter>({
})

const selectTemplateShapePageSort = reactive<QuerySelectTemplateShapePageSorter>({
  id: undefined,
  name: undefined,
  description: undefined,
})

const selectTemplateShapePageSelection = ref([])

const selectTemplateShapePageQuery = useQuerySelectTemplateShapePage(
  props,
  selectTemplateShapePageSort,
  selectTemplateShapePageFilter
)

const handleRefreshSelectTemplateShapePage = () => {
  selectTemplateShapePageQuery.refresh()
  selectTemplateShapePageSelection.value = []
}

watch(
  [selectTemplateShapePageFilter, selectTemplateShapePageSort],
  handleRefreshSelectTemplateShapePage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Template Shapes"
    />
    <GridTemplateShapes
      :state="selectTemplateShapePageQuery.state"
      v-model:selection="selectTemplateShapePageSelection"
    />
  </div>
</template>
