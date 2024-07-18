<script setup lang="ts" generic="T">
import { ref, shallowRef, reactive } from 'vue';
import Menubar from 'primevue/menubar'
import Button from 'primevue/button'
import Chip from 'primevue/chip'
import Menu from 'primevue/menu';
import { type Option } from '../dialogs/PlatformDialogFilter.vue';

import { useModalStore } from '../../store/modalStore';
import SelectPickerEventSource from '../controls/SelectPickerEventSource.vue'
import SelectPickerEventTopic from '../controls/SelectPickerEventTopic.vue'
import SelectPickerEventType from '../controls/SelectPickerEventType.vue'

const modalStore = useModalStore()

const emit = defineEmits<{
  (e: 'refresh'): void
}>()

const filterByEventSource = defineModel('filterByEventSource')
const filterByEventTopic = defineModel('filterByEventTopic')
const filterByEventType = defineModel('filterByEventType')
const sortById = defineModel<'asc' | 'desc' | undefined>('sortById')
const sortByCeSource = defineModel<'asc' | 'desc' | undefined>('sortByCeSource')
const sortByCeType = defineModel<'asc' | 'desc' | undefined>('sortByCeType')
const sortByTopic = defineModel<'asc' | 'desc' | undefined>('sortByTopic')
const sortByCreatedAt = defineModel<'asc' | 'desc' | undefined>('sortByCreatedAt')

const filters = reactive<Record<string, Option | undefined>>({
  eventSource: undefined,
  eventTopic: undefined,
  eventType: undefined,
})

const sortings = reactive<Record<string, 'asc' | 'desc' | undefined>>({
  id: undefined,
  ceSource: undefined,
  ceType: undefined,
  topic: undefined,
  createdAt: undefined,
})

const filtersMenuRef = ref()

const filtersMenuItems = ref([
  {
    label: 'By Property',
    items: [
      {
        label: 'Event Source',
        icon: 'pi pi-plus-circle',
        command: () => [
          modalStore.openModal(() => ({
            component: shallowRef(SelectPickerEventSource),
            props: {},
            handlers: {
              ['update:model-value'](value: string) {
                filterByEventSource.value = value
              },
              ['change:option'](option: Option) {
                filters.eventSource = option
              }
            }
          }))
        ]
      },
      {
        label: 'Event Topic',
        icon: 'pi pi-plus-circle',
        command: () => [
          modalStore.openModal(() => ({
            component: shallowRef(SelectPickerEventTopic),
            props: {},
            handlers: {
              ['update:model-value'](value: string) {
                filterByEventTopic.value = value
              },
              ['change:option'](option: Option) {
                filters.eventTopic = option
              }
            }
          }))
        ]
      },
      {
        label: 'Event Type',
        icon: 'pi pi-plus-circle',
        command: () => [
          modalStore.openModal(() => ({
            component: shallowRef(SelectPickerEventType),
            props: {},
            handlers: {
              ['update:model-value'](value: string) {
                filterByEventType.value = value
              },
              ['change:option'](option: Option) {
                filters.eventType = option
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
        label: 'Ce Source (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.ceSource = 'asc'
          sortByCeSource.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'Ce Source (Descending)',
        command: () => {
          sortings.ceSource = 'desc'
          sortByCeSource.value = 'desc'
        }
      },
      {
        label: 'Ce Type (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.ceType = 'asc'
          sortByCeType.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'Ce Type (Descending)',
        command: () => {
          sortings.ceType = 'desc'
          sortByCeType.value = 'desc'
        }
      },
      {
        label: 'Topic (Ascending)',
        icon: 'pi pi-sort-alpha-down',
        command: () => {
          sortings.topic = 'asc'
          sortByTopic.value = 'asc'
        }
      },
      {
        icon: 'pi pi-sort-alpha-up',
        label: 'Topic (Descending)',
        command: () => {
          sortings.topic = 'desc'
          sortByTopic.value = 'desc'
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
    ]
  }
])
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
            <Chip v-if="filterByEventSource !== undefined" removable @remove="filterByEventSource = undefined" class="p-1 ms-2 my-1 md:whitespace-nowrap">
              <span><b>Event Source: </b><span v-text="filters.eventSource?.title || filterByEventSource"></span></span>
            </Chip>
            <Chip v-if="filterByEventTopic !== undefined" removable @remove="filterByEventTopic = undefined" class="p-1 ms-2 my-1 md:whitespace-nowrap">
              <span><b>Event Topic: </b><span v-text="filters.eventTopic?.title || filterByEventTopic"></span></span>
            </Chip>
            <Chip v-if="filterByEventType !== undefined" removable @remove="filterByEventType = undefined" class="p-1 ms-2 my-1 md:whitespace-nowrap">
              <span><b>Event Type: </b><span v-text="filters.eventType?.title || filterByEventType"></span></span>
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
            <Chip v-if="sortByCeSource !== undefined" removable @remove="sortByCeSource = undefined" class="p-1 ms-2 my-1 md:whitespace-nowrap">
              <span><b>Ce Source: </b><span v-text="sortings.ceSource || sortByCeSource"></span></span>
            </Chip>
            <Chip v-if="sortByCeType !== undefined" removable @remove="sortByCeType = undefined" class="p-1 ms-2 my-1 md:whitespace-nowrap">
              <span><b>Ce Type: </b><span v-text="sortings.ceType || sortByCeType"></span></span>
            </Chip>
            <Chip v-if="sortByTopic !== undefined" removable @remove="sortByTopic = undefined" class="p-1 ms-2 my-1 md:whitespace-nowrap">
              <span><b>Topic: </b><span v-text="sortings.topic || sortByTopic"></span></span>
            </Chip>
            <Chip v-if="sortByCreatedAt !== undefined" removable @remove="sortByCreatedAt = undefined" class="p-1 ms-2 my-1 md:whitespace-nowrap">
              <span><b>Created At: </b><span v-text="sortings.createdAt || sortByCreatedAt"></span></span>
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
      </div>
    </template>
  </Menubar>
</template>
