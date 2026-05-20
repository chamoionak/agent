<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import ChatRoom from '../components/ChatRoom.vue'
import { doChatWithLoveAppSse } from '../api/ai'
import { generateChatId } from '../utils/chatId'

const chatId = ref('')
const messages = ref([])
const loading = ref(false)
let sseConnection = null

onMounted(() => {
  chatId.value = generateChatId()
})

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
  sseConnection = doChatWithLoveAppSse(text, chatId.value, {
    onMessage(chunk) {
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
    title="AI 恋爱大师"
    :subtitle="`会话 ID：${chatId}`"
    :messages="messages"
    :loading="loading"
    @send="handleSend"
  />
</template>
