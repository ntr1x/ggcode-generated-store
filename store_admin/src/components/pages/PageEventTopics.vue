<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import { eventsRemote } from '../../remotes/eventsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import GridEventTopics from '../grids/GridEventTopics.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()
// @ts-ignore
const security = useSecurityContext()

const eventTopicSelectFilter = reactive({
})

const eventTopicSelectSort = reactive({
  name: undefined,
  description: undefined,
})

const eventTopicSelectSelection = ref([])

const eventTopicSelectQuery = useAxiosAutoRequest<any>(eventsRemote, async () => {
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

const handleRefreshEventTopicSelect = () => {
  eventTopicSelectQuery.refresh()
  eventTopicSelectSelection.value = []
}

watch(
  [eventTopicSelectFilter, eventTopicSelectSort],
  handleRefreshEventTopicSelect
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Event Topics"
    />
    <GridEventTopics
      :state="eventTopicSelectQuery.state"
      v-model:selection="eventTopicSelectSelection"
    />
  </div>
</template>
