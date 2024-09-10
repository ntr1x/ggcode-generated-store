<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectShopPageFilter,
  type QuerySelectShopPageSorter,
  useQuerySelectShopPage
} from '../../queries/useQuerySelectShopPage';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarShops from '../toolbars/ToolbarShops.vue';
import GridShops from '../grids/GridShops.vue';

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

const selectShopPageFilter = reactive<QuerySelectShopPageFilter>({
  regionId: undefined,
})

const selectShopPageSort = reactive<QuerySelectShopPageSorter>({
})

const selectShopPageSelection = ref([])

const selectShopPageQuery = useQuerySelectShopPage(
  props,
  selectShopPageSort,
  selectShopPageFilter
)

const handleRefreshSelectShopPage = () => {
  selectShopPageQuery.refresh()
  selectShopPageSelection.value = []
}

watch(
  [selectShopPageFilter, selectShopPageSort],
  handleRefreshSelectShopPage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Shops"
    />
    <ToolbarShops
      class="rounded-none border-0 border-b"
      v-model:selection="selectShopPageSelection"
      v-model:filter-by-region-id = selectShopPageFilter.regionId
      @refresh="handleRefreshSelectShopPage"
    />
    <GridShops
      :state="selectShopPageQuery.state"
      v-model:selection="selectShopPageSelection"
      @refresh="handleRefreshSelectShopPage"
    />
  </div>
</template>
