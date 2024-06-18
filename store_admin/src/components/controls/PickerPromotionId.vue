<script setup lang="ts">
import { computed } from 'vue';
import { productsRemote } from '../../remotes/productsRemote'
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
}

export type ResponseData = {
  content: ResponseDataRow[]
}

const { state } = useAxiosRequest<ResponseData>(productsRemote, async () => {
  const token = await authStore.requireToken()
  return {
    method: 'POST',
    url: '/system/public_promotion/select',
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
    value: item.id
  }))
})

</script>

<template>
  <FilterDialog
    title="Promotion Id"
    :is-loading="state.isLoading"
    :is-loaded="state.isLoaded"
    :is-failed="state.isFailed"
    :options="options"
    v-model="model"
    @change:option="value => emit('change:option', value)"
  />
</template>
