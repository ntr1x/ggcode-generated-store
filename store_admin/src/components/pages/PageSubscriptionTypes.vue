<script setup lang="ts">
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useRoute } from 'vue-router';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { eventsRemote } from '../../remotes/eventsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import GridSubscriptionTypes from '../grids/GridSubscriptionTypes.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()

const subscriptionTypeSelectFilter = reactive({
  customerId: route.params.customerId,
  typeId: undefined,
})

const subscriptionTypeSelectSort = reactive({
  id: undefined,
  name: undefined,
  description: undefined,
})

const subscriptionTypeSelectSelection = ref([])

const subscriptionTypeSelectQuery = useAxiosRequest<any>(eventsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  setProperty(data, 'customerId', subscriptionTypeSelectFilter.customerId)
  setProperty(data, 'typeId', subscriptionTypeSelectFilter.typeId)
  const params = {}
  const sort: string[] = []
  if (subscriptionTypeSelectSort.id != null) {
    sort.push('id,' + subscriptionTypeSelectSort.id)
  }
  if (subscriptionTypeSelectSort.name != null) {
    sort.push('name,' + subscriptionTypeSelectSort.name)
  }
  if (subscriptionTypeSelectSort.description != null) {
    sort.push('description,' + subscriptionTypeSelectSort.description)
  }
  setProperty(params, 'sort', sort.length > 0 ? sort : undefined)

  return {
    method: 'POST',
    url: `/system/public_subscription_type/select`,
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
  [subscriptionTypeSelectFilter, subscriptionTypeSelectSort],
  () => {
    subscriptionTypeSelectQuery.refresh()
    subscriptionTypeSelectSelection.value = []
  }
)
</script>

<template>
  <div class="flex flex-col overflow-hidden">
    <SectionHeading tag="h1" title="Subscription Types" />
    <GridSubscriptionTypes
      :state="subscriptionTypeSelectQuery.state"
      v-model:selection="subscriptionTypeSelectSelection"
    />
  </div>
</template>
