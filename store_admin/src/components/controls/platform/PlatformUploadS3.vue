<script setup lang="ts">
import FileUpload, { FileUploadUploaderEvent } from 'primevue/fileupload';
import { uploadsRemote } from '../../../remotes/uploadsRemote';
import { minioRemote } from '../../../remotes/minioRemote';
import { useAuthStore } from '../../../store/authStore';
import { v4 } from 'uuid';
// import { ref } from 'vue';

const model = defineModel()
const props = defineProps<{
  bucket: string
}>()


const authStore = useAuthStore()

const uploader = async (event: FileUploadUploaderEvent) => {
  console.log(event)

  const files = Array.isArray(event.files)
    ? event.files
    : [event.files]

  const file = files[0]

  const key = v4()

  const payload = {
    bucket: props.bucket,
    key,
    source: 'admin-ui',
    originalName: file.name
  }

  const token = await authStore.requireToken()

  const { data: preSignData } = await uploadsRemote.post('/profile/uploads/preSign', payload, {
    headers: {
      Authorization: `Bearer ${token}`
    }
  })

  const fd = new FormData()
  for (const [name, value] of Object.entries(preSignData.formData)) {
    fd.append(name, value as string)
  }
  fd.append("key", key)
  fd.append("source", payload.source)
  fd.append("original-name", payload.originalName)
  fd.append("Content-Type", file.type)
  fd.append("file", files[0], key)

  const { data: uploadData } = await minioRemote.post(props.bucket, fd, {
    headers: {
      "Content-Type": "multipart/form-data"
    }
  })

  console.log(uploadData)

  model.value = key

  // const reader = new FileReader();
  // let blob = await fetch(file.objectURL).then((r) => r.blob()); //blob:url

  // reader.readAsDataURL(blob);

  // reader.onloadend = function () {
  //     const base64data = reader.result;
  // };
}

// const fileupload = ref();
</script>

<template>
  <FileUpload
    mode="basic"
    name="myUpload"
    accept="image/*"
    customUpload
    auto
    @uploader="uploader"
  />
</template>
