<script setup lang="ts">
import { computed } from 'vue';
import { productsRemote } from '../../remotes/productsRemote'
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';
import PlatformDialogFilter, { type Option } from '../dialogs/PlatformDialogFilter.vue';

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

const model = defineModel<ResponseDataRow | undefined>()

const emit = defineEmits<{
  (e: 'change:option', value: Option): void
}>()

const authStore = useAuthStore()

const { state } = useAxiosAutoRequest<ResponseData>(productsRemote, async () => {
  const token = await authStore.requireToken()
  return {
    method: 'POST',
    url: '/system/public_product/select',
    data: {},
    params: {"size":50,"sort":"name,asc"},
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
    value: item.id
  }))
})

</script>

<template>
  <PlatformDialogFilter
    title="Product Id"
    :is-loading="state.isLoading"
    :is-loaded="state.isLoaded"
    :is-failed="state.isFailed"
    :options="options"
    v-model="model"
    @change:option="value => emit('change:option', value)"
  />
</template>
