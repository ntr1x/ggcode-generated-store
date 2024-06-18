<script setup lang="ts">
import { computed } from 'vue';
import Dropdown from 'primevue/dropdown'
import { eventsRemote } from '../../remotes/eventsRemote'
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';

const eventSource = defineModel('eventSource')

const authStore = useAuthStore()

const { state } = useAxiosRequest(eventsRemote, async () => {
  const token = await authStore.requireToken()
  return {
    method: 'POST',
    url: '/system/public_event_source_info/select',
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
    id: item.name,
    label: item.name,
  }))
})

</script>

<template>
  <Dropdown
    :loading="state.isLoading"
    v-model="eventSource"
    :options="options"
    showClear
    filter
    optionValue="id"
    optionLabel="label"
    placeholder="Event Source"
    class="w-full md:w-[14rem]"
  />
</template>
