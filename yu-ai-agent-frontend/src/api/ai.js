import { API_BASE_URL } from '../config'

const LOVE_APP_SSE_PATH = '/ai/love_app/chat/sse'
const MANUS_CHAT_PATH = '/ai/manus/chat'

function buildSseUrl(path, params) {
  const fullPath = `${API_BASE_URL}${path}`
  const url = fullPath.startsWith('http')
    ? new URL(fullPath)
    : new URL(fullPath, window.location.origin)

  Object.entries(params).forEach(([key, value]) => {
    if (value != null && value !== '') {
      url.searchParams.set(key, value)
    }
  })
  return url.toString()
}

/**
 * 使用 EventSource 建立 SSE 连接（GET），同源或已配置 CORS 时最稳定
 * @returns {{ close: () => void }}
 */
function connectSse(url, { onMessage, onError, onClose }) {
  const eventSource = new EventSource(url)
  let closed = false
  let received = false

  const finish = () => {
    if (closed) return
    closed = true
    eventSource.close()
    onClose?.()
  }

  eventSource.onmessage = (event) => {
    received = true
    if (event.data) {
      onMessage(event.data)
    }
  }

  eventSource.onerror = () => {
    if (closed) return

    // 服务端正常结束流时会触发 onerror，需主动 close 防止自动重连
    if (received || eventSource.readyState === EventSource.CLOSED) {
      finish()
      return
    }

    closed = true
    eventSource.close()
    onError?.(
      new Error(
        '无法连接后端，请确认：1) 后端已启动在 8123 端口；2) 使用 npm run dev 启动前端（启用代理）；3) 勿混用 localhost 与 127.0.0.1 访问页面',
      ),
    )
    onClose?.()
  }

  return {
    close() {
      if (!closed) {
        closed = true
        eventSource.close()
        onClose?.()
      }
    },
  }
}

/** AI 恋爱大师 - SSE 流式对话 */
export function doChatWithLoveAppSse(message, chatId, handlers) {
  const url = buildSseUrl(LOVE_APP_SSE_PATH, { message, chatId })
  return connectSse(url, handlers)
}

/** AI 超级智能体 - SSE 流式对话 */
export function doChatWithManus(message, handlers) {
  const url = buildSseUrl(MANUS_CHAT_PATH, { message })
  return connectSse(url, handlers)
}
