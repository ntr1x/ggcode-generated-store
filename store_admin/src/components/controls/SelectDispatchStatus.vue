<script setup lang="ts">
import { computed } from 'vue';
import Dropdown from 'primevue/dropdown'
import { eventsRemote } from '../../remotes/eventsRemote'
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';

const dispatchStatus = defineModel('dispatchStatus')

const authStore = useAuthStore()

const { state } = useAxiosRequest(eventsRemote, async () => {
  const token = await authStore.requireToken()
  return {
    method: 'POST',
    url: '/system/public_dispatch_status/select',
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
    v-model="dispatchStatus"
    :options="options"
    showClear
    filter
    optionValue="id"
    optionLabel="label"
    placeholder="Dispatch Status"
    class="w-full md:w-[14rem]"
  />
</template>
