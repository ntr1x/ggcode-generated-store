<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { eventsRemote } from '../../remotes/eventsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import GridEventTopics from '../grids/GridEventTopics.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()

const eventTopicSelectFilter = reactive({
})

const eventTopicSelectSort = reactive({
  name: undefined,
  description: undefined,
})

const eventTopicSelectSelection = ref([])

const eventTopicSelectQuery = useAxiosRequest<any>(eventsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params: Record<string, any> = {}
  const sort: string[] = []
  if (eventTopicSelectSort.name != null) {
    sort.push('name,' + eventTopicSelectSort.name)
  }
  if (eventTopicSelectSort.description != null) {
    sort.push('description,' + eventTopicSelectSort.description)
  }
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

  return {
    method: 'POST',
    url: `/system/public_event_topic_info/select`,
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
  [eventTopicSelectFilter, eventTopicSelectSort],
  () => {
    eventTopicSelectQuery.refresh()
    eventTopicSelectSelection.value = []
  }
)
</script>

<template>
  <div class="flex flex-col overflow-hidden">
    <SectionHeading tag="h1" title="Event Topics" />
    <GridEventTopics
      :state="eventTopicSelectQuery.state"
      v-model:selection="eventTopicSelectSelection"
    />
  </div>
</template>
