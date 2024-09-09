<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectSourceTypePageFilter,
  type QuerySelectSourceTypePageSorter,
  useQuerySelectSourceTypePage
} from '../../queries/useQuerySelectSourceTypePage';
import SectionHeading from '../partials/SectionHeading.vue';
import GridSourceTypes from '../grids/GridSourceTypes.vue';

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

const selectSourceTypePageFilter = reactive<QuerySelectSourceTypePageFilter>({
})

const selectSourceTypePageSort = reactive<QuerySelectSourceTypePageSorter>({
})

const selectSourceTypePageSelection = ref([])

const selectSourceTypePageQuery = useQuerySelectSourceTypePage(
  props,
  selectSourceTypePageSort,
  selectSourceTypePageFilter
)

const handleRefreshSelectSourceTypePage = () => {
  selectSourceTypePageQuery.refresh()
  selectSourceTypePageSelection.value = []
}

watch(
  [selectSourceTypePageFilter, selectSourceTypePageSort],
  handleRefreshSelectSourceTypePage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Source Types"
    />
    <GridSourceTypes
      :state="selectSourceTypePageQuery.state"
      v-model:selection="selectSourceTypePageSelection"
    />
  </div>
</template>
