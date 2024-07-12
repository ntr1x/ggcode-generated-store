<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import { eventsRemote } from '../../remotes/eventsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarTemplates from '../toolbars/ToolbarTemplates.vue';
import GridTemplates from '../grids/GridTemplates.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()
// @ts-ignore
const security = useSecurityContext()

const templateSelectFilter = reactive({
  typeId: undefined,
  shapeId: undefined,
})

const templateSelectSort = reactive({
  id: undefined,
  name: undefined,
  typeId: undefined,
  shapeId: undefined,
})

const templateSelectSelection = ref([])

const templateSelectQuery = useAxiosRequest<any>(eventsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  setProperty(data, 'typeId', templateSelectFilter.typeId)
  setProperty(data, 'shapeId', templateSelectFilter.shapeId)
  const params: Record<string, any> = {"size":50,"sort":"id,desc"}
  const sort: string[] = []
  if (templateSelectSort.id != null) {
    sort.push('id,' + templateSelectSort.id)
  }
  if (templateSelectSort.name != null) {
    sort.push('name,' + templateSelectSort.name)
  }
  if (templateSelectSort.typeId != null) {
    sort.push('typeId,' + templateSelectSort.typeId)
  }
  if (templateSelectSort.shapeId != null) {
    sort.push('shapeId,' + templateSelectSort.shapeId)
  }
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

  return {
    method: 'POST',
    url: `/system/public_template/select`,
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
  [templateSelectFilter, templateSelectSort],
  () => {
    templateSelectQuery.refresh()
    templateSelectSelection.value = []
  }
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Templates"
    />
    <ToolbarTemplates
      class="rounded-none border-0 border-b"
      v-model:selection="templateSelectSelection"
      v-model:filter-by-type-id = templateSelectFilter.typeId
      v-model:filter-by-shape-id = templateSelectFilter.shapeId
      v-model:sort-by-id = templateSelectSort.id
      v-model:sort-by-name = templateSelectSort.name
      v-model:sort-by-type-id = templateSelectSort.typeId
      v-model:sort-by-shape-id = templateSelectSort.shapeId
    />
    <GridTemplates
      :state="templateSelectQuery.state"
      v-model:selection="templateSelectSelection"
    />
  </div>
</template>
