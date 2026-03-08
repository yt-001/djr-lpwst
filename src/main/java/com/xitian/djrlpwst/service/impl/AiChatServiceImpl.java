package com.xitian.djrlpwst.service.impl;

import com.xitian.djrlpwst.config.AiProperties;
import com.xitian.djrlpwst.service.AiChatService;
import com.xitian.djrlpwst.service.AiKnowledgeService;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Duration;

@Service
public class AiChatServiceImpl implements AiChatService {

    private final AiProperties properties;
    private final ChatLanguageModel model;
    private final AiKnowledgeService aiKnowledgeService;

    public AiChatServiceImpl(AiProperties properties, AiKnowledgeService aiKnowledgeService) {
        this.properties = properties;
        this.aiKnowledgeService = aiKnowledgeService;
        this.model = OpenAiChatModel.builder()
            .apiKey(properties.getApiKey())
            .baseUrl(properties.getBaseUrl())
            .modelName(properties.getModel())
            .temperature(properties.getTemperature())
            .timeout(Duration.ofSeconds(properties.getTimeoutSeconds()))
            .build();
    }

    @Override
    public String chat(String message) {
        if (!StringUtils.hasText(properties.getApiKey())) {
            throw new IllegalStateException("未配置 AI Key");
        }
        String prompt = buildPrompt(message);
        return model.generate(prompt);
    }

    private String buildPrompt(String message) {
        StringBuilder sb = new StringBuilder();
        sb.append("你是重庆市梁平区文旅助手，回答要聚焦梁平的历史文化、非遗、景点、美食、路线与出行建议。");
        sb.append("如果问题与梁平无关，请礼貌引导到梁平话题，并给出相关建议。");
        sb.append("回答使用中文，简洁准确。");
        String context = aiKnowledgeService.buildContext();
        if (StringUtils.hasText(context)) {
            sb.append("\n知识要点：\n").append(context);
        }
        sb.append("\n用户问题：").append(message);
        return sb.toString();
    }
}
