<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import { productsRemote } from '../../remotes/productsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarPromotions from '../toolbars/ToolbarPromotions.vue';
import GridPromotions from '../grids/GridPromotions.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()
// @ts-ignore
const security = useSecurityContext()

const promotionSelectFilter = reactive({
  promotionType: undefined,
})

const promotionSelectSort = reactive({
})

const promotionSelectSelection = ref([])

const promotionSelectQuery = useAxiosAutoRequest<any>(productsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  setProperty(data, 'typeId', promotionSelectFilter.promotionType)
  const params: Record<string, any> = {}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

  return {
    method: 'POST',
    url: `/system/public_promotion/select`,
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

const handleRefreshPromotionSelect = () => {
  promotionSelectQuery.refresh()
  promotionSelectSelection.value = []
}

watch(
  [promotionSelectFilter, promotionSelectSort],
  handleRefreshPromotionSelect
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Promotions"
    />
    <ToolbarPromotions
      class="rounded-none border-0 border-b"
      v-model:selection="promotionSelectSelection"
      v-model:filter-by-promotion-type = promotionSelectFilter.promotionType
      @refresh="handleRefreshPromotionSelect"
    />
    <GridPromotions
      :state="promotionSelectQuery.state"
      v-model:selection="promotionSelectSelection"
    />
  </div>
</template>
