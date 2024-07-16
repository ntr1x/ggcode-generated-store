<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import { customersRemote } from '../../remotes/customersRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import GridAgents from '../grids/GridAgents.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()
// @ts-ignore
const security = useSecurityContext()

const agentSelectFilter = reactive({
  customerId: route.params.customerId,
})

const agentSelectSort = reactive({
})

const agentSelectSelection = ref([])

const agentSelectQuery = useAxiosAutoRequest<any>(customersRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  setProperty(data, 'customerId', agentSelectFilter.customerId)
  const params: Record<string, any> = {"size":50}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

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

const handleRefreshAgentSelect = () => {
  agentSelectQuery.refresh()
  agentSelectSelection.value = []
}

watch(
  [agentSelectFilter, agentSelectSort],
  handleRefreshAgentSelect
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-auto">
    <SectionHeading
      tag="h1"
      title="Agents"
    />
    <GridAgents
      :state="agentSelectQuery.state"
      v-model:selection="agentSelectSelection"
    />
  </div>
</template>
