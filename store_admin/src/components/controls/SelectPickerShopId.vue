<script setup lang="ts">
import { computed } from 'vue';
import { selectShopPageRequest } from '../../requests/selectShopPageRequest'
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';
import PlatformDialogFilter, { type Option } from '../dialogs/PlatformDialogFilter.vue';

type IHaveId = {
  id: string
}

type IHaveName = {
  name?: string
}

type IHaveDescription = {
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
  return selectShopPageRequest({
    token,
  })
})

const options = computed(() => {
  const items = state.data?.content || []
  return items.map(item => ({
    key: `item-${item.id}`,
    title: item.name,
    value: item.id
  }))
})

</script>

<template>
  <PlatformDialogFilter
    title="Shop Id"
    :is-loading="state.isLoading"
    :is-loaded="state.isLoaded"
    :is-failed="state.isFailed"
    :options="options"
    v-model="model"
    @change:option="value => emit('change:option', value)"
  />
</template>
