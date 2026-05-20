<script setup>
const apps = [
  {
    id: 'love',
    title: 'AI 恋爱大师',
    description: '恋爱心理专家，倾听你的情感困惑，给出专属建议',
    path: '/love-app',
    icon: '💕',
    tag: '情感咨询',
    gradient: 'linear-gradient(135deg, #f093fb 0%, #f5576c 100%)',
    glow: 'rgba(245, 87, 108, 0.35)',
  },
  {
    id: 'manus',
    title: 'AI 超级智能体',
    description: '全能 AI 助手，可调用多种工具完成复杂任务',
    path: '/manus',
    icon: '🤖',
    tag: '多工具协作',
    gradient: 'linear-gradient(135deg, #4facfe 0%, #00f2fe 100%)',
    glow: 'rgba(79, 172, 254, 0.35)',
  },
]
</script>

<template>
  <div class="home">
    <div class="bg-blob blob-1" />
    <div class="bg-blob blob-2" />
    <div class="bg-blob blob-3" />

    <div class="home-inner">
      <header class="hero">
        <span class="hero-badge">AI Agent</span>
        <h1>应用中心</h1>
        <p>选择下方应用，开启智能对话体验</p>
      </header>

      <main class="app-grid">
        <RouterLink
          v-for="app in apps"
          :key="app.id"
          :to="app.path"
          class="app-card"
          :style="{ '--card-gradient': app.gradient, '--card-glow': app.glow }"
        >
          <div class="card-glow" />
          <div class="card-top">
            <div class="icon-wrap">{{ app.icon }}</div>
            <span class="card-tag">{{ app.tag }}</span>
          </div>
          <h2>{{ app.title }}</h2>
          <p>{{ app.description }}</p>
          <span class="enter-btn">
            进入应用
            <svg width="16" height="16" viewBox="0 0 24 24" fill="none" aria-hidden="true">
              <path
                d="M5 12h14M13 6l6 6-6 6"
                stroke="currentColor"
                stroke-width="2"
                stroke-linecap="round"
                stroke-linejoin="round"
              />
            </svg>
          </span>
        </RouterLink>
      </main>

      <footer class="home-footer">
        <span>Powered by Spring AI · Vue 3</span>
      </footer>
    </div>
  </div>
</template>

<style scoped>
.home {
  position: relative;
  min-height: 100vh;
  overflow: hidden;
  background: linear-gradient(180deg, #ffffff 0%, #f4f6fb 50%, #eef1f8 100%);
}

.bg-blob {
  position: absolute;
  border-radius: 50%;
  filter: blur(90px);
  pointer-events: none;
}

.blob-1 {
  width: 420px;
  height: 420px;
  top: -120px;
  left: -80px;
  background: rgba(167, 139, 250, 0.28);
}

.blob-2 {
  width: 360px;
  height: 360px;
  top: 20%;
  right: -100px;
  background: rgba(147, 197, 253, 0.35);
}

.blob-3 {
  width: 300px;
  height: 300px;
  bottom: -60px;
  left: 35%;
  background: rgba(251, 207, 232, 0.4);
}

.home-inner {
  position: relative;
  z-index: 1;
  max-width: 880px;
  margin: 0 auto;
  padding: 64px 24px 48px;
}

.hero {
  text-align: center;
  margin-bottom: 56px;
}

.hero-badge {
  display: inline-block;
  padding: 6px 14px;
  margin-bottom: 20px;
  font-size: 12px;
  font-weight: 600;
  letter-spacing: 0.08em;
  text-transform: uppercase;
  color: #6366f1;
  background: #eef2ff;
  border: 1px solid #e0e7ff;
  border-radius: 999px;
}

.hero h1 {
  margin: 0 0 14px;
  font-size: clamp(36px, 6vw, 48px);
  font-weight: 700;
  letter-spacing: -0.02em;
  background: linear-gradient(135deg, #1e293b 0%, #6366f1 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.hero p {
  margin: 0;
  color: #64748b;
  font-size: 16px;
}

.app-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 24px;
}

.app-card {
  position: relative;
  display: flex;
  flex-direction: column;
  padding: 28px;
  background: #ffffff;
  border: 1px solid #e8ecf4;
  border-radius: 24px;
  text-decoration: none;
  color: inherit;
  box-shadow: 0 4px 20px rgba(15, 23, 42, 0.05);
  transition: transform 0.25s ease, border-color 0.25s ease, box-shadow 0.25s ease;
  overflow: hidden;
}

.card-glow {
  position: absolute;
  inset: 0;
  opacity: 0;
  background: radial-gradient(circle at 30% 0%, var(--card-glow), transparent 70%);
  transition: opacity 0.3s ease;
  pointer-events: none;
}

.app-card:hover {
  transform: translateY(-6px);
  border-color: #e0e7ff;
  box-shadow: 0 16px 40px rgba(99, 102, 241, 0.12);
}

.app-card:hover .card-glow {
  opacity: 1;
}

.app-card:hover .enter-btn {
  gap: 10px;
}

.card-top {
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  margin-bottom: 20px;
}

.icon-wrap {
  width: 56px;
  height: 56px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 28px;
  background: var(--card-gradient);
  border-radius: 16px;
  box-shadow: 0 8px 24px var(--card-glow);
}

.card-tag {
  padding: 4px 10px;
  font-size: 11px;
  font-weight: 500;
  color: #64748b;
  background: #f1f5f9;
  border-radius: 6px;
}

.app-card h2 {
  margin: 0 0 10px;
  font-size: 22px;
  font-weight: 600;
  color: #1e293b;
}

.app-card p {
  flex: 1;
  margin: 0 0 24px;
  font-size: 14px;
  color: #64748b;
  line-height: 1.7;
}

.enter-btn {
  display: inline-flex;
  align-items: center;
  gap: 6px;
  font-size: 14px;
  font-weight: 600;
  background: var(--card-gradient);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  transition: gap 0.2s ease;
}

.enter-btn svg {
  color: #6366f1;
  -webkit-text-fill-color: initial;
  transition: transform 0.2s ease;
}

.app-card:hover .enter-btn svg {
  color: #4f46e5;
}

.app-card:hover .enter-btn svg {
  transform: translateX(4px);
}

.home-footer {
  margin-top: 56px;
  text-align: center;
}

.home-footer span {
  font-size: 12px;
  color: #94a3b8;
  letter-spacing: 0.02em;
}
</style>
