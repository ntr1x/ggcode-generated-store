<script setup lang="ts">
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useRoute } from 'vue-router';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { eventsRemote } from '../../remotes/eventsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import GridEventSources from '../grids/GridEventSources.vue';

const route = useRoute()
const authStore = useAuthStore()

const eventSourceSelectFilter = reactive({
})

const eventSourceSelectSort = reactive({
  name: undefined,
  description: undefined,
})

const eventSourceSelectSelection = ref([])

const eventSourceSelectQuery = useAxiosRequest<any>(eventsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params = {}
  const sort: string[] = []
  if (eventSourceSelectSort.name != null) {
    sort.push('name,' + eventSourceSelectSort.name)
  }
  if (eventSourceSelectSort.description != null) {
    sort.push('description,' + eventSourceSelectSort.description)
  }
  setProperty(params, 'sort', sort.length > 0 ? sort : undefined)

  return {
    method: 'POST',
    url: `/system/public_event_source_info/select`,
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
  [eventSourceSelectFilter, eventSourceSelectSort],
  () => {
    eventSourceSelectQuery.refresh()
    eventSourceSelectSelection.value = []
  }
)
</script>

<template>
  <div class="flex flex-col overflow-hidden">
    <SectionHeading tag="h1" title="Event Sources" />
    <GridEventSources
      :state="eventSourceSelectQuery.state"
      v-model:selection="eventSourceSelectSelection"
    />
  </div>
</template>
