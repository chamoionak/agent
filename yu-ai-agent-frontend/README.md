# 鱼皮 AI Agent 前端

基于 Vue 3 + Vite + Axios 的 AI 对话前端项目。

## 功能

- **主页**：切换「AI 恋爱大师」与「AI 超级智能体」
- **恋爱大师**：聊天室 UI，进入页面自动生成 `chatId`，通过 SSE 调用 `/ai/love_app/chat/sse`
- **超级智能体**：相同聊天室 UI，通过 SSE 调用 `/ai/manus/chat`

## 环境要求

- Node.js 18+
- 后端服务运行在 `http://localhost:8123`（context-path: `/api`）

## 启动

```bash
cd yu-ai-agent-frontend
npm install
npm run dev
```

浏览器访问终端提示的地址（默认 `http://localhost:5173`）。

## 构建

```bash
npm run build
```

## 配置

开发环境默认通过 Vite 代理访问后端（`/api` → `http://127.0.0.1:8123`），无需处理跨域。

若需直连后端，在 `.env.development` 中设置：

```
VITE_API_BASE_URL=http://127.0.0.1:8123/api
```

注意：请统一使用 `localhost` 或 `127.0.0.1` 访问前端页面，不要混用。
