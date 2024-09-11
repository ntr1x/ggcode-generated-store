<script setup lang="ts">
import { computed } from 'vue';
import Dropdown from 'primevue/dropdown'
import { selectTemplateShapePageRequest } from '../../requests/selectTemplateShapePageRequest'
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';

type IHaveId = {
  id: string
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

const templateShape = defineModel('templateShape')

const authStore = useAuthStore()

const { state } = useAxiosAutoRequest<ResponseData>(async () => {
  const token = await authStore.requireToken()
  return selectTemplateShapePageRequest({
    token,
  })
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
    v-model="templateShape"
    :options="options"
    showClear
    optionValue="id"
    optionLabel="label"
    placeholder="Template Shape"
    class="w-full"
  />
</template>
