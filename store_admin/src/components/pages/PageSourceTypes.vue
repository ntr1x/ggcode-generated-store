<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { paymentsRemote } from '../../remotes/paymentsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import GridSourceTypes from '../grids/GridSourceTypes.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()

const sourceTypeSelectFilter = reactive({
})

const sourceTypeSelectSort = reactive({
})

const sourceTypeSelectSelection = ref([])

const sourceTypeSelectQuery = useAxiosRequest<any>(paymentsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params: Record<string, any> = {}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

  return {
    method: 'POST',
    url: `/system/public_source_type/select`,
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
  [sourceTypeSelectFilter, sourceTypeSelectSort],
  () => {
    sourceTypeSelectQuery.refresh()
    sourceTypeSelectSelection.value = []
  }
)
</script>

<template>
  <div class="flex flex-col overflow-hidden">
    <SectionHeading tag="h1" title="Source Types" />
    <GridSourceTypes
      :state="sourceTypeSelectQuery.state"
      v-model:selection="sourceTypeSelectSelection"
    />
  </div>
</template>
