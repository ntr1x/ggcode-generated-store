<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import { structureRemote } from '../../remotes/structureRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarGuideSubjects from '../toolbars/ToolbarGuideSubjects.vue';
import GridGuideSubjects from '../grids/GridGuideSubjects.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()
// @ts-ignore
const security = useSecurityContext()

const guideSubjectSelectFilter = reactive({
})

const guideSubjectSelectSort = reactive({
  id: undefined,
  name: undefined,
  position: undefined,
  createdAt: undefined,
  updatedAt: undefined,
})

const guideSubjectSelectSelection = ref([])

const guideSubjectSelectQuery = useAxiosAutoRequest<any>(structureRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params: Record<string, any> = {"size":50,"sort":"position,asc"}
  const sort: string[] = []
  if (guideSubjectSelectSort.id != null) {
    sort.push('id,' + guideSubjectSelectSort.id)
  }
  if (guideSubjectSelectSort.name != null) {
    sort.push('name,' + guideSubjectSelectSort.name)
  }
  if (guideSubjectSelectSort.position != null) {
    sort.push('position,' + guideSubjectSelectSort.position)
  }
  if (guideSubjectSelectSort.createdAt != null) {
    sort.push('createdAt,' + guideSubjectSelectSort.createdAt)
  }
  if (guideSubjectSelectSort.updatedAt != null) {
    sort.push('updatedAt,' + guideSubjectSelectSort.updatedAt)
  }
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

  return {
    method: 'POST',
    url: `/system/public_guide_subject/select`,
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

const handleRefreshGuideSubjectSelect = () => {
  guideSubjectSelectQuery.refresh()
  guideSubjectSelectSelection.value = []
}

watch(
  [guideSubjectSelectFilter, guideSubjectSelectSort],
  handleRefreshGuideSubjectSelect
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Guide Subjects"
    />
    <ToolbarGuideSubjects
      class="rounded-none border-0 border-b"
      v-model:selection="guideSubjectSelectSelection"
      v-model:sort-by-id = guideSubjectSelectSort.id
      v-model:sort-by-name = guideSubjectSelectSort.name
      v-model:sort-by-position = guideSubjectSelectSort.position
      @refresh="handleRefreshGuideSubjectSelect"
    />
    <GridGuideSubjects
      :state="guideSubjectSelectQuery.state"
      v-model:selection="guideSubjectSelectSelection"
    />
  </div>
</template>
