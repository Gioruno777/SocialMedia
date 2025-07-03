<template>
  <form
    @submit.prevent="handleSubmit"
    class="w-full flex flex-col p-2 text-2xl bg-red-100 border-2 border-white rounded-md"
  >
    <textarea
      v-model="content"
      class="w-full p-2 resize-none bg-white border border-gray-300 rounded-md"
      rows="3"
      placeholder="在想什麼呢？"
    ></textarea>
    <button type="submit" class="w-full mt-2 bg-white border border-gray-300 rounded-md">
      {{ isPending ? 'Loading...' : '▷' }}
    </button>
  </form>
</template>

<script setup lang="ts">
  import { useCreatePost } from '@/api/post'
  import { ref } from 'vue'
  const content = ref('')
  const { createPost, isPending } = useCreatePost()
  const handleSubmit = () => {
    if (!content.value.trim()) {
      return
    }
    createPost({ content: content.value })
    content.value = ''
  }
</script>
