<script setup lang="ts">
import { computed } from 'vue';
import { eventsRemote } from '../../remotes/eventsRemote'
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import FilterDialog, { type Option } from '../dialogs/FilterDialog.vue';

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

const model = defineModel<ResponseDataRow | undefined>()

const emit = defineEmits<{
  (e: 'change:option', value: Option): void
}>()

const authStore = useAuthStore()

const { state } = useAxiosRequest<ResponseData>(eventsRemote, async () => {
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
    key: `item-${item.name}`,
    title: item.name,
    description: item.description,
    value: item.name
  }))
})

</script>

<template>
  <FilterDialog
    title="Event Source"
    :is-loading="state.isLoading"
    :is-loaded="state.isLoaded"
    :is-failed="state.isFailed"
    :options="options"
    v-model="model"
    @change:option="value => emit('change:option', value)"
  />
</template>
