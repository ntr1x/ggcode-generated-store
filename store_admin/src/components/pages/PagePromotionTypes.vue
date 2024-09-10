<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectPromotionTypePageFilter,
  type QuerySelectPromotionTypePageSorter,
  useQuerySelectPromotionTypePage
} from '../../queries/useQuerySelectPromotionTypePage';
import SectionHeading from '../partials/SectionHeading.vue';
import GridPromotionTypes from '../grids/GridPromotionTypes.vue';

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

const selectPromotionTypePageFilter = reactive<QuerySelectPromotionTypePageFilter>({
})

const selectPromotionTypePageSort = reactive<QuerySelectPromotionTypePageSorter>({
})

const selectPromotionTypePageSelection = ref([])

const selectPromotionTypePageQuery = useQuerySelectPromotionTypePage(
  props,
  selectPromotionTypePageSort,
  selectPromotionTypePageFilter
)

const handleRefreshSelectPromotionTypePage = () => {
  selectPromotionTypePageQuery.refresh()
  selectPromotionTypePageSelection.value = []
}

watch(
  [selectPromotionTypePageFilter, selectPromotionTypePageSort],
  handleRefreshSelectPromotionTypePage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Promotion Types"
    />
    <GridPromotionTypes
      :state="selectPromotionTypePageQuery.state"
      v-model:selection="selectPromotionTypePageSelection"
      @refresh="handleRefreshSelectPromotionTypePage"
    />
  </div>
</template>
