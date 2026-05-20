<script setup>
import { ref, watch, nextTick } from 'vue'

const props = defineProps({
  title: { type: String, required: true },
  subtitle: { type: String, default: '' },
  messages: { type: Array, required: true },
  loading: { type: Boolean, default: false },
})

const emit = defineEmits(['send'])

const inputText = ref('')
const messagesRef = ref(null)

watch(
  () => props.messages,
  async () => {
    await nextTick()
    if (messagesRef.value) {
      messagesRef.value.scrollTop = messagesRef.value.scrollHeight
    }
  },
  { deep: true },
)

watch(
  () => props.loading,
  async () => {
    await nextTick()
    if (messagesRef.value) {
      messagesRef.value.scrollTop = messagesRef.value.scrollHeight
    }
  },
)

function handleSend() {
  const text = inputText.value.trim()
  if (!text || props.loading) return
  emit('send', text)
  inputText.value = ''
}

function handleKeydown(e) {
  if (e.key === 'Enter' && !e.shiftKey) {
    e.preventDefault()
    handleSend()
  }
}
</script>

<template>
  <div class="chat-page">
    <header class="chat-header">
      <RouterLink to="/" class="back-link">← 返回主页</RouterLink>
      <div class="header-main">
        <h1>{{ title }}</h1>
        <p v-if="subtitle" class="subtitle">{{ subtitle }}</p>
      </div>
    </header>

    <div ref="messagesRef" class="messages">
      <div v-if="messages.length === 0" class="empty-hint">
        发送一条消息开始对话吧
      </div>
      <div
        v-for="(msg, index) in messages"
        :key="index"
        class="message-row"
        :class="msg.role === 'user' ? 'user' : 'assistant'"
      >
        <div class="avatar">{{ msg.role === 'user' ? '我' : 'AI' }}</div>
        <div class="bubble">
          <pre class="bubble-text">{{ msg.content }}</pre>
          <span
            v-if="loading && index === messages.length - 1 && msg.role === 'assistant' && !msg.content"
            class="typing"
          >正在输入...</span>
        </div>
      </div>
    </div>

    <footer class="input-bar">
      <textarea
        v-model="inputText"
        class="input"
        placeholder="输入消息，Enter 发送，Shift+Enter 换行"
        rows="2"
        :disabled="loading"
        @keydown="handleKeydown"
      />
      <button class="send-btn" type="button" :disabled="loading || !inputText.trim()" @click="handleSend">
        {{ loading ? '回复中...' : '发送' }}
      </button>
    </footer>
  </div>
</template>

<style scoped>
.chat-page {
  display: flex;
  flex-direction: column;
  height: 100vh;
  background: #f5f7fb;
}

.chat-header {
  flex-shrink: 0;
  padding: 16px 24px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: #fff;
  box-shadow: 0 2px 12px rgba(102, 126, 234, 0.35);
}

.back-link {
  display: inline-block;
  margin-bottom: 8px;
  color: rgba(255, 255, 255, 0.9);
  text-decoration: none;
  font-size: 14px;
}

.back-link:hover {
  color: #fff;
}

.header-main h1 {
  margin: 0;
  font-size: 22px;
  font-weight: 600;
}

.subtitle {
  margin: 6px 0 0;
  font-size: 12px;
  opacity: 0.85;
  word-break: break-all;
}

.messages {
  flex: 1;
  overflow-y: auto;
  padding: 20px 24px;
}

.empty-hint {
  text-align: center;
  color: #9ca3af;
  margin-top: 40px;
  font-size: 14px;
}

.message-row {
  display: flex;
  gap: 12px;
  margin-bottom: 16px;
  max-width: 85%;
}

.message-row.user {
  flex-direction: row-reverse;
  margin-left: auto;
}

.message-row.assistant {
  margin-right: auto;
}

.avatar {
  flex-shrink: 0;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 13px;
  font-weight: 600;
}

.user .avatar {
  background: #667eea;
  color: #fff;
}

.assistant .avatar {
  background: #e5e7eb;
  color: #4b5563;
}

.bubble {
  padding: 12px 16px;
  border-radius: 16px;
  min-width: 60px;
}

.user .bubble {
  background: #667eea;
  color: #fff;
  border-bottom-right-radius: 4px;
}

.assistant .bubble {
  background: #fff;
  color: #1f2937;
  border-bottom-left-radius: 4px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.06);
}

.bubble-text {
  margin: 0;
  white-space: pre-wrap;
  word-break: break-word;
  font-family: inherit;
  font-size: 14px;
  line-height: 1.6;
}

.typing {
  font-size: 12px;
  color: #9ca3af;
}

.input-bar {
  flex-shrink: 0;
  display: flex;
  gap: 12px;
  padding: 16px 24px;
  background: #fff;
  border-top: 1px solid #e5e7eb;
  box-shadow: 0 -2px 12px rgba(0, 0, 0, 0.04);
}

.input {
  flex: 1;
  resize: none;
  border: 1px solid #e5e7eb;
  border-radius: 12px;
  padding: 12px 16px;
  font-size: 14px;
  font-family: inherit;
  outline: none;
  transition: border-color 0.2s;
}

.input:focus {
  border-color: #667eea;
}

.input:disabled {
  background: #f9fafb;
}

.send-btn {
  align-self: flex-end;
  padding: 12px 24px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: #fff;
  border: none;
  border-radius: 12px;
  font-size: 14px;
  font-weight: 500;
  cursor: pointer;
  transition: opacity 0.2s;
}

.send-btn:hover:not(:disabled) {
  opacity: 0.92;
}

.send-btn:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}
</style>
