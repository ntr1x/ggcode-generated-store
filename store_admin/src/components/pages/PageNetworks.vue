<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectNetworkPageFilter,
  type QuerySelectNetworkPageSorter,
  useQuerySelectNetworkPage
} from '../../queries/useQuerySelectNetworkPage';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarNetworks from '../toolbars/ToolbarNetworks.vue';
import GridNetworks from '../grids/GridNetworks.vue';

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

const selectNetworkPageFilter = reactive<QuerySelectNetworkPageFilter>({
  typeId: undefined,
  shopId: undefined,
  hidden: undefined,
})

const selectNetworkPageSort = reactive<QuerySelectNetworkPageSorter>({
  id: undefined,
  name: undefined,
  typeId: undefined,
  shopId: undefined,
  hidden: undefined,
})

const selectNetworkPageSelection = ref([])

const selectNetworkPageQuery = useQuerySelectNetworkPage(
  props,
  selectNetworkPageSort,
  selectNetworkPageFilter
)

const handleRefreshSelectNetworkPage = () => {
  selectNetworkPageQuery.refresh()
  selectNetworkPageSelection.value = []
}

watch(
  [selectNetworkPageFilter, selectNetworkPageSort],
  handleRefreshSelectNetworkPage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Networks"
    />
    <ToolbarNetworks
      class="rounded-none border-0 border-b"
      v-model:selection="selectNetworkPageSelection"
      v-model:filter-by-type-id = selectNetworkPageFilter.typeId
      v-model:sort-by-id = selectNetworkPageSort.id
      v-model:sort-by-name = selectNetworkPageSort.name
      v-model:sort-by-type-id = selectNetworkPageSort.typeId
      v-model:sort-by-hidden = selectNetworkPageSort.hidden
      @refresh="handleRefreshSelectNetworkPage"
    />
    <GridNetworks
      :state="selectNetworkPageQuery.state"
      v-model:selection="selectNetworkPageSelection"
      @refresh="handleRefreshSelectNetworkPage"
    />
  </div>
</template>
