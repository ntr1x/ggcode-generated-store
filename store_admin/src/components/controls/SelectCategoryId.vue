<script setup lang="ts">
import { computed } from 'vue';
import Dropdown from 'primevue/dropdown'
import { productsRemote } from '../../remotes/productsRemote'
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';

type IHaveId = {
  id: string
}

type IHaveName = {
  name: string
}

type IHaveDescription = {
}

export type ResponseDataRow = IHaveId & IHaveName & IHaveDescription

export type ResponseData = {
  content: ResponseDataRow[]
}

const categoryId = defineModel('categoryId')

const authStore = useAuthStore()

const { state } = useAxiosAutoRequest<ResponseData>(productsRemote, async () => {
  const token = await authStore.requireToken()
  return {
    method: 'POST',
    url: '/system/public_category/select',
    data: {},
    params: {},
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
    v-model="categoryId"
    :options="options"
    showClear
    filter
    optionValue="id"
    optionLabel="label"
    placeholder="Category Id"
    class="w-full"
  />
</template>
