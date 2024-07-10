<script setup lang="ts">
import { computed } from 'vue';
import Dropdown from 'primevue/dropdown'
import { customersRemote } from '../../remotes/customersRemote'
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';

type IHaveId = {
  id: string
}

type IHaveName = {
  fullName: string
}

type IHaveDescription = {
  email: string
}

export type ResponseDataRow = IHaveId & IHaveName & IHaveDescription

export type ResponseData = {
  content: ResponseDataRow[]
}

const customerId = defineModel('customerId')

const authStore = useAuthStore()

const { state } = useAxiosRequest<ResponseData>(customersRemote, async () => {
  const token = await authStore.requireToken()
  return {
    method: 'POST',
    url: '/system/public_customer/select',
    data: {},
    params: {"size":50,"sort":"fullName"},
    headers: {
      Authorization: `Bearer ${token}`
    }
  }
})

const options = computed(() => {
  const items = state.data?.content || []
  return items.map(item => ({
    id: item.id,
    label: item.fullName,
  }))
})

</script>

<template>
  <Dropdown
    :loading="state.isLoading"
    v-model="customerId"
    :options="options"
    showClear
    filter
    optionValue="id"
    optionLabel="label"
    placeholder="Customer Id"
    class="w-full md:w-[14rem]"
  />
</template>
