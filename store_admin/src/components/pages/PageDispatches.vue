<script setup lang="ts">
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useRoute } from 'vue-router';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { eventsRemote } from '../../remotes/eventsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarDispatches from '../toolbars/ToolbarDispatches.vue';
import GridDispatches from '../grids/GridDispatches.vue';

const route = useRoute()
const authStore = useAuthStore()

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
  const params = {
  "sort": "created_at,desc"
}
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
  setProperty(params, 'sort', sort.length > 0 ? sort : undefined)

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
</script>

<template>
  <div class="flex flex-col overflow-hidden">
    <SectionHeading tag="h1" title="Dispatches" />
    <ToolbarDispatches
      class="rounded-none border-0 border-b"
      v-model:selection="dispatchSelectSelection"
      v-model:filter-by-type-id = dispatchSelectFilter.typeId
      v-model:filter-by-status-id = dispatchSelectFilter.statusId
      v-model:sort-by-id = dispatchSelectSort.id
      v-model:sort-by-createdAt = dispatchSelectSort.createdAt
      v-model:sort-by-updatedAt = dispatchSelectSort.updatedAt
      v-model:sort-by-typeId = dispatchSelectSort.typeId
      v-model:sort-by-statusId = dispatchSelectSort.statusId
    />
    <GridDispatches
      :state="dispatchSelectQuery.state"
      v-model:selection="dispatchSelectSelection"
    />
  </div>
</template>
