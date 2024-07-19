<script setup lang="ts" generic="T extends { id: string }">
import { ref, reactive, shallowRef } from 'vue';
import Menubar from 'primevue/menubar'
import Button from 'primevue/button'
import Chip from 'primevue/chip'
import Menu from 'primevue/menu';
import { type Option } from '../dialogs/PlatformDialogFilter.vue';
import { useModalStore } from '../../store/modalStore';
import SelectPickerNetworkType from '../controls/SelectPickerNetworkType.vue'
import { useActionNetworkRemoveSelected } from '../../actions/useActionNetworkRemoveSelected'
import { useActionCreateNetwork } from '../../actions/useActionCreateNetwork'
const modalStore = useModalStore()

const emit = defineEmits<{
  (e: 'refresh'): void
}>()

const filterByTypeId = defineModel('filterByTypeId')
const sortById = defineModel<'asc' | 'desc' | undefined>('sortById')
const sortByName = defineModel<'asc' | 'desc' | undefined>('sortByName')
const sortByTypeId = defineModel<'asc' | 'desc' | undefined>('sortByTypeId')
const sortByHidden = defineModel<'asc' | 'desc' | undefined>('sortByHidden')
const selection = defineModel<T[]>('selection', { required: true })

const filters = reactive<Record<string, Option | undefined>>({
  typeId: undefined,
})

const sortings = reactive<Record<string, 'asc' | 'desc' | undefined>>({
  id: undefined,
  name: undefined,
  typeId: undefined,
  hidden: undefined,
})

const filtersMenuRef = ref()

const filtersMenuItems = ref([
  {
    label: 'By Property',
    items: [
      {
        label: 'Type Id',
        icon: 'pi pi-plus-circle',
        command: () => [
          modalStore.openModal(() => ({
            component: shallowRef(SelectPickerNetworkType),
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
        label: 'Name (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.name = 'asc'
          sortByName.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'Name (Descending)',
        command: () => {
          sortings.name = 'desc'
          sortByName.value = 'desc'
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
        label: 'Hidden (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.hidden = 'asc'
          sortByHidden.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'Hidden (Descending)',
        command: () => {
          sortings.hidden = 'desc'
          sortByHidden.value = 'desc'
        }
      },
    ]
  }
])

const actionNetworkRemoveSelected = useActionNetworkRemoveSelected()
const actionCreateNetwork = useActionCreateNetwork()

async function handleRemoveSelected() {
  try {
    await actionNetworkRemoveSelected.execute(selection.value.map(item => ({ id: item.id })))
    emit('refresh')
  } catch (e) {
    console.log(e)
  }
}

async function handleCreateNetwork() {
  try {
    actionCreateNetwork.execute(undefined, {
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
            <Chip v-if="filterByTypeId !== undefined" removable @remove="filterByTypeId = undefined" class="p-1 ms-2 my-1 md:whitespace-nowrap">
              <span><b>Type Id: </b><span v-text="filters.typeId?.title || filterByTypeId"></span></span>
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
            <Chip v-if="sortByName !== undefined" removable @remove="sortByName = undefined" class="p-1 ms-2 my-1 md:whitespace-nowrap">
              <span><b>Name: </b><span v-text="sortings.name || sortByName"></span></span>
            </Chip>
            <Chip v-if="sortByTypeId !== undefined" removable @remove="sortByTypeId = undefined" class="p-1 ms-2 my-1 md:whitespace-nowrap">
              <span><b>Type Id: </b><span v-text="sortings.typeId || sortByTypeId"></span></span>
            </Chip>
            <Chip v-if="sortByHidden !== undefined" removable @remove="sortByHidden = undefined" class="p-1 ms-2 my-1 md:whitespace-nowrap">
              <span><b>Hidden: </b><span v-text="sortings.hidden || sortByHidden"></span></span>
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
            label="Create Network"
            class="ms-2"
            @click="handleCreateNetwork"
          />
        </div>
      </div>
    </template>
  </Menubar>
</template>
