package com.yupi.yuaiagent.rag;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.KeywordMetadataEnricher;
import org.springframework.stereotype.Component;

import java.util.List;

//基于AI的文档元信息增强器（为文档补充元信息）
@Component
public class MyKeywordEnricher {
    @Resource
    private ChatModel dashscopeChatModel;
    public List<Document> enrichDocuments(List<Document> documents) {
        // 使用 dashscopeChatModel 对文档进行关键字抽取和摘要生成，并返回结果列表
        KeywordMetadataEnricher keywordMetadataEnricher = new KeywordMetadataEnricher(dashscopeChatModel, 5);
        return keywordMetadataEnricher.apply(documents);
    }
}
