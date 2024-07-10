<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { customersRemote } from '../../remotes/customersRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import FieldsetAgentInfo from '../fieldsets/FieldsetAgentInfo.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()

const agentGetFilter = reactive({
})

const agentGetSort = reactive({
})

const agentGetSelection = ref([])

const agentGetQuery = useAxiosRequest<any>(customersRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params: Record<string, any> = {}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

  return {
    method: 'GET',
    url: `/system/public_agent/i/${route.params.agentId}`,
    params,
    data,
    paramsSerializer: {
      indexes: null
    },
    headers: {
      Authorization: `Bearer ${token}`
    }
  }
})

watch(
  [agentGetFilter, agentGetSort],
  () => {
    agentGetQuery.refresh()
    agentGetSelection.value = []
  }
)
</script>

<template>
  <div class="flex flex-col overflow-hidden">
    <SectionHeading tag="h1" title="Agent" />
    <FieldsetAgentInfo :state="agentGetQuery.state"/>
  </div>
</template>
