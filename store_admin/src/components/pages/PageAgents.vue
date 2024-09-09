<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectAgentPageFilter,
  type QuerySelectAgentPageSorter,
  useQuerySelectAgentPage
} from '../../queries/useQuerySelectAgentPage';
import SectionHeading from '../partials/SectionHeading.vue';
import GridAgents from '../grids/GridAgents.vue';

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

const selectAgentPageFilter = reactive<QuerySelectAgentPageFilter>({
  customerId: undefined,
})

const selectAgentPageSort = reactive<QuerySelectAgentPageSorter>({
})

const selectAgentPageSelection = ref([])

const selectAgentPageQuery = useQuerySelectAgentPage(
  props,
  selectAgentPageSort,
  selectAgentPageFilter
)

const handleRefreshSelectAgentPage = () => {
  selectAgentPageQuery.refresh()
  selectAgentPageSelection.value = []
}

watch(
  [selectAgentPageFilter, selectAgentPageSort],
  handleRefreshSelectAgentPage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-auto">
    <SectionHeading
      tag="h1"
      title="Agents"
    />
    <GridAgents
      :state="selectAgentPageQuery.state"
      v-model:selection="selectAgentPageSelection"
    />
  </div>
</template>
