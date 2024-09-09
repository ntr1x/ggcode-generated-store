<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectCustomerPageFilter,
  type QuerySelectCustomerPageSorter,
  useQuerySelectCustomerPage
} from '../../queries/useQuerySelectCustomerPage';
import SectionHeading from '../partials/SectionHeading.vue';
import GridCustomers from '../grids/GridCustomers.vue';

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

const selectCustomerPageFilter = reactive<QuerySelectCustomerPageFilter>({
})

const selectCustomerPageSort = reactive<QuerySelectCustomerPageSorter>({
})

const selectCustomerPageSelection = ref([])

const selectCustomerPageQuery = useQuerySelectCustomerPage(
  props,
  selectCustomerPageSort,
  selectCustomerPageFilter
)

const handleRefreshSelectCustomerPage = () => {
  selectCustomerPageQuery.refresh()
  selectCustomerPageSelection.value = []
}

watch(
  [selectCustomerPageFilter, selectCustomerPageSort],
  handleRefreshSelectCustomerPage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Customers"
    />
    <GridCustomers
      :state="selectCustomerPageQuery.state"
      v-model:selection="selectCustomerPageSelection"
    />
  </div>
</template>
