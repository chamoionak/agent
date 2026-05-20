/**
 * 后端 API 前缀
 * 开发环境默认走 Vite 代理（/api -> 127.0.0.1:8123），避免跨域
 * 生产环境可通过 .env 配置：VITE_API_BASE_URL=http://127.0.0.1:8123/api
 */
export const API_BASE_URL =
  import.meta.env.VITE_API_BASE_URL || '/api'
