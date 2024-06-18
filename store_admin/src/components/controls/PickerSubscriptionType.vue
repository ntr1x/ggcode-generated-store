<script setup lang="ts">
import { computed } from 'vue';
import { eventsRemote } from '../../remotes/eventsRemote'
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import FilterDialog, { type Option } from '../dialogs/FilterDialog.vue';

const model = defineModel()

const emit = defineEmits<{
  (e: 'change:option', value: Option): void
}>()

const authStore = useAuthStore()

export type ResponseDataRow = {
  id: string
  name: string
  description: string
}

export type ResponseData = {
  content: ResponseDataRow[]
}

const { state } = useAxiosRequest<ResponseData>(eventsRemote, async () => {
  const token = await authStore.requireToken()
  return {
    method: 'POST',
    url: '/system/public_subscription_type/select',
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
    key: `item-${item.id}`,
    title: item.name,
    description: item.description,
    value: item.id
  }))
})

</script>

<template>
  <FilterDialog
    title="Subscription Type"
    :is-loading="state.isLoading"
    :is-loaded="state.isLoaded"
    :is-failed="state.isFailed"
    :options="options"
    v-model="model"
    @change:option="value => emit('change:option', value)"
  />
</template>
