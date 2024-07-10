<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { paymentsRemote } from '../../remotes/paymentsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import GridOrderStatuses from '../grids/GridOrderStatuses.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()

const orderStatusSelectFilter = reactive({
})

const orderStatusSelectSort = reactive({
})

const orderStatusSelectSelection = ref([])

const orderStatusSelectQuery = useAxiosRequest<any>(paymentsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params: Record<string, any> = {"size":50,"sort":"id,asc"}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

  return {
    method: 'POST',
    url: `/system/public_order_status/select`,
    params,
    data,
    paramsSerializer: {
      indexes: null
    },
    headers: {
      Authorization: `Bearer ${token}`
    }
  }
})

watch(
  [orderStatusSelectFilter, orderStatusSelectSort],
  () => {
    orderStatusSelectQuery.refresh()
    orderStatusSelectSelection.value = []
  }
)
</script>

<template>
  <div class="flex flex-col overflow-hidden">
    <SectionHeading tag="h1" title="Order Statuses" />
    <GridOrderStatuses
      :state="orderStatusSelectQuery.state"
      v-model:selection="orderStatusSelectSelection"
    />
  </div>
</template>
