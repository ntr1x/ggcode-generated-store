<script setup lang="ts">
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useRoute } from 'vue-router';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { eventsRemote } from '../../remotes/eventsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarSubscriptions from '../toolbars/ToolbarSubscriptions.vue';
import GridSubscriptions from '../grids/GridSubscriptions.vue';

const route = useRoute()
const authStore = useAuthStore()

const subscriptionSelectFilter = reactive({
  customerId: route.params.customerId,
  typeId: undefined,
})

const subscriptionSelectSort = reactive({
  id: undefined,
  createdAt: undefined,
})

const subscriptionSelectSelection = ref([])

const subscriptionSelectQuery = useAxiosRequest<any>(eventsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  setProperty(data, 'customerId', subscriptionSelectFilter.customerId)
  setProperty(data, 'typeId', subscriptionSelectFilter.typeId)
  const params = {}
  const sort: string[] = []
  if (subscriptionSelectSort.id != null) {
    sort.push('id,' + subscriptionSelectSort.id)
  }
  if (subscriptionSelectSort.createdAt != null) {
    sort.push('createdAt,' + subscriptionSelectSort.createdAt)
  }
  setProperty(params, 'sort', sort.length > 0 ? sort : undefined)

  return {
    method: 'POST',
    url: `/system/public_subscription/select`,
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
  [subscriptionSelectFilter, subscriptionSelectSort],
  () => {
    subscriptionSelectQuery.refresh()
    subscriptionSelectSelection.value = []
  }
)
</script>

<template>
  <div class="flex flex-col overflow-hidden">
    <SectionHeading tag="h1" title="Subscriptions" />
    <ToolbarSubscriptions
      class="rounded-none border-0 border-b"
      v-model:selection="subscriptionSelectSelection"
      v-model:filter-by-type-id = subscriptionSelectFilter.typeId
      v-model:sort-by-id = subscriptionSelectSort.id
      v-model:sort-by-createdAt = subscriptionSelectSort.createdAt
      v-model:sort-by-typeId = subscriptionSelectSort.typeId
    />
    <GridSubscriptions
      :state="subscriptionSelectQuery.state"
      v-model:selection="subscriptionSelectSelection"
    />
  </div>
</template>
