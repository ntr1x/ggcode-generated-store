<script setup lang="ts">
import { computed } from 'vue';
import Dropdown from 'primevue/dropdown'
import { selectOrderStatusPageRequest } from '../../requests/selectOrderStatusPageRequest'
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';

type IHaveId = {
  id: string
}

type IHaveName = {
  name?: string
}

type IHaveDescription = {
  description?: string
}

export type ResponseDataRow = IHaveId & IHaveName & IHaveDescription

export type ResponseData = {
  content: ResponseDataRow[]
}

const orderStatus = defineModel('orderStatus')

const authStore = useAuthStore()

const { state } = useAxiosAutoRequest<ResponseData>(async () => {
  const token = await authStore.requireToken()
  return selectOrderStatusPageRequest({
    token,
  })
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
    optionValue="id"
    optionLabel="label"
    placeholder="Order Status"
    class="w-full"
  />
</template>
