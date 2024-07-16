<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import { productsRemote } from '../../remotes/productsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import GridRegions from '../grids/GridRegions.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()
// @ts-ignore
const security = useSecurityContext()

const regionSelectFilter = reactive({
})

const regionSelectSort = reactive({
})

const regionSelectSelection = ref([])

const regionSelectQuery = useAxiosAutoRequest<any>(productsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params: Record<string, any> = {}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

  return {
    method: 'POST',
    url: `/system/public_region/select`,
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

const handleRefreshRegionSelect = () => {
  regionSelectQuery.refresh()
  regionSelectSelection.value = []
}

watch(
  [regionSelectFilter, regionSelectSort],
  handleRefreshRegionSelect
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Regions"
    />
    <GridRegions
      :state="regionSelectQuery.state"
      v-model:selection="regionSelectSelection"
    />
  </div>
</template>
