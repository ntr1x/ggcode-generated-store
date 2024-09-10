<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectNetworkTypePageFilter,
  type QuerySelectNetworkTypePageSorter,
  useQuerySelectNetworkTypePage
} from '../../queries/useQuerySelectNetworkTypePage';
import SectionHeading from '../partials/SectionHeading.vue';
import GridNetworkTypes from '../grids/GridNetworkTypes.vue';

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

const selectNetworkTypePageFilter = reactive<QuerySelectNetworkTypePageFilter>({
})

const selectNetworkTypePageSort = reactive<QuerySelectNetworkTypePageSorter>({
  id: undefined,
  name: undefined,
  description: undefined,
})

const selectNetworkTypePageSelection = ref([])

const selectNetworkTypePageQuery = useQuerySelectNetworkTypePage(
  props,
  selectNetworkTypePageSort,
  selectNetworkTypePageFilter
)

const handleRefreshSelectNetworkTypePage = () => {
  selectNetworkTypePageQuery.refresh()
  selectNetworkTypePageSelection.value = []
}

watch(
  [selectNetworkTypePageFilter, selectNetworkTypePageSort],
  handleRefreshSelectNetworkTypePage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Network Types"
    />
    <GridNetworkTypes
      :state="selectNetworkTypePageQuery.state"
      v-model:selection="selectNetworkTypePageSelection"
      @refresh="handleRefreshSelectNetworkTypePage"
    />
  </div>
</template>
