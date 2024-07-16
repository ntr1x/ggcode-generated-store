<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import { eventsRemote } from '../../remotes/eventsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import GridTemplateShapes from '../grids/GridTemplateShapes.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()
// @ts-ignore
const security = useSecurityContext()

const templateShapeSelectFilter = reactive({
})

const templateShapeSelectSort = reactive({
  id: undefined,
  name: undefined,
  description: undefined,
})

const templateShapeSelectSelection = ref([])

const templateShapeSelectQuery = useAxiosAutoRequest<any>(eventsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params: Record<string, any> = {}
  const sort: string[] = []
  if (templateShapeSelectSort.id != null) {
    sort.push('id,' + templateShapeSelectSort.id)
  }
  if (templateShapeSelectSort.name != null) {
    sort.push('name,' + templateShapeSelectSort.name)
  }
  if (templateShapeSelectSort.description != null) {
    sort.push('description,' + templateShapeSelectSort.description)
  }
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

  return {
    method: 'POST',
    url: `/system/public_template_shape/select`,
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

const handleRefreshTemplateShapeSelect = () => {
  templateShapeSelectQuery.refresh()
  templateShapeSelectSelection.value = []
}

watch(
  [templateShapeSelectFilter, templateShapeSelectSort],
  handleRefreshTemplateShapeSelect
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Template Shapes"
    />
    <GridTemplateShapes
      :state="templateShapeSelectQuery.state"
      v-model:selection="templateShapeSelectSelection"
    />
  </div>
</template>
