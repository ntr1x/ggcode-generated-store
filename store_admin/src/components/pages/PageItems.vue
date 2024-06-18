<script setup lang="ts">
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useRoute } from 'vue-router';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { productsRemote } from '../../remotes/productsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarItems from '../toolbars/ToolbarItems.vue';
import GridItems from '../grids/GridItems.vue';

const route = useRoute()
const authStore = useAuthStore()

const itemSelectFilter = reactive({
  shopId: undefined,
})

const itemSelectSort = reactive({
})

const itemSelectSelection = ref([])

const itemSelectQuery = useAxiosRequest<any>(productsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  setProperty(data, 'shopId', itemSelectFilter.shopId)
  const params = {}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : undefined)

  return {
    method: 'POST',
    url: `/system/public_item/select`,
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
  [itemSelectFilter, itemSelectSort],
  () => {
    itemSelectQuery.refresh()
    itemSelectSelection.value = []
  }
)
</script>

<template>
  <div class="flex flex-col overflow-hidden">
    <SectionHeading tag="h1" title="Items" />
    <ToolbarItems
      class="rounded-none border-0 border-b"
      v-model:selection="itemSelectSelection"
      v-model:filter-by-shop-id = itemSelectFilter.shopId
    />
    <GridItems
      :state="itemSelectQuery.state"
      v-model:selection="itemSelectSelection"
    />
  </div>
</template>
