<template>
  <form
    @submit.prevent="handleSubmit"
    class="w-full flex items-cemters mt-4 text-xs bg-red-100 border-2 border-white rounded-md"
  >
    <textarea
      v-model="content"
      class="w-9/10 p-2 resize-none bg-white"
      rows="1"
      placeholder="請輸入留言..."
    ></textarea>
    <button type="submit" class="w-1/10 p-2 bg-white">
      {{ isPending ? 'Loading...' : '▷' }}
    </button>
  </form>
</template>

<script setup lang="ts">
  import { useCreateComment } from '@/api/comment'
  import { ref } from 'vue'

  const props = defineProps<{
    postId: number
  }>()

  const content = ref('')
  const { CreateComment, isPending } = useCreateComment()
  const handleSubmit = () => {
    if (!content.value.trim()) {
      return
    }
    CreateComment({
      content: content.value,
      postId: props.postId,
    })
    content.value = ''
  }
</script>
