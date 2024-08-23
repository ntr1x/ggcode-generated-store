<script setup lang="ts">
import { ref } from 'vue';
import { v4 } from 'uuid';
import FileUpload, { FileUploadUploaderEvent } from 'primevue/fileupload';
import { uploadsRemote } from '../../remotes/uploadsRemote'
import { minioRemote } from '../../remotes/minioRemote';
import { useAuthStore } from '../../store/authStore';

const model = defineModel()

const upload = ref<{
  key: string
  name: string
} | undefined>(undefined)

const authStore = useAuthStore()

const uploader = async (event: FileUploadUploaderEvent) => {
  const files = Array.isArray(event.files)
    ? event.files
    : [event.files]

  const file = files[0]
  const key = `guides/${v4()}`

  const payload = {
    key,
    bucket: 'app-public-document',
  }

  const token = await authStore.requireToken()

  const { data: preSignData } = await uploadsRemote.request({
    method: 'POST',
    url: '/profile/uploads/preSign',
    data: Object.assign({}, payload),
    params: {},
    headers: {
      Authorization: `Bearer ${token}`
    }
  })

  const fd = new FormData()
  for (const [name, value] of Object.entries(preSignData.formData)) {
    fd.append(name, value as string)
  }
  fd.append("key", key)
  fd.append("file", file, key)

  await minioRemote.post(payload.bucket, fd, {
    headers: {
      "Content-Type": "multipart/form-data"
    }
  })

  model.value = key

  upload.value = {
    key,
    name: file.name
  }
}

</script>

<template>
  <div class="w-full">
    <div class="flex relative items-center gap-2">
      <FileUpload
        mode="basic"
        customUpload
        auto
        @uploader="uploader"
      />
      <div class="overflow-hidden relative">
        <div class="overflow-hidden whitespace-nowrap text-ellipsis" v-html="upload?.name"></div>
      </div>
    </div>
  </div>
</template>
