<script setup lang="ts">
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useRoute } from 'vue-router';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import { productsRemote } from '../../remotes/productsRemote';
import SectionHeading from '../partials/SectionHeading.vue';
import GridCategories from '../grids/GridCategories.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()

const categorySelectFilter = reactive({
})

const categorySelectSort = reactive({
})

const categorySelectSelection = ref([])

const categorySelectQuery = useAxiosRequest<any>(productsRemote, async () => {
  const token = await authStore.requireToken()
  const data = {}
  const params = {}
  const sort: string[] = []
  setProperty(params, 'sort', sort.length > 0 ? sort : undefined)

  return {
    method: 'POST',
    url: `/system/public_category/select`,
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
  [categorySelectFilter, categorySelectSort],
  () => {
    categorySelectQuery.refresh()
    categorySelectSelection.value = []
  }
)
</script>

<template>
  <div class="flex flex-col overflow-hidden">
    <SectionHeading tag="h1" title="Categories" />
    <GridCategories
      :state="categorySelectQuery.state"
      v-model:selection="categorySelectSelection"
    />
  </div>
</template>
