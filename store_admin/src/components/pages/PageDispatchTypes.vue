<script setup lang="ts">
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useRoute } from 'vue-router';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { eventsRemote } from '../../remotes/eventsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import GridDispatchTypes from '../grids/GridDispatchTypes.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()

const dispatchTypeSelectFilter = reactive({
})

const dispatchTypeSelectSort = reactive({
  id: undefined,
  name: undefined,
  description: undefined,
})

const dispatchTypeSelectSelection = ref([])

const dispatchTypeSelectQuery = useAxiosRequest<any>(eventsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params = {}
  const sort: string[] = []
  if (dispatchTypeSelectSort.id != null) {
    sort.push('id,' + dispatchTypeSelectSort.id)
  }
  if (dispatchTypeSelectSort.name != null) {
    sort.push('name,' + dispatchTypeSelectSort.name)
  }
  if (dispatchTypeSelectSort.description != null) {
    sort.push('description,' + dispatchTypeSelectSort.description)
  }
  setProperty(params, 'sort', sort.length > 0 ? sort : undefined)

  return {
    method: 'POST',
    url: `/system/public_dispatch_type/select`,
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
  [dispatchTypeSelectFilter, dispatchTypeSelectSort],
  () => {
    dispatchTypeSelectQuery.refresh()
    dispatchTypeSelectSelection.value = []
  }
)
</script>

<template>
  <div class="flex flex-col overflow-hidden">
    <SectionHeading tag="h1" title="Dispatch Types" />
    <GridDispatchTypes
      :state="dispatchTypeSelectQuery.state"
      v-model:selection="dispatchTypeSelectSelection"
    />
  </div>
</template>
