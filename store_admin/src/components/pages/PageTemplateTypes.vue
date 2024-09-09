<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectTemplateTypePageFilter,
  type QuerySelectTemplateTypePageSorter,
  useQuerySelectTemplateTypePage
} from '../../queries/useQuerySelectTemplateTypePage';
import SectionHeading from '../partials/SectionHeading.vue';
import GridTemplateTypes from '../grids/GridTemplateTypes.vue';

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

const selectTemplateTypePageFilter = reactive<QuerySelectTemplateTypePageFilter>({
})

const selectTemplateTypePageSort = reactive<QuerySelectTemplateTypePageSorter>({
  id: undefined,
  name: undefined,
  description: undefined,
})

const selectTemplateTypePageSelection = ref([])

const selectTemplateTypePageQuery = useQuerySelectTemplateTypePage(
  props,
  selectTemplateTypePageSort,
  selectTemplateTypePageFilter
)

const handleRefreshSelectTemplateTypePage = () => {
  selectTemplateTypePageQuery.refresh()
  selectTemplateTypePageSelection.value = []
}

watch(
  [selectTemplateTypePageFilter, selectTemplateTypePageSort],
  handleRefreshSelectTemplateTypePage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Template Types"
    />
    <GridTemplateTypes
      :state="selectTemplateTypePageQuery.state"
      v-model:selection="selectTemplateTypePageSelection"
    />
  </div>
</template>
