package com.yupi.yuaiagent.app;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class testt {

    /** 与 LoveApp 一致，使用 DashScope；若用本地 Ollama 可改为 ollamaChatModel */
    @Resource(name = "dashscopeChatModel")
    private ChatModel chatModel;

    @Test
    void multiTurnChat() {
        List<Message> messages = new ArrayList<>();

        //第一轮对话
        messages.add(new SystemMessage("你是一个旅游规划师"));
        messages.add(new UserMessage("我想去新疆"));
        ChatResponse response = chatModel.call(new Prompt(messages));
        String content = response.getResult().getOutput().getText();
        messages.add(new AssistantMessage(content));

        //第二轮对话
        messages.add(new UserMessage("能帮我推荐一些旅游景点吗?"));
        response = chatModel.call(new Prompt(messages));
        content = response.getResult().getOutput().getText();
        messages.add(new AssistantMessage(content));

        //第三轮对话
        messages.add(new UserMessage("那里这两天的天气如何?"));
        response = chatModel.call(new Prompt(messages));
        content = response.getResult().getOutput().getText();

        System.out.printf("content: %s\n", content);
    }
}
