<script setup lang="ts">
import { onMounted } from 'vue';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import {
  type QuerySelectPaymentTypePageFilter,
  type QuerySelectPaymentTypePageSorter,
  useQuerySelectPaymentTypePage
} from '../../queries/useQuerySelectPaymentTypePage';
import SectionHeading from '../partials/SectionHeading.vue';
import GridPaymentTypes from '../grids/GridPaymentTypes.vue';

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

const selectPaymentTypePageFilter = reactive<QuerySelectPaymentTypePageFilter>({
})

const selectPaymentTypePageSort = reactive<QuerySelectPaymentTypePageSorter>({
})

const selectPaymentTypePageSelection = ref([])

const selectPaymentTypePageQuery = useQuerySelectPaymentTypePage(
  props,
  selectPaymentTypePageSort,
  selectPaymentTypePageFilter
)

const handleRefreshSelectPaymentTypePage = () => {
  selectPaymentTypePageQuery.refresh()
  selectPaymentTypePageSelection.value = []
}

watch(
  [selectPaymentTypePageFilter, selectPaymentTypePageSort],
  handleRefreshSelectPaymentTypePage
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Payment Types"
    />
    <GridPaymentTypes
      :state="selectPaymentTypePageQuery.state"
      v-model:selection="selectPaymentTypePageSelection"
    />
  </div>
</template>
