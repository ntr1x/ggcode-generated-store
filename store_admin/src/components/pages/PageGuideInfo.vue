<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import { structureRemote } from '../../remotes/structureRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import FieldsetGuideGuideInfo from '../fieldsets/FieldsetGuideGuideInfo.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()
// @ts-ignore
const security = useSecurityContext()

const guideGetFilter = reactive({
})

const guideGetSort = reactive({
})

const guideGetSelection = ref([])

const guideGetQuery = useAxiosAutoRequest<any>(structureRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params: Record<string, any> = {}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

  return {
    method: 'GET',
    url: `/system/public_guide/i/${route.params.guideId}`,
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

const handleRefreshGuideGet = () => {
  guideGetQuery.refresh()
  guideGetSelection.value = []
}

watch(
  [guideGetFilter, guideGetSort],
  handleRefreshGuideGet
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-auto">
    <SectionHeading
      tag="h1"
      title="Guide"
    />
    <FieldsetGuideGuideInfo
      :state="guideGetQuery.state"
    />
  </div>
</template>
