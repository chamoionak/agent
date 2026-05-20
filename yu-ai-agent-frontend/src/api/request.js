import axios from 'axios'
import { API_BASE_URL } from '../config'

/** Axios 实例，供普通 HTTP 请求使用 */
const request = axios.create({
  baseURL: API_BASE_URL,
  timeout: 300000,
})

export default request
