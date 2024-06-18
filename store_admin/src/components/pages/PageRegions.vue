<script setup lang="ts">
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useRoute } from 'vue-router';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { productsRemote } from '../../remotes/productsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import GridRegions from '../grids/GridRegions.vue';

const route = useRoute()
const authStore = useAuthStore()

const regionSelectFilter = reactive({
})

const regionSelectSort = reactive({
})

const regionSelectSelection = ref([])

const regionSelectQuery = useAxiosRequest<any>(productsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params = {}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : undefined)

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

watch(
  [regionSelectFilter, regionSelectSort],
  () => {
    regionSelectQuery.refresh()
    regionSelectSelection.value = []
  }
)
</script>

<template>
  <div class="flex flex-col overflow-hidden">
    <SectionHeading tag="h1" title="Regions" />
    <GridRegions
      :state="regionSelectQuery.state"
      v-model:selection="regionSelectSelection"
    />
  </div>
</template>
