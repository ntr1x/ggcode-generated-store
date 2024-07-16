<script setup lang="ts">
import { computed } from 'vue';
import Dropdown from 'primevue/dropdown'
import { eventsRemote } from '../../remotes/eventsRemote'
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';

type IHaveId = {
  name: string
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

const eventTopic = defineModel('eventTopic')

const authStore = useAuthStore()

const { state } = useAxiosAutoRequest<ResponseData>(eventsRemote, async () => {
  const token = await authStore.requireToken()
  return {
    method: 'POST',
    url: '/system/public_event_topic_info/select',
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
    v-model="eventTopic"
    :options="options"
    showClear
    filter
    optionValue="id"
    optionLabel="label"
    placeholder="Event Topic"
    class="w-full"
  />
</template>
