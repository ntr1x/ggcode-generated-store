<script setup lang="ts" generic="T extends { id: string }">
import { ref, shallowRef, reactive } from 'vue';
import Menubar from 'primevue/menubar'
import Button from 'primevue/button'
import Chip from 'primevue/chip'
import Menu from 'primevue/menu';
import { type Option } from '../dialogs/PlatformDialogFilter.vue';

import { useModalStore } from '../../store/modalStore';
import SearchPickerCustomerId from '../controls/SearchPickerCustomerId.vue'
import SelectPickerDispatchType from '../controls/SelectPickerDispatchType.vue'
import SelectPickerDispatchStatus from '../controls/SelectPickerDispatchStatus.vue'
import { useActionDispatchRemoveSelected } from '../../actions/useActionDispatchRemoveSelected'
import { useActionDispatchMessage } from '../../actions/useActionDispatchMessage'

const modalStore = useModalStore()

const emit = defineEmits<{
  (e: 'refresh'): void
}>()

const filterByCustomerId = defineModel('filterByCustomerId')
const filterByTypeId = defineModel('filterByTypeId')
const filterByStatusId = defineModel('filterByStatusId')
const sortById = defineModel<'asc' | 'desc' | undefined>('sortById')
const sortByCreatedAt = defineModel<'asc' | 'desc' | undefined>('sortByCreatedAt')
const sortByUpdatedAt = defineModel<'asc' | 'desc' | undefined>('sortByUpdatedAt')
const sortByTypeId = defineModel<'asc' | 'desc' | undefined>('sortByTypeId')
const sortByStatusId = defineModel<'asc' | 'desc' | undefined>('sortByStatusId')
const selection = defineModel<T[]>('selection', { required: true })

const filters = reactive<Record<string, Option | undefined>>({
  customerId: undefined,
  typeId: undefined,
  statusId: undefined,
})

const sortings = reactive<Record<string, 'asc' | 'desc' | undefined>>({
  id: undefined,
  createdAt: undefined,
  updatedAt: undefined,
  typeId: undefined,
  statusId: undefined,
})

const filtersMenuRef = ref()

const filtersMenuItems = ref([
  {
    label: 'By Property',
    items: [
      {
        label: 'Customer Id',
        icon: 'pi pi-plus-circle',
        command: () => [
          modalStore.openModal(() => ({
            component: shallowRef(SearchPickerCustomerId),
            props: {},
            handlers: {
              ['update:model-value'](value: string) {
                filterByCustomerId.value = value
              },
              ['change:option'](option: Option) {
                filters.customerId = option
              }
            }
          }))
        ]
      },
      {
        label: 'Type Id',
        icon: 'pi pi-plus-circle',
        command: () => [
          modalStore.openModal(() => ({
            component: shallowRef(SelectPickerDispatchType),
            props: {},
            handlers: {
              ['update:model-value'](value: string) {
                filterByTypeId.value = value
              },
              ['change:option'](option: Option) {
                filters.typeId = option
              }
            }
          }))
        ]
      },
      {
        label: 'Status Id',
        icon: 'pi pi-plus-circle',
        command: () => [
          modalStore.openModal(() => ({
            component: shallowRef(SelectPickerDispatchStatus),
            props: {},
            handlers: {
              ['update:model-value'](value: string) {
                filterByStatusId.value = value
              },
              ['change:option'](option: Option) {
                filters.statusId = option
              }
            }
          }))
        ]
      },
    ]
  }
])

const sortersMenuRef = ref()

const sortersMenuItems = ref([
  {
    label: 'By Column',
    items: [
      {
        label: 'Id (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.id = 'asc'
          sortById.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'Id (Descending)',
        command: () => {
          sortings.id = 'desc'
          sortById.value = 'desc'
        }
      },
      {
        label: 'Created At (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.createdAt = 'asc'
          sortByCreatedAt.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'Created At (Descending)',
        command: () => {
          sortings.createdAt = 'desc'
          sortByCreatedAt.value = 'desc'
        }
      },
      {
        label: 'Updated At (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.updatedAt = 'asc'
          sortByUpdatedAt.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'Updated At (Descending)',
        command: () => {
          sortings.updatedAt = 'desc'
          sortByUpdatedAt.value = 'desc'
        }
      },
      {
        label: 'Type Id (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.typeId = 'asc'
          sortByTypeId.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'Type Id (Descending)',
        command: () => {
          sortings.typeId = 'desc'
          sortByTypeId.value = 'desc'
        }
      },
      {
        label: 'Status Id (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.statusId = 'asc'
          sortByStatusId.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'Status Id (Descending)',
        command: () => {
          sortings.statusId = 'desc'
          sortByStatusId.value = 'desc'
        }
      },
    ]
  }
])

const actionDispatchRemoveSelected = useActionDispatchRemoveSelected()
const actionDispatchMessage = useActionDispatchMessage()

async function handleRemoveSelected() {
  try {
    await actionDispatchRemoveSelected.execute(selection.value.map(item => ({ id: item.id })))
    emit('refresh')
  } catch (e) {
    console.log(e)
  }
}

async function handleCreateDispatch() {
  try {
    actionDispatchMessage.execute(undefined, {
      success: (data: any) => {
        console.log(data)
        emit('refresh')
      },
      failure: (error: any) => {
        console.error(error)
        // TODO @framework: Implement
        
      }
    })
    emit('refresh')
  } catch (e) {
    console.log(e)
  }
}
</script>

<template>
  <Menubar>
    <template #start>
      <div class="flex flex-wrap">
        <div class="flex items-center group-filters">
          <div class="flex flex-none">
            <Button text label="Filters" icon="pi pi-filter" @click="filtersMenuRef.toggle($event)" />
            <Menu ref="filtersMenuRef" id="overlay_menu" :model="filtersMenuItems" :popup="true" />
          </div>
          <div class="flex flex-wrap flex-1">
            <Chip v-if="filterByCustomerId !== undefined" removable @remove="filterByCustomerId = undefined" class="p-1 ms-2 my-1 md:whitespace-nowrap">
              <span><b>Customer Id: </b><span v-text="filters.customerId?.title || filterByCustomerId"></span></span>
            </Chip>
            <Chip v-if="filterByTypeId !== undefined" removable @remove="filterByTypeId = undefined" class="p-1 ms-2 my-1 md:whitespace-nowrap">
              <span><b>Type Id: </b><span v-text="filters.typeId?.title || filterByTypeId"></span></span>
            </Chip>
            <Chip v-if="filterByStatusId !== undefined" removable @remove="filterByStatusId = undefined" class="p-1 ms-2 my-1 md:whitespace-nowrap">
              <span><b>Status Id: </b><span v-text="filters.statusId?.title || filterByStatusId"></span></span>
            </Chip>
          </div>
        </div>
        <div class="flex items-center group-sort">
          <div class="flex flex-none">
            <Button text label="Sort" icon="pi pi-sort" @click="sortersMenuRef.toggle($event)" />
            <Menu ref="sortersMenuRef" id="overlay_menu" :model="sortersMenuItems" :popup="true" />
          </div>
          <div class="flex flex-wrap flex-1">
            <Chip v-if="sortById !== undefined" removable @remove="sortById = undefined" class="p-1 ms-2 my-1 md:whitespace-nowrap">
              <span><b>Id: </b><span v-text="sortings.id || sortById"></span></span>
            </Chip>
            <Chip v-if="sortByCreatedAt !== undefined" removable @remove="sortByCreatedAt = undefined" class="p-1 ms-2 my-1 md:whitespace-nowrap">
              <span><b>Created At: </b><span v-text="sortings.createdAt || sortByCreatedAt"></span></span>
            </Chip>
            <Chip v-if="sortByUpdatedAt !== undefined" removable @remove="sortByUpdatedAt = undefined" class="p-1 ms-2 my-1 md:whitespace-nowrap">
              <span><b>Updated At: </b><span v-text="sortings.updatedAt || sortByUpdatedAt"></span></span>
            </Chip>
            <Chip v-if="sortByTypeId !== undefined" removable @remove="sortByTypeId = undefined" class="p-1 ms-2 my-1 md:whitespace-nowrap">
              <span><b>Type Id: </b><span v-text="sortings.typeId || sortByTypeId"></span></span>
            </Chip>
            <Chip v-if="sortByStatusId !== undefined" removable @remove="sortByStatusId = undefined" class="p-1 ms-2 my-1 md:whitespace-nowrap">
              <span><b>Status Id: </b><span v-text="sortings.statusId || sortByStatusId"></span></span>
            </Chip>
          </div>
        </div>
        <div class="flex items-center group-sort">
          <div class="flex flex-none">
            <Button text label="Refresh" icon="pi pi-refresh" @click="emit('refresh')" />
          </div>
        </div>
      </div>
    </template>
    <template #end>
      <div class="flex flex-wrap">
        <div class="flex items-center group-actions">
          <Button
            v-if="selection.length > 0"
            text
            label="Remove Selected"
            class="ms-2"
            severity="danger"
            @click="handleRemoveSelected"
          />
          <Button
            text
            label="Create Dispatch"
            class="ms-2"
            @click="handleCreateDispatch"
          />
        </div>
      </div>
    </template>
  </Menubar>
</template>
