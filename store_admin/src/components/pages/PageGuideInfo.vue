<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QueryGetGuideRecordFilter,
  type QueryGetGuideRecordSorter,
  useQueryGetGuideRecord
} from '../../queries/useQueryGetGuideRecord';
import SectionHeading from '../partials/SectionHeading.vue';
import FieldsetGuideGuideInfo from '../fieldsets/FieldsetGuideGuideInfo.vue';

const props = defineProps<{
  guideId: string
}>()

onMounted(() => {
  console.trace(props)
})

// @ts-ignore
const authStore = useAuthStore()

// @ts-ignore
const security = useSecurityContext()

const getGuideRecordFilter = reactive<QueryGetGuideRecordFilter>({
})

const getGuideRecordSort = reactive<QueryGetGuideRecordSorter>({
})

const getGuideRecordSelection = ref([])

const getGuideRecordQuery = useQueryGetGuideRecord(
  props,
  getGuideRecordSort,
  getGuideRecordFilter
)

const handleRefreshGetGuideRecord = () => {
  getGuideRecordQuery.refresh()
  getGuideRecordSelection.value = []
}

watch(
  [getGuideRecordFilter, getGuideRecordSort],
  handleRefreshGetGuideRecord
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-auto">
    <SectionHeading
      tag="h1"
      title="Guide"
    />
    <FieldsetGuideGuideInfo
      :state="getGuideRecordQuery.state"
    />
  </div>
</template>
