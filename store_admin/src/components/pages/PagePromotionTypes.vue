<script setup lang="ts">
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useRoute } from 'vue-router';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { paymentsRemote } from '../../remotes/paymentsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import GridPromotionTypes from '../grids/GridPromotionTypes.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()

const promotionTypeSelectFilter = reactive({
})

const promotionTypeSelectSort = reactive({
})

const promotionTypeSelectSelection = ref([])

const promotionTypeSelectQuery = useAxiosRequest<any>(paymentsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params = {}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : undefined)

  return {
    method: 'POST',
    url: `/system/public_promotion_type/select`,
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
  [promotionTypeSelectFilter, promotionTypeSelectSort],
  () => {
    promotionTypeSelectQuery.refresh()
    promotionTypeSelectSelection.value = []
  }
)
</script>

<template>
  <div class="flex flex-col overflow-hidden">
    <SectionHeading tag="h1" title="Promotion Types" />
    <GridPromotionTypes
      :state="promotionTypeSelectQuery.state"
      v-model:selection="promotionTypeSelectSelection"
    />
  </div>
</template>
