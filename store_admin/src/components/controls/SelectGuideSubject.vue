<script setup lang="ts">
import { computed } from 'vue';
import Dropdown from 'primevue/dropdown'
import { structureRemote } from '../../remotes/structureRemote'
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';

type IHaveId = {
  id: string
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

const guideSubject = defineModel('guideSubject')

const authStore = useAuthStore()

const { state } = useAxiosAutoRequest<ResponseData>(structureRemote, async () => {
  const token = await authStore.requireToken()
  return {
    method: 'POST',
    url: '/system/public_guide_subject/select',
    data: {},
    params: {"size":50,"sort":"position,asc"},
    headers: {
      Authorization: `Bearer ${token}`
    }
  }
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
    v-model="guideSubject"
    :options="options"
    showClear
    optionValue="id"
    optionLabel="label"
    placeholder="Guide Subject"
    class="w-full"
  />
</template>
