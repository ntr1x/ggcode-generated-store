<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QueryGetAgentRecordFilter,
  type QueryGetAgentRecordSorter,
  useQueryGetAgentRecord
} from '../../queries/useQueryGetAgentRecord';
import SectionHeading from '../partials/SectionHeading.vue';
import FieldsetAgentInfo from '../fieldsets/FieldsetAgentInfo.vue';

const props = defineProps<{
  agentId: string
}>()

onMounted(() => {
  console.trace(props)
})

// @ts-ignore
const authStore = useAuthStore()

// @ts-ignore
const security = useSecurityContext()

const getAgentRecordFilter = reactive<QueryGetAgentRecordFilter>({
})

const getAgentRecordSort = reactive<QueryGetAgentRecordSorter>({
})

const getAgentRecordSelection = ref([])

const getAgentRecordQuery = useQueryGetAgentRecord(
  props,
  getAgentRecordSort,
  getAgentRecordFilter
)

const handleRefreshGetAgentRecord = () => {
  getAgentRecordQuery.refresh()
  getAgentRecordSelection.value = []
}

watch(
  [getAgentRecordFilter, getAgentRecordSort],
  handleRefreshGetAgentRecord
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Agent"
    />
    <FieldsetAgentInfo
      :state="getAgentRecordQuery.state"
    />
  </div>
</template>
