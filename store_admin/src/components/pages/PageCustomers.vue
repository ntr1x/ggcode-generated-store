<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { customersRemote } from '../../remotes/customersRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import GridCustomers from '../grids/GridCustomers.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()

const customerSelectFilter = reactive({
})

const customerSelectSort = reactive({
})

const customerSelectSelection = ref([])

const customerSelectQuery = useAxiosRequest<any>(customersRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params: Record<string, any> = {"size":50,"sort":"fullName"}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

  return {
    method: 'POST',
    url: `/system/public_customer/select`,
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
  [customerSelectFilter, customerSelectSort],
  () => {
    customerSelectQuery.refresh()
    customerSelectSelection.value = []
  }
)
</script>

<template>
  <div class="flex flex-col overflow-hidden">
    <SectionHeading tag="h1" title="Customers" />
    <GridCustomers
      :state="customerSelectQuery.state"
      v-model:selection="customerSelectSelection"
    />
  </div>
</template>
