<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import { eventsRemote } from '../../remotes/eventsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import GridEventTypes from '../grids/GridEventTypes.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()
// @ts-ignore
const security = useSecurityContext()

const eventTypeSelectFilter = reactive({
})

const eventTypeSelectSort = reactive({
  name: undefined,
  description: undefined,
})

const eventTypeSelectSelection = ref([])

const eventTypeSelectQuery = useAxiosRequest<any>(eventsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params: Record<string, any> = {}
  const sort: string[] = []
  if (eventTypeSelectSort.name != null) {
    sort.push('name,' + eventTypeSelectSort.name)
  }
  if (eventTypeSelectSort.description != null) {
    sort.push('description,' + eventTypeSelectSort.description)
  }
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

  return {
    method: 'POST',
    url: `/system/public_event_type_info/select`,
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
  [eventTypeSelectFilter, eventTypeSelectSort],
  () => {
    eventTypeSelectQuery.refresh()
    eventTypeSelectSelection.value = []
  }
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Event Types"
    />
    <GridEventTypes
      :state="eventTypeSelectQuery.state"
      v-model:selection="eventTypeSelectSelection"
    />
  </div>
</template>
