<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectOrderTypePageFilter,
  type QuerySelectOrderTypePageSorter,
  useQuerySelectOrderTypePage
} from '../../queries/useQuerySelectOrderTypePage';
import SectionHeading from '../partials/SectionHeading.vue';
import GridOrderTypes from '../grids/GridOrderTypes.vue';

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

const selectOrderTypePageFilter = reactive<QuerySelectOrderTypePageFilter>({
})

const selectOrderTypePageSort = reactive<QuerySelectOrderTypePageSorter>({
})

const selectOrderTypePageSelection = ref([])

const selectOrderTypePageQuery = useQuerySelectOrderTypePage(
  props,
  selectOrderTypePageSort,
  selectOrderTypePageFilter
)

const handleRefreshSelectOrderTypePage = () => {
  selectOrderTypePageQuery.refresh()
  selectOrderTypePageSelection.value = []
}

watch(
  [selectOrderTypePageFilter, selectOrderTypePageSort],
  handleRefreshSelectOrderTypePage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Order Types"
    />
    <GridOrderTypes
      :state="selectOrderTypePageQuery.state"
      v-model:selection="selectOrderTypePageSelection"
      @refresh="handleRefreshSelectOrderTypePage"
    />
  </div>
</template>
