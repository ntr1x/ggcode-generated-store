<script setup lang="ts">
import { computed } from 'vue';
import Dropdown from 'primevue/dropdown'
import { selectEventTopicPageRequest } from '../../requests/selectEventTopicPageRequest'
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';

type IHaveId = {
  name: string
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

const eventTopic = defineModel('eventTopic')

const authStore = useAuthStore()

const { state } = useAxiosAutoRequest<ResponseData>(async () => {
  const token = await authStore.requireToken()
  return selectEventTopicPageRequest({
    token,
  })
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
    optionValue="id"
    optionLabel="label"
    placeholder="Event Topic"
    class="w-full"
  />
</template>
