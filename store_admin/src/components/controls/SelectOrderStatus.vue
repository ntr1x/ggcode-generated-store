<script setup lang="ts">
import { computed } from 'vue';
import Dropdown from 'primevue/dropdown'
import { paymentsRemote } from '../../remotes/paymentsRemote'
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';

const orderStatus = defineModel('orderStatus')

const authStore = useAuthStore()

const { state } = useAxiosRequest(paymentsRemote, async () => {
  const token = await authStore.requireToken()
  return {
    method: 'POST',
    url: '/system/public_order_status/select',
    data: {},
    params: {
  "sort": "id,asc"
},
    headers: {
      Authorization: `Bearer ${token}`
    }
  }
})

const options = computed(() => {
  const items = state.data?.content || []
  return items.map(item => ({
    id: item.id,
    label: item.name,
  }))
})

</script>

<template>
  <Dropdown
    :loading="state.isLoading"
    v-model="orderStatus"
    :options="options"
    showClear
    filter
    optionValue="id"
    optionLabel="label"
    placeholder="Order Status"
    class="w-full md:w-[14rem]"
  />
</template>
