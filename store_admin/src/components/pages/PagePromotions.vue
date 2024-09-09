<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectPromotionPageFilter,
  type QuerySelectPromotionPageSorter,
  useQuerySelectPromotionPage
} from '../../queries/useQuerySelectPromotionPage';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarPromotions from '../toolbars/ToolbarPromotions.vue';
import GridPromotions from '../grids/GridPromotions.vue';

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

const selectPromotionPageFilter = reactive<QuerySelectPromotionPageFilter>({
  promotionType: undefined,
})

const selectPromotionPageSort = reactive<QuerySelectPromotionPageSorter>({
})

const selectPromotionPageSelection = ref([])

const selectPromotionPageQuery = useQuerySelectPromotionPage(
  props,
  selectPromotionPageSort,
  selectPromotionPageFilter
)

const handleRefreshSelectPromotionPage = () => {
  selectPromotionPageQuery.refresh()
  selectPromotionPageSelection.value = []
}

watch(
  [selectPromotionPageFilter, selectPromotionPageSort],
  handleRefreshSelectPromotionPage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Promotions"
    />
    <ToolbarPromotions
      class="rounded-none border-0 border-b"
      v-model:selection="selectPromotionPageSelection"
      v-model:filter-by-promotion-type = selectPromotionPageFilter.promotionType
      @refresh="handleRefreshSelectPromotionPage"
    />
    <GridPromotions
      :state="selectPromotionPageQuery.state"
      v-model:selection="selectPromotionPageSelection"
    />
  </div>
</template>
