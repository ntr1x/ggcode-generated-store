<script setup lang="ts">
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useRoute } from 'vue-router';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { paymentsRemote } from '../../remotes/paymentsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import GridOrderTypes from '../grids/GridOrderTypes.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()

const orderTypeSelectFilter = reactive({
})

const orderTypeSelectSort = reactive({
})

const orderTypeSelectSelection = ref([])

const orderTypeSelectQuery = useAxiosRequest<any>(paymentsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params = {}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : undefined)

  return {
    method: 'POST',
    url: `/system/public_order_type/select`,
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
  [orderTypeSelectFilter, orderTypeSelectSort],
  () => {
    orderTypeSelectQuery.refresh()
    orderTypeSelectSelection.value = []
  }
)
</script>

<template>
  <div class="flex flex-col overflow-hidden">
    <SectionHeading tag="h1" title="Order Types" />
    <GridOrderTypes
      :state="orderTypeSelectQuery.state"
      v-model:selection="orderTypeSelectSelection"
    />
  </div>
</template>
