<script setup lang="ts">
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useRoute } from 'vue-router';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { productsRemote } from '../../remotes/productsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import ToolbarPromotions from '../toolbars/ToolbarPromotions.vue';
import GridPromotions from '../grids/GridPromotions.vue';

const route = useRoute()
const authStore = useAuthStore()

const promotionSelectFilter = reactive({
  promotionType: undefined,
})

const promotionSelectSort = reactive({
})

const promotionSelectSelection = ref([])

const promotionSelectQuery = useAxiosRequest<any>(productsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  setProperty(data, 'typeId', promotionSelectFilter.promotionType)
  const params = {}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : undefined)

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

watch(
  [promotionSelectFilter, promotionSelectSort],
  () => {
    promotionSelectQuery.refresh()
    promotionSelectSelection.value = []
  }
)
</script>

<template>
  <div class="flex flex-col overflow-hidden">
    <SectionHeading tag="h1" title="Promotions" />
    <ToolbarPromotions
      class="rounded-none border-0 border-b"
      v-model:selection="promotionSelectSelection"
      v-model:filter-by-promotion-type = promotionSelectFilter.promotionType
    />
    <GridPromotions
      :state="promotionSelectQuery.state"
      v-model:selection="promotionSelectSelection"
    />
  </div>
</template>
