<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import { productsRemote } from '../../remotes/productsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarProducts from '../toolbars/ToolbarProducts.vue';
import GridProducts from '../grids/GridProducts.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()
// @ts-ignore
const security = useSecurityContext()

const productSelectFilter = reactive({
  categoryId: undefined,
})

const productSelectSort = reactive({
  id: undefined,
  name: undefined,
})

const productSelectSelection = ref([])

const productSelectQuery = useAxiosAutoRequest<any>(productsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  setProperty(data, 'categoryId', productSelectFilter.categoryId)
  const params: Record<string, any> = {}
  const sort: string[] = []
  if (productSelectSort.id != null) {
    sort.push('id,' + productSelectSort.id)
  }
  if (productSelectSort.name != null) {
    sort.push('name,' + productSelectSort.name)
  }
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

  return {
    method: 'POST',
    url: `/system/public_product/select`,
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

const handleRefreshProductSelect = () => {
  productSelectQuery.refresh()
  productSelectSelection.value = []
}

watch(
  [productSelectFilter, productSelectSort],
  handleRefreshProductSelect
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Products"
    />
    <ToolbarProducts
      class="rounded-none border-0 border-b"
      v-model:selection="productSelectSelection"
      v-model:filter-by-category-id = productSelectFilter.categoryId
      v-model:sort-by-id = productSelectSort.id
      v-model:sort-by-name = productSelectSort.name
      @refresh="handleRefreshProductSelect"
    />
    <GridProducts
      :state="productSelectQuery.state"
      v-model:selection="productSelectSelection"
    />
  </div>
</template>
