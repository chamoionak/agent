package com.yupi.yuaiagent.agent;

import cn.hutool.core.util.StrUtil;
import com.yupi.yuaiagent.agent.model.AgentState;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象基础代理类，用于管理代理状态和执行流程。
 * 提供状态转换、内存管理和基于步骤的执行循环的基础功能。
 * 子类必须实现step方法。
 */
@Data
@Slf4j
public abstract class BaseAgent {

    //核心属性
    private String name;

    //提示词
    private String systemPrompt;
    private String nextStepPrompt;

    //代理状态
    private AgentState state=AgentState.IDLE;

    //执行步骤控制
    private int currentStep=0;
    private int maxStep=10;

    //LLM大模型
    private ChatClient chatClient;

    //Memory记忆（需要自主维护上下文）
    private List<Message>messageList=new ArrayList<>();

    /**
     * 运行代理
     * @param userPrompt
     * @return
     */
    public String run(String userPrompt){
        //基础校验
        if(this.state!=AgentState.IDLE){
            throw new IllegalStateException("Agent is not in IDLE state");
        }
        if (StrUtil.isBlank(userPrompt)){
            throw new IllegalArgumentException("User prompt cannot be blank");
        }
        //执行，更改状态
        this.state=AgentState.RUNNING;
        //记录消息上下文
        messageList.add(new UserMessage(userPrompt));
        //保存结果列表
        List<String> results=new ArrayList<>();
        try{
        //执行循环
        for (int i=0;i<maxStep&&state!=AgentState.FINISHED;++i){
            int stepNumber=i+1;
            currentStep=stepNumber;
           log.info("Running step {} of max {}",stepNumber,maxStep);
           //单步执行
            String stepResult=step();
            String result="step"+stepNumber+":"+stepResult;
            results.add(result);
        }
        //检查是否超出步骤限制
        if(currentStep>=maxStep){
            state=AgentState.FINISHED;
            log.warn("Max step reached, agent stopped"+maxStep);
        }
        return String.join("\n",results);
    }catch (Exception e) {
            state=AgentState.ERROR;
            log.error("Error occurred during agent execution",e);
            return "An error occurred";
        }finally {
            this.cleanup();
        }
    }


    /**
     * 执行单个步骤
     *
     * @return 步骤执行结果
     */
    public abstract String step();

    /**
     * 清理资源
     */
    protected void cleanup() {
        // 子类可以重写此方法来清理资源
    }

}
