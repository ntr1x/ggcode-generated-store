<script setup lang="ts">
import { computed } from 'vue';
import { selectEventTypePageRequest } from '../../requests/selectEventTypePageRequest'
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';
import PlatformDialogFilter, { type Option } from '../dialogs/PlatformDialogFilter.vue';

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

const model = defineModel<ResponseDataRow | undefined>()

const emit = defineEmits<{
  (e: 'change:option', value: Option): void
}>()

const authStore = useAuthStore()

const { state } = useAxiosAutoRequest<ResponseData>(async () => {
  const token = await authStore.requireToken()
  return selectEventTypePageRequest({
    token,
  })
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
  <PlatformDialogFilter
    title="Event Type"
    :is-loading="state.isLoading"
    :is-loaded="state.isLoaded"
    :is-failed="state.isFailed"
    :options="options"
    v-model="model"
    @change:option="value => emit('change:option', value)"
  />
</template>
