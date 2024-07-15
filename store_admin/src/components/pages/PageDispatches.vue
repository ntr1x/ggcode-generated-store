<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import { eventsRemote } from '../../remotes/eventsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarDispatches from '../toolbars/ToolbarDispatches.vue';
import GridDispatches from '../grids/GridDispatches.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()
// @ts-ignore
const security = useSecurityContext()

const dispatchSelectFilter = reactive({
  customerId: route.params.customerId,
  typeId: undefined,
  statusId: undefined,
})

const dispatchSelectSort = reactive({
  id: undefined,
  typeId: undefined,
  statusId: undefined,
  createdAt: undefined,
  updatedAt: undefined,
})

const dispatchSelectSelection = ref([])

const dispatchSelectQuery = useAxiosRequest<any>(eventsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  setProperty(data, 'customerId', dispatchSelectFilter.customerId)
  setProperty(data, 'typeId', dispatchSelectFilter.typeId)
  setProperty(data, 'statusId', dispatchSelectFilter.statusId)
  const params: Record<string, any> = {"size":50,"sort":"createdAt,desc"}
  const sort: string[] = []
  if (dispatchSelectSort.id != null) {
    sort.push('id,' + dispatchSelectSort.id)
  }
  if (dispatchSelectSort.typeId != null) {
    sort.push('typeId,' + dispatchSelectSort.typeId)
  }
  if (dispatchSelectSort.statusId != null) {
    sort.push('statusId,' + dispatchSelectSort.statusId)
  }
  if (dispatchSelectSort.createdAt != null) {
    sort.push('createdAt,' + dispatchSelectSort.createdAt)
  }
  if (dispatchSelectSort.updatedAt != null) {
    sort.push('updatedAt,' + dispatchSelectSort.updatedAt)
  }
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

  return {
    method: 'POST',
    url: `/system/public_dispatch/select`,
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
  [dispatchSelectFilter, dispatchSelectSort],
  () => {
    dispatchSelectQuery.refresh()
    dispatchSelectSelection.value = []
  }
)

function handleRefreshDispatches() {
  dispatchSelectQuery.refresh()
  dispatchSelectSelection.value = []
}

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Dispatches"
    />
    <ToolbarDispatches
      class="rounded-none border-0 border-b"
      v-model:selection="dispatchSelectSelection"
      v-model:filter-by-customer-id = dispatchSelectFilter.customerId
      v-model:filter-by-type-id = dispatchSelectFilter.typeId
      v-model:filter-by-status-id = dispatchSelectFilter.statusId
      v-model:sort-by-id = dispatchSelectSort.id
      v-model:sort-by-created-at = dispatchSelectSort.createdAt
      v-model:sort-by-updated-at = dispatchSelectSort.updatedAt
      v-model:sort-by-type-id = dispatchSelectSort.typeId
      v-model:sort-by-status-id = dispatchSelectSort.statusId
      @refresh="handleRefreshDispatches"
    />
    <GridDispatches
      :state="dispatchSelectQuery.state"
      v-model:selection="dispatchSelectSelection"
    />
  </div>
</template>
