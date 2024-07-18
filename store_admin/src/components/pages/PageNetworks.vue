<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import { structureRemote } from '../../remotes/structureRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarNetworks from '../toolbars/ToolbarNetworks.vue';
import GridNetworks from '../grids/GridNetworks.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()
// @ts-ignore
const security = useSecurityContext()

const networkSelectFilter = reactive({
  typeId: undefined,
  shopId: undefined,
  hidden: undefined,
})

const networkSelectSort = reactive({
  id: undefined,
  name: undefined,
  typeId: undefined,
  shopId: undefined,
  hidden: undefined,
})

const networkSelectSelection = ref([])

const networkSelectQuery = useAxiosAutoRequest<any>(structureRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  setProperty(data, 'typeId', networkSelectFilter.typeId)
  setProperty(data, 'shopId', networkSelectFilter.shopId)
  setProperty(data, 'hidden', networkSelectFilter.hidden)
  const params: Record<string, any> = {"size":50,"sort":"id,desc"}
  const sort: string[] = []
  if (networkSelectSort.id != null) {
    sort.push('id,' + networkSelectSort.id)
  }
  if (networkSelectSort.name != null) {
    sort.push('name,' + networkSelectSort.name)
  }
  if (networkSelectSort.typeId != null) {
    sort.push('typeId,' + networkSelectSort.typeId)
  }
  if (networkSelectSort.shopId != null) {
    sort.push('shopId,' + networkSelectSort.shopId)
  }
  if (networkSelectSort.hidden != null) {
    sort.push('hidden,' + networkSelectSort.hidden)
  }
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

  return {
    method: 'POST',
    url: `/system/public_network/select`,
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

const handleRefreshNetworkSelect = () => {
  networkSelectQuery.refresh()
  networkSelectSelection.value = []
}

watch(
  [networkSelectFilter, networkSelectSort],
  handleRefreshNetworkSelect
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Networks"
    />
    <ToolbarNetworks
      class="rounded-none border-0 border-b"
      v-model:selection="networkSelectSelection"
      v-model:filter-by-type-id = networkSelectFilter.typeId
      v-model:sort-by-id = networkSelectSort.id
      v-model:sort-by-name = networkSelectSort.name
      v-model:sort-by-type-id = networkSelectSort.typeId
      v-model:sort-by-hidden = networkSelectSort.hidden
      @refresh="handleRefreshNetworkSelect"
    />
    <GridNetworks
      :state="networkSelectQuery.state"
      v-model:selection="networkSelectSelection"
    />
  </div>
</template>
