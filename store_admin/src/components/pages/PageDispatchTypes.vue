<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import { eventsRemote } from '../../remotes/eventsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import GridDispatchTypes from '../grids/GridDispatchTypes.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()
// @ts-ignore
const security = useSecurityContext()

const dispatchTypeSelectFilter = reactive({
})

const dispatchTypeSelectSort = reactive({
  id: undefined,
  name: undefined,
  description: undefined,
})

const dispatchTypeSelectSelection = ref([])

const dispatchTypeSelectQuery = useAxiosAutoRequest<any>(eventsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params: Record<string, any> = {}
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
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

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

const handleRefreshDispatchTypeSelect = () => {
  dispatchTypeSelectQuery.refresh()
  dispatchTypeSelectSelection.value = []
}

watch(
  [dispatchTypeSelectFilter, dispatchTypeSelectSort],
  handleRefreshDispatchTypeSelect
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Dispatch Types"
    />
    <GridDispatchTypes
      :state="dispatchTypeSelectQuery.state"
      v-model:selection="dispatchTypeSelectSelection"
    />
  </div>
</template>
