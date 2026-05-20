<script setup>
import { ref, onUnmounted } from 'vue'
import ChatRoom from '../components/ChatRoom.vue'
import { doChatWithManus } from '../api/ai'

const messages = ref([])
const loading = ref(false)
let sseConnection = null

onUnmounted(() => {
  sseConnection?.close()
})

function handleSend(text) {
  if (loading.value) return

  messages.value.push({ role: 'user', content: text })
  const assistantMsg = { role: 'assistant', content: '' }
  messages.value.push(assistantMsg)
  loading.value = true

  sseConnection?.close()
  sseConnection = doChatWithManus(text, {
    onMessage(chunk) {
      if (assistantMsg.content && chunk.startsWith('Step')) {
        assistantMsg.content += '\n\n'
      }
      assistantMsg.content += chunk
    },
    onError(err) {
      if (!assistantMsg.content) {
        assistantMsg.content = `请求出错：${err.message || '未知错误'}`
      }
      loading.value = false
    },
    onClose() {
      loading.value = false
    },
  })
}
</script>

<template>
  <ChatRoom
    title="AI 超级智能体"
    subtitle="多步骤智能体，实时展示执行过程"
    :messages="messages"
    :loading="loading"
    @send="handleSend"
  />
</template>
