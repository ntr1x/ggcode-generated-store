<script setup lang="ts">
import { computed, ref, watch } from 'vue';
import { debounce } from 'lodash';
import { customersRemote } from '../../remotes/customersRemote'
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import SearchDialog, { type Option } from '../dialogs/SearchDialog.vue';

type IHaveId = {
  id: string
}

type IHaveName = {
  fullName: string
}

type IHaveDescription = {
  email: string
}

export type ResponseDataRow = IHaveId & IHaveName & IHaveDescription

export type ResponseData = {
  content: ResponseDataRow[]
}

const model = defineModel<ResponseDataRow | undefined>()

const query = ref<string | undefined>()

const emit = defineEmits<{
  (e: 'change:option', value: Option): void
}>()

const authStore = useAuthStore()

const response = useAxiosRequest<ResponseData>(customersRemote, async () => {
  const token = await authStore.requireToken()

  const requestData = {
    $where: query.value == null ? undefined : [
      {
        $gt: {
          a: {
            func: {
              similarity: {
                a: { prop: "fullName" },
                b: { value: query.value }
              }
            }
          },
          b: { value: '0.2' }
        }
      }
    ],
    $order: query.value == null ? undefined : [
      {
        direction: "DESC",
        func: {
          similarity: {
            a: { prop: "fullName" },
            b: { value: query.value }
          }
        }
      }
    ]
  }

  return {
    method: 'POST',
    url: '/system/public_customer/select',
    data: Object.assign({}, requestData),
    params: {"size":50,"sort":"fullName"},
    headers: {
      Authorization: `Bearer ${token}`
    }
  }
})

const options = computed(() => {
  const items = response.state.data?.content || []
  return items.map(item => ({
    key: `item-${item.id}`,
    title: item.fullName,
    description: item.email,
    value: item.id
  }))
})

function handleQuery(value?: string) {
  query.value = value
}

watch(
  [query],
  debounce(() => {
    response.refresh()
  }, 500)
)

</script>

<template>
  <SearchDialog
    title="Customer Id"
    :is-loading="response.state.isLoading"
    :is-loaded="response.state.isLoaded"
    :is-failed="response.state.isFailed"
    :options="options"
    v-model="model"
    @change:option="value => emit('change:option', value)"
    @change:query="query => handleQuery(query)"
  />
</template>
