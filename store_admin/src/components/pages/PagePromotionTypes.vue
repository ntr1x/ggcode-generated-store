<script setup lang="ts">
import { useRoute } from 'vue-router';
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { useAxiosAutoRequest } from '../../hooks/useAxiosAutoRequest';
import { useSecurityContext } from '../../hooks/useSecurityContext';
import { paymentsRemote } from '../../remotes/paymentsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import GridPromotionTypes from '../grids/GridPromotionTypes.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()
// @ts-ignore
const security = useSecurityContext()

const promotionTypeSelectFilter = reactive({
})

const promotionTypeSelectSort = reactive({
})

const promotionTypeSelectSelection = ref([])

const promotionTypeSelectQuery = useAxiosAutoRequest<any>(paymentsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params: Record<string, any> = {}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : params.sort)

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

const handleRefreshPromotionTypeSelect = () => {
  promotionTypeSelectQuery.refresh()
  promotionTypeSelectSelection.value = []
}

watch(
  [promotionTypeSelectFilter, promotionTypeSelectSort],
  handleRefreshPromotionTypeSelect
)

</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Promotion Types"
    />
    <GridPromotionTypes
      :state="promotionTypeSelectQuery.state"
      v-model:selection="promotionTypeSelectSelection"
    />
  </div>
</template>
