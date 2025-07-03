<template>
  <div class="flex justify-between items-center w-full text-gray-700 mb-1 border-b">
    <div class="flex items-center gap-2">
      <p class="font-bold">{{ comment.userName }}</p>
      <p class="mt-2">{{ comment.content }}</p>
    </div>
    <p class="mt-4 text-sm text-gray-500">{{ formatDate(comment.createdAt) }}</p>
  </div>
</template>

<script setup lang="ts">
  import { formatDate } from '@/utils/time'
  defineProps<{
    comment: {
      commentId: number
      userName: string
      content: string
      createdAt: string
    }
  }>()
</script>
