<script setup lang="ts">
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useRoute } from 'vue-router';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { customersRemote } from '../../remotes/customersRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import GridAgents from '../grids/GridAgents.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()

const agentSelectFilter = reactive({
  customerId: route.params.customerId,
})

const agentSelectSort = reactive({
})

const agentSelectSelection = ref([])

const agentSelectQuery = useAxiosRequest<any>(customersRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  setProperty(data, 'customerId', agentSelectFilter.customerId)
  const params = {
  "size": 50,
  "sort": "created_at,asc"
}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : undefined)

  return {
    method: 'POST',
    url: `/system/public_agent/select`,
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
  [agentSelectFilter, agentSelectSort],
  () => {
    agentSelectQuery.refresh()
    agentSelectSelection.value = []
  }
)
</script>

<template>
  <div class="flex flex-col overflow-auto">
    <SectionHeading tag="h1" title="Agents" />
    <GridAgents
      :state="agentSelectQuery.state"
      v-model:selection="agentSelectSelection"
    />
  </div>
</template>
