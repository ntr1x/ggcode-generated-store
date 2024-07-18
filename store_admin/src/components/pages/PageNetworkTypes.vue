<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import { structureRemote } from '../../remotes/structureRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import GridNetworkTypes from '../grids/GridNetworkTypes.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()
// @ts-ignore
const security = useSecurityContext()

const networkTypeSelectFilter = reactive({
})

const networkTypeSelectSort = reactive({
  id: undefined,
  name: undefined,
  description: undefined,
})

const networkTypeSelectSelection = ref([])

const networkTypeSelectQuery = useAxiosAutoRequest<any>(structureRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params: Record<string, any> = {}
  const sort: string[] = []
  if (networkTypeSelectSort.id != null) {
    sort.push('id,' + networkTypeSelectSort.id)
  }
  if (networkTypeSelectSort.name != null) {
    sort.push('name,' + networkTypeSelectSort.name)
  }
  if (networkTypeSelectSort.description != null) {
    sort.push('description,' + networkTypeSelectSort.description)
  }
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

  return {
    method: 'POST',
    url: `/system/public_network_type/select`,
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

const handleRefreshNetworkTypeSelect = () => {
  networkTypeSelectQuery.refresh()
  networkTypeSelectSelection.value = []
}

watch(
  [networkTypeSelectFilter, networkTypeSelectSort],
  handleRefreshNetworkTypeSelect
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Network Types"
    />
    <GridNetworkTypes
      :state="networkTypeSelectQuery.state"
      v-model:selection="networkTypeSelectSelection"
    />
  </div>
</template>
