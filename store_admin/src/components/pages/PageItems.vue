<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import { productsRemote } from '../../remotes/productsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarItems from '../toolbars/ToolbarItems.vue';
import GridItems from '../grids/GridItems.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()
// @ts-ignore
const security = useSecurityContext()

const itemSelectFilter = reactive({
  shopId: undefined,
})

const itemSelectSort = reactive({
})

const itemSelectSelection = ref([])

const itemSelectQuery = useAxiosAutoRequest<any>(productsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  setProperty(data, 'shopId', itemSelectFilter.shopId)
  const params: Record<string, any> = {}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

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

const handleRefreshItemSelect = () => {
  itemSelectQuery.refresh()
  itemSelectSelection.value = []
}

watch(
  [itemSelectFilter, itemSelectSort],
  handleRefreshItemSelect
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Items"
    />
    <ToolbarItems
      class="rounded-none border-0 border-b"
      v-model:selection="itemSelectSelection"
      v-model:filter-by-shop-id = itemSelectFilter.shopId
      @refresh="handleRefreshItemSelect"
    />
    <GridItems
      :state="itemSelectQuery.state"
      v-model:selection="itemSelectSelection"
    />
  </div>
</template>
