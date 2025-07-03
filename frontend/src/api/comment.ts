import { getAuthHeaders } from '@/utils/authClient'
import { useMutation, useQuery, useQueryClient } from '@tanstack/vue-query'
const API_BASE_URL = import.meta.env.VITE_BACKEND_URL

export const useGetComment = (postId: number) => {
  const request = async () => {
    const response = await fetch(`${API_BASE_URL}/api/comments/post/${postId}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        ...getAuthHeaders(),
      },
    })

    if (!response.ok) {
      throw new Error('留言讀取失敗')
    }

    return response.json()
  }

  const { data, isLoading, isError } = useQuery({
    queryKey: ['GetComment', postId],
    queryFn: request,
  })
  return { data, isLoading, isError }
}

export interface CommentPayload {
  postId: number
  content: string
}

export const useCreateComment = () => {
  const queryClient = useQueryClient()

  const request = async (formData: CommentPayload) => {
    const response = await fetch(`${API_BASE_URL}/api/comments`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        ...getAuthHeaders(),
      },
      body: JSON.stringify(formData),
    })

    const data = await response.json()
    if (!response.ok) {
      throw new Error('留言失敗')
    }
    return data
  }

  const { mutate: CreateComment, isPending } = useMutation({
    mutationFn: request,
    onSuccess: async () => {
      alert('留言成功')
      await queryClient.invalidateQueries({ queryKey: ['GetComment'] })
    },
    onError: () => {
      alert('留言失敗')
    },
  })
  return { CreateComment, isPending }
}
