<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import { structureRemote } from '../../remotes/structureRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarGuides from '../toolbars/ToolbarGuides.vue';
import GridGuides from '../grids/GridGuides.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()
// @ts-ignore
const security = useSecurityContext()

const guideSelectFilter = reactive({
  subjectId: route.params.subjectId,
})

const guideSelectSort = reactive({
  id: undefined,
  name: undefined,
  position: undefined,
  subjectId: undefined,
  createdAt: undefined,
  updatedAt: undefined,
})

const guideSelectSelection = ref([])

const guideSelectQuery = useAxiosAutoRequest<any>(structureRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  setProperty(data, 'subjectId', guideSelectFilter.subjectId)
  const params: Record<string, any> = {"size":50,"sort":"position,asc"}
  const sort: string[] = []
  if (guideSelectSort.id != null) {
    sort.push('id,' + guideSelectSort.id)
  }
  if (guideSelectSort.name != null) {
    sort.push('name,' + guideSelectSort.name)
  }
  if (guideSelectSort.position != null) {
    sort.push('position,' + guideSelectSort.position)
  }
  if (guideSelectSort.subjectId != null) {
    sort.push('subjectId,' + guideSelectSort.subjectId)
  }
  if (guideSelectSort.createdAt != null) {
    sort.push('createdAt,' + guideSelectSort.createdAt)
  }
  if (guideSelectSort.updatedAt != null) {
    sort.push('updatedAt,' + guideSelectSort.updatedAt)
  }
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

  return {
    method: 'POST',
    url: `/system/public_guide/select`,
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

const handleRefreshGuideSelect = () => {
  guideSelectQuery.refresh()
  guideSelectSelection.value = []
}

watch(
  [guideSelectFilter, guideSelectSort],
  handleRefreshGuideSelect
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Guides"
    />
    <ToolbarGuides
      class="rounded-none border-0 border-b"
      v-model:selection="guideSelectSelection"
      v-model:filter-by-subject-id = guideSelectFilter.subjectId
      v-model:sort-by-id = guideSelectSort.id
      v-model:sort-by-name = guideSelectSort.name
      v-model:sort-by-position = guideSelectSort.position
      v-model:sort-by-subject-id = guideSelectSort.subjectId
      @refresh="handleRefreshGuideSelect"
    />
    <GridGuides
      :state="guideSelectQuery.state"
      v-model:selection="guideSelectSelection"
    />
  </div>
</template>
