<script setup lang="ts">
import { computed, ref, watch } from 'vue';
import { customersRemote } from '../../remotes/customersRemote'
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import LookupDialog, { type Option } from '../dialogs/LookupDialog.vue';
import { debounce } from 'lodash';

type IHaveId = {
  id: string
}

type IHaveName = {
  name: string
  surname: string
  patronymic: string
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

const customersQuery = useAxiosRequest<ResponseData>(customersRemote, async () => {
  const token = await authStore.requireToken()
  return {
    method: 'POST',
    url: '/system/public_customer/select',
    data: {
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
            b: {
              value: '0.05'
            }
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
    },
    params: {},
    headers: {
      Authorization: `Bearer ${token}`
    }
  }
})

const options = computed(() => {
  const items = customersQuery.state.data?.content || []
  return items.map(item => ({
    key: `item-${item.id}`,
    title: `${item.name} ${item.patronymic} ${item.surname}`,
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
    customersQuery.refresh()
  }, 500)
)

</script>

<template>
  <LookupDialog
    title="Customer"
    :is-loading="customersQuery.state.isLoading"
    :is-loaded="customersQuery.state.isLoaded"
    :is-failed="customersQuery.state.isFailed"
    :options="options"
    v-model="model"
    @change:option="value => emit('change:option', value)"
    @change:query="query => handleQuery(query)"
  />
</template>
