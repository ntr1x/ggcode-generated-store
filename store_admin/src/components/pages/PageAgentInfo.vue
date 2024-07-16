<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import { customersRemote } from '../../remotes/customersRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import FieldsetAgentInfo from '../fieldsets/FieldsetAgentInfo.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()
// @ts-ignore
const security = useSecurityContext()

const agentGetFilter = reactive({
})

const agentGetSort = reactive({
})

const agentGetSelection = ref([])

const agentGetQuery = useAxiosAutoRequest<any>(customersRemote, async () => {
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

const handleRefreshAgentGet = () => {
  agentGetQuery.refresh()
  agentGetSelection.value = []
}

watch(
  [agentGetFilter, agentGetSort],
  handleRefreshAgentGet
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Agent"
    />
    <FieldsetAgentInfo
      :state="agentGetQuery.state"
    />
  </div>
</template>
