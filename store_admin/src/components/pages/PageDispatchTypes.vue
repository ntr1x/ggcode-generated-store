<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectDispatchTypePageFilter,
  type QuerySelectDispatchTypePageSorter,
  useQuerySelectDispatchTypePage
} from '../../queries/useQuerySelectDispatchTypePage';
import SectionHeading from '../partials/SectionHeading.vue';
import GridDispatchTypes from '../grids/GridDispatchTypes.vue';

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

const selectDispatchTypePageFilter = reactive<QuerySelectDispatchTypePageFilter>({
})

const selectDispatchTypePageSort = reactive<QuerySelectDispatchTypePageSorter>({
  id: undefined,
  name: undefined,
  description: undefined,
})

const selectDispatchTypePageSelection = ref([])

const selectDispatchTypePageQuery = useQuerySelectDispatchTypePage(
  props,
  selectDispatchTypePageSort,
  selectDispatchTypePageFilter
)

const handleRefreshSelectDispatchTypePage = () => {
  selectDispatchTypePageQuery.refresh()
  selectDispatchTypePageSelection.value = []
}

watch(
  [selectDispatchTypePageFilter, selectDispatchTypePageSort],
  handleRefreshSelectDispatchTypePage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Dispatch Types"
    />
    <GridDispatchTypes
      :state="selectDispatchTypePageQuery.state"
      v-model:selection="selectDispatchTypePageSelection"
      @refresh="handleRefreshSelectDispatchTypePage"
    />
  </div>
</template>
