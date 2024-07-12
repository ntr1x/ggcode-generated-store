<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import { eventsRemote } from '../../remotes/eventsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import GridTemplateTypes from '../grids/GridTemplateTypes.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()
// @ts-ignore
const security = useSecurityContext()

const templateTypeSelectFilter = reactive({
})

const templateTypeSelectSort = reactive({
  id: undefined,
  name: undefined,
  description: undefined,
})

const templateTypeSelectSelection = ref([])

const templateTypeSelectQuery = useAxiosRequest<any>(eventsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params: Record<string, any> = {}
  const sort: string[] = []
  if (templateTypeSelectSort.id != null) {
    sort.push('id,' + templateTypeSelectSort.id)
  }
  if (templateTypeSelectSort.name != null) {
    sort.push('name,' + templateTypeSelectSort.name)
  }
  if (templateTypeSelectSort.description != null) {
    sort.push('description,' + templateTypeSelectSort.description)
  }
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

  return {
    method: 'POST',
    url: `/system/public_template_type/select`,
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
  [templateTypeSelectFilter, templateTypeSelectSort],
  () => {
    templateTypeSelectQuery.refresh()
    templateTypeSelectSelection.value = []
  }
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Template Types"
    />
    <GridTemplateTypes
      :state="templateTypeSelectQuery.state"
      v-model:selection="templateTypeSelectSelection"
    />
  </div>
</template>
