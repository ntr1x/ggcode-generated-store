<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QueryGetProductRecordFilter,
  type QueryGetProductRecordSorter,
  useQueryGetProductRecord
} from '../../queries/useQueryGetProductRecord';
import {
  type QuerySelectPromotionTargetPageFilter,
  type QuerySelectPromotionTargetPageSorter,
  useQuerySelectPromotionTargetPage
} from '../../queries/useQuerySelectPromotionTargetPage';
import SectionHeading from '../partials/SectionHeading.vue';
import FieldsetProductInfo from '../fieldsets/FieldsetProductInfo.vue';
import ToolbarPromotionTargets from '../toolbars/ToolbarPromotionTargets.vue';
import GridPromotionTargets from '../grids/GridPromotionTargets.vue';

const props = defineProps<{
  productId: string
}>()

onMounted(() => {
  console.trace(props)
})

// @ts-ignore
const authStore = useAuthStore()

// @ts-ignore
const security = useSecurityContext()

const getProductRecordFilter = reactive<QueryGetProductRecordFilter>({
})

const getProductRecordSort = reactive<QueryGetProductRecordSorter>({
})

const getProductRecordSelection = ref([])

const getProductRecordQuery = useQueryGetProductRecord(
  props,
  getProductRecordSort,
  getProductRecordFilter
)

const handleRefreshGetProductRecord = () => {
  getProductRecordQuery.refresh()
  getProductRecordSelection.value = []
}

watch(
  [getProductRecordFilter, getProductRecordSort],
  handleRefreshGetProductRecord
)

const selectPromotionTargetPageFilter = reactive<QuerySelectPromotionTargetPageFilter>({
  productId: props.productId,
  promotionId: undefined,
})

const selectPromotionTargetPageSort = reactive<QuerySelectPromotionTargetPageSorter>({
})

const selectPromotionTargetPageSelection = ref([])

const selectPromotionTargetPageQuery = useQuerySelectPromotionTargetPage(
  props,
  selectPromotionTargetPageSort,
  selectPromotionTargetPageFilter
)

const handleRefreshSelectPromotionTargetPage = () => {
  selectPromotionTargetPageQuery.refresh()
  selectPromotionTargetPageSelection.value = []
}

watch(
  [selectPromotionTargetPageFilter, selectPromotionTargetPageSort],
  handleRefreshSelectPromotionTargetPage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-auto">
    <SectionHeading
      tag="h1"
      title="Product"
    />
    <FieldsetProductInfo
      :state="getProductRecordQuery.state"
    />
    <SectionHeading
      tag="h3"
      title="Promotion Targets"
    />
    <ToolbarPromotionTargets
      class="rounded-none border-0 border-b"
      v-model:selection="selectPromotionTargetPageSelection"
      v-model:filter-by-promotion-id = selectPromotionTargetPageFilter.promotionId
      @refresh="handleRefreshSelectPromotionTargetPage"
    />
    <GridPromotionTargets
      :state="selectPromotionTargetPageQuery.state"
      v-model:selection="selectPromotionTargetPageSelection"
      :scrollable="false"
      :hide-customer="true"
    />
  </div>
</template>
