package com.niu.web;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description ai对话
 * @date 2024/5/6 22:55:34
 */
@RestController
public class ChatAIController {

    @Resource
    private OpenAiChatClient openAiChatClient;

    @PreAuthorize("hasAnyAuthority('ai:chat')")
    @PostMapping("/api/chat")
    public Object chat1(@RequestBody String message) {
        //代码中的配置项会覆盖掉配置文件里的配置
        OpenAiChatOptions openAiChatOptions = OpenAiChatOptions.builder()
                .withModel("gpt-3.5-turbo")//指定版本
                .withTemperature(0.4F)//温度越高，回答的准确率（创新性）下降，越低，准确率上升
                .build();
        ChatResponse chatResponse = openAiChatClient.call(new Prompt(message, openAiChatOptions));
        return chatResponse.getResult().getOutput().getContent();
    }

}
