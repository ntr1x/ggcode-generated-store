<script setup lang="ts">
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useRoute } from 'vue-router';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { productsRemote } from '../../remotes/productsRemote';
import { paymentsRemote } from '../../remotes/paymentsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import FieldsetProductInfo from '../fieldsets/FieldsetProductInfo.vue';
import ToolbarPromotionTargets from '../toolbars/ToolbarPromotionTargets.vue';
import GridPromotionTargets from '../grids/GridPromotionTargets.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()

const productGetFilter = reactive({
})

const productGetSort = reactive({
})

const productGetSelection = ref([])

const productGetQuery = useAxiosRequest<any>(productsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params = {}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : undefined)

  return {
    method: 'GET',
    url: `/system/public_product/i/${route.params.productId}`,
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
  [productGetFilter, productGetSort],
  () => {
    productGetQuery.refresh()
    productGetSelection.value = []
  }
)
const promotionTargetSelectFilter = reactive({
  productId: route.params.productId,
  promotionId: route.params.promotionId,
})

const promotionTargetSelectSort = reactive({
})

const promotionTargetSelectSelection = ref([])

const promotionTargetSelectQuery = useAxiosRequest<any>(paymentsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  setProperty(data, 'productId', promotionTargetSelectFilter.productId)
  setProperty(data, 'promotionId', promotionTargetSelectFilter.promotionId)
  const params = {}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : undefined)

  return {
    method: 'POST',
    url: `/system/public_promotion_target/select`,
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
  [promotionTargetSelectFilter, promotionTargetSelectSort],
  () => {
    promotionTargetSelectQuery.refresh()
    promotionTargetSelectSelection.value = []
  }
)
</script>

<template>
  <div class="flex flex-col overflow-auto">
    <SectionHeading tag="h1" title="Product" />
    <FieldsetProductInfo :state="productGetQuery.state"/>
    <SectionHeading tag="h3" title="Promotion Targets" />
    <ToolbarPromotionTargets
      class="rounded-none border-0 border-b"
      v-model:selection="promotionTargetSelectSelection"
      v-model:filter-by-promotion-id = promotionTargetSelectFilter.promotionId
    />
    <GridPromotionTargets
      :state="promotionTargetSelectQuery.state"
      v-model:selection="promotionTargetSelectSelection"
      :scrollable="false"
      :hide-customer="true"
    />
  </div>
</template>
