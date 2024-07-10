<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { productsRemote } from '../../remotes/productsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarShops from '../toolbars/ToolbarShops.vue';
import GridShops from '../grids/GridShops.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()

const shopSelectFilter = reactive({
  regionId: undefined,
})

const shopSelectSort = reactive({
})

const shopSelectSelection = ref([])

const shopSelectQuery = useAxiosRequest<any>(productsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  setProperty(data, 'regionId', shopSelectFilter.regionId)
  const params: Record<string, any> = {}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

  return {
    method: 'POST',
    url: `/system/public_shop/select`,
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
  [shopSelectFilter, shopSelectSort],
  () => {
    shopSelectQuery.refresh()
    shopSelectSelection.value = []
  }
)
</script>

<template>
  <div class="flex flex-col overflow-hidden">
    <SectionHeading tag="h1" title="Shops" />
    <ToolbarShops
      class="rounded-none border-0 border-b"
      v-model:selection="shopSelectSelection"
      v-model:filter-by-region-id = shopSelectFilter.regionId
    />
    <GridShops
      :state="shopSelectQuery.state"
      v-model:selection="shopSelectSelection"
    />
  </div>
</template>
