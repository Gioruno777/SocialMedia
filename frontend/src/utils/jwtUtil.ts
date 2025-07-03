import { jwtDecode } from 'jwt-decode'

type JwtPayload = {
  sub: string
  exp: number
  [key: string]: any
}

export function getCurrentUserId(): number | null {
  const token = localStorage.getItem('auth_token')
  if (!token) return null
  try {
    const decoded = jwtDecode<JwtPayload>(token)
    return parseInt(decoded.sub)
  } catch (err) {
    console.error('JWT 解碼失敗', err)
    return null
  }
}
