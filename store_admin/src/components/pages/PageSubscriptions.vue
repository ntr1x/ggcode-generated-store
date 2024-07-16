<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import { eventsRemote } from '../../remotes/eventsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarSubscriptions from '../toolbars/ToolbarSubscriptions.vue';
import GridSubscriptions from '../grids/GridSubscriptions.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()
// @ts-ignore
const security = useSecurityContext()

const subscriptionSelectFilter = reactive({
  customerId: route.params.customerId,
  typeId: undefined,
})

const subscriptionSelectSort = reactive({
  id: undefined,
  createdAt: undefined,
  typeId: undefined,
})

const subscriptionSelectSelection = ref([])

const subscriptionSelectQuery = useAxiosAutoRequest<any>(eventsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  setProperty(data, 'customerId', subscriptionSelectFilter.customerId)
  setProperty(data, 'typeId', subscriptionSelectFilter.typeId)
  const params: Record<string, any> = {}
  const sort: string[] = []
  if (subscriptionSelectSort.id != null) {
    sort.push('id,' + subscriptionSelectSort.id)
  }
  if (subscriptionSelectSort.createdAt != null) {
    sort.push('createdAt,' + subscriptionSelectSort.createdAt)
  }
  if (subscriptionSelectSort.typeId != null) {
    sort.push('typeId,' + subscriptionSelectSort.typeId)
  }
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

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

const handleRefreshSubscriptionSelect = () => {
  subscriptionSelectQuery.refresh()
  subscriptionSelectSelection.value = []
}

watch(
  [subscriptionSelectFilter, subscriptionSelectSort],
  handleRefreshSubscriptionSelect
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Subscriptions"
    />
    <ToolbarSubscriptions
      class="rounded-none border-0 border-b"
      v-model:selection="subscriptionSelectSelection"
      v-model:filter-by-customer-id = subscriptionSelectFilter.customerId
      v-model:filter-by-type-id = subscriptionSelectFilter.typeId
      v-model:sort-by-id = subscriptionSelectSort.id
      v-model:sort-by-created-at = subscriptionSelectSort.createdAt
      v-model:sort-by-type-id = subscriptionSelectSort.typeId
      @refresh="handleRefreshSubscriptionSelect"
    />
    <GridSubscriptions
      :state="subscriptionSelectQuery.state"
      v-model:selection="subscriptionSelectSelection"
    />
  </div>
</template>
