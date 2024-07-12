<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import { eventsRemote } from '../../remotes/eventsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarEvents from '../toolbars/ToolbarEvents.vue';
import GridEvents from '../grids/GridEvents.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()
// @ts-ignore
const security = useSecurityContext()

const eventSelectFilter = reactive({
  eventSource: undefined,
  eventType: undefined,
  eventTopic: undefined,
})

const eventSelectSort = reactive({
  id: undefined,
  ceSource: undefined,
  ceType: undefined,
  topic: undefined,
  createdAt: undefined,
})

const eventSelectSelection = ref([])

const eventSelectQuery = useAxiosRequest<any>(eventsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  setProperty(data, 'ceSource', eventSelectFilter.eventSource)
  setProperty(data, 'ceType', eventSelectFilter.eventType)
  setProperty(data, 'topic', eventSelectFilter.eventTopic)
  const params: Record<string, any> = {"size":50,"sort":"createdAt,desc"}
  const sort: string[] = []
  if (eventSelectSort.id != null) {
    sort.push('id,' + eventSelectSort.id)
  }
  if (eventSelectSort.ceSource != null) {
    sort.push('ceSource,' + eventSelectSort.ceSource)
  }
  if (eventSelectSort.ceType != null) {
    sort.push('ceType,' + eventSelectSort.ceType)
  }
  if (eventSelectSort.topic != null) {
    sort.push('topic,' + eventSelectSort.topic)
  }
  if (eventSelectSort.createdAt != null) {
    sort.push('createdAt,' + eventSelectSort.createdAt)
  }
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

  return {
    method: 'POST',
    url: `/system/public_event/select`,
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
  [eventSelectFilter, eventSelectSort],
  () => {
    eventSelectQuery.refresh()
    eventSelectSelection.value = []
  }
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Events"
    />
    <ToolbarEvents
      class="rounded-none border-0 border-b"
      v-model:selection="eventSelectSelection"
      v-model:filter-by-event-source = eventSelectFilter.eventSource
      v-model:filter-by-event-topic = eventSelectFilter.eventTopic
      v-model:filter-by-event-type = eventSelectFilter.eventType
      v-model:sort-by-id = eventSelectSort.id
      v-model:sort-by-ce-source = eventSelectSort.ceSource
      v-model:sort-by-ce-type = eventSelectSort.ceType
      v-model:sort-by-topic = eventSelectSort.topic
      v-model:sort-by-created-at = eventSelectSort.createdAt
    />
    <GridEvents
      :state="eventSelectQuery.state"
      v-model:selection="eventSelectSelection"
    />
  </div>
</template>
