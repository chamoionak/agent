package com.yupi.yuaiagent.app;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoveAppTest {

    @Resource
    private LoveApp loveApp;
    @Test
    void doChat() {
        String chatId = UUID.randomUUID().toString();

        //第一轮
        String message="你好，我是程序员小蝈蝈";
        String answer= loveApp.doChat(message,chatId);
        //第二轮
        message="你找找个看我的另一半是谁";
        answer= loveApp.doChat(message,chatId);
        Assertions.assertNotNull(answer);
        //第三轮
        message="你好，你记得我叫什么吗";
        answer= loveApp.doChat(message,chatId);
        Assertions.assertNotNull(answer);
    }

    @Test
    void doChatWithReport() {
        String chatId = UUID.randomUUID().toString();

        //第一轮
        String message="你好，我是程序员小蝈蝈";
        LoveApp.LoveReport loveReport = loveApp.doChatWithReport(message, chatId);
        Assertions.assertNotNull(loveReport);
    }

    @Test
    void doChatWithRag() {
        String chatId = UUID.randomUUID().toString();
        String message="怎样给恋爱中的对方制造浪漫惊喜？";
        String answer = loveApp.doChatWithRag(message, chatId);
        Assertions.assertNotNull(answer);
    }
}