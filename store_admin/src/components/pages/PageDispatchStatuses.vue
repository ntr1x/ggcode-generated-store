<script setup lang="ts">
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useRoute } from 'vue-router';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { eventsRemote } from '../../remotes/eventsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import GridDispatchStatuses from '../grids/GridDispatchStatuses.vue';

const route = useRoute()
const authStore = useAuthStore()

const dispatchStatusSelectFilter = reactive({
})

const dispatchStatusSelectSort = reactive({
  id: undefined,
  name: undefined,
  description: undefined,
})

const dispatchStatusSelectSelection = ref([])

const dispatchStatusSelectQuery = useAxiosRequest<any>(eventsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params = {}
  const sort: string[] = []
  if (dispatchStatusSelectSort.id != null) {
    sort.push('id,' + dispatchStatusSelectSort.id)
  }
  if (dispatchStatusSelectSort.name != null) {
    sort.push('name,' + dispatchStatusSelectSort.name)
  }
  if (dispatchStatusSelectSort.description != null) {
    sort.push('description,' + dispatchStatusSelectSort.description)
  }
  setProperty(params, 'sort', sort.length > 0 ? sort : undefined)

  return {
    method: 'POST',
    url: `/system/public_dispatch_status/select`,
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
  [dispatchStatusSelectFilter, dispatchStatusSelectSort],
  () => {
    dispatchStatusSelectQuery.refresh()
    dispatchStatusSelectSelection.value = []
  }
)
</script>

<template>
  <div class="flex flex-col overflow-hidden">
    <SectionHeading tag="h1" title="Dispatch Statuses" />
    <GridDispatchStatuses
      :state="dispatchStatusSelectQuery.state"
      v-model:selection="dispatchStatusSelectSelection"
    />
  </div>
</template>
