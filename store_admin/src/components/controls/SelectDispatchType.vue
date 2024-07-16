<script setup lang="ts">
import { computed } from 'vue';
import Dropdown from 'primevue/dropdown'
import { eventsRemote } from '../../remotes/eventsRemote'
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';

type IHaveId = {
  id: string
}

type IHaveName = {
  name: string
}

type IHaveDescription = {
  description: string
}

export type ResponseDataRow = IHaveId & IHaveName & IHaveDescription

export type ResponseData = {
  content: ResponseDataRow[]
}

const dispatchType = defineModel('dispatchType')

const authStore = useAuthStore()

const { state } = useAxiosAutoRequest<ResponseData>(eventsRemote, async () => {
  const token = await authStore.requireToken()
  return {
    method: 'POST',
    url: '/system/public_dispatch_type/select',
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
    v-model="dispatchType"
    :options="options"
    showClear
    filter
    optionValue="id"
    optionLabel="label"
    placeholder="Dispatch Type"
    class="w-full"
  />
</template>
