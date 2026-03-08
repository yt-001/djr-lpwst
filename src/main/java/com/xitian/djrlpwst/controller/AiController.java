package com.xitian.djrlpwst.controller;

import com.xitian.djrlpwst.bean.ResultBean;
import com.xitian.djrlpwst.bean.StatusCode;
import com.xitian.djrlpwst.bean.base.BaseController;
import com.xitian.djrlpwst.domain.dto.AiChatDTO;
import com.xitian.djrlpwst.domain.dto.AiKnowledgeCreateDTO;
import com.xitian.djrlpwst.domain.dto.AiKnowledgeUpdateDTO;
import com.xitian.djrlpwst.domain.entity.AiGreeting;
import com.xitian.djrlpwst.domain.entity.AiPrompt;
import com.xitian.djrlpwst.domain.vo.AiKnowledgeVO;
import com.xitian.djrlpwst.service.AiChatService;
import com.xitian.djrlpwst.service.AiGreetingService;
import com.xitian.djrlpwst.service.AiKnowledgeService;
import com.xitian.djrlpwst.service.AiPromptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ai")
@Tag(name = "AI 接口", description = "AI 对话与知识管理接口")
public class AiController extends BaseController<Object> {

    @Autowired
    private AiChatService aiChatService;

    @Autowired
    private AiKnowledgeService aiKnowledgeService;

    @Autowired
    private AiGreetingService aiGreetingService;

    @Autowired
    private AiPromptService aiPromptService;

    @PostMapping("/chat")
    @Operation(summary = "AI 对话")
    public ResultBean<String> chat(@Valid @RequestBody AiChatDTO dto) {
        try {
            return ResultBean.success(aiChatService.chat(dto.getMessage()));
        } catch (Exception e) {
            return ResultBean.fail(StatusCode.FAIL, e.getMessage());
        }
    }

    @PostMapping("/knowledge")
    @Operation(summary = "新增 AI 知识")
    public ResultBean<Long> createKnowledge(@Valid @RequestBody AiKnowledgeCreateDTO dto) {
        AiKnowledgeVO vo = aiKnowledgeService.create(dto);
        return ResultBean.success(vo.getId());
    }

    @GetMapping("/knowledge/{id}")
    @Operation(summary = "查询 AI 知识详情")
    public ResultBean<AiKnowledgeVO> getKnowledge(@PathVariable Long id) {
        AiKnowledgeVO vo = aiKnowledgeService.getById(id);
        if (vo == null) {
            return ResultBean.fail(StatusCode.DATA_NOT_FOUND, "知识不存在");
        }
        return ResultBean.success(vo);
    }

    @PutMapping("/knowledge/{id}")
    @Operation(summary = "更新 AI 知识")
    public ResultBean<AiKnowledgeVO> updateKnowledge(@PathVariable Long id, @RequestBody AiKnowledgeUpdateDTO dto) {
        AiKnowledgeVO vo = aiKnowledgeService.update(id, dto);
        if (vo == null) {
            return ResultBean.fail(StatusCode.DATA_NOT_FOUND, "知识不存在");
        }
        return ResultBean.success(vo);
    }

    @DeleteMapping("/knowledge/{id}")
    @Operation(summary = "删除 AI 知识")
    public ResultBean<Void> deleteKnowledge(@PathVariable Long id) {
        boolean ok = aiKnowledgeService.delete(id);
        if (!ok) {
            return ResultBean.fail(StatusCode.DATA_NOT_FOUND, "知识不存在");
        }
        return ResultBean.success();
    }

    @GetMapping("/knowledge/list")
    @Operation(summary = "AI 知识列表")
    public ResultBean<List<AiKnowledgeVO>> listKnowledge(@RequestParam(required = false) String keyword) {
        return ResultBean.success(aiKnowledgeService.list(keyword));
    }

    @GetMapping("/greetings/random")
    @Operation(summary = "随机问候语")
    public ResultBean<String> randomGreeting() {
        AiGreeting greeting = aiGreetingService.getRandomEnabled();
        if (greeting == null) {
            return ResultBean.success("");
        }
        return ResultBean.success(greeting.getContent());
    }

    @GetMapping("/prompts/list")
    @Operation(summary = "推荐问题列表")
    public ResultBean<List<String>> listPrompts(@RequestParam(required = false) Integer limit) {
        List<AiPrompt> prompts = aiPromptService.listEnabled(limit);
        List<String> result = prompts.stream().map(AiPrompt::getContent).collect(Collectors.toList());
        return ResultBean.success(result);
    }

    @GetMapping("/prompts/random")
    @Operation(summary = "随机推荐问题列表")
    public ResultBean<List<String>> randomPrompts(@RequestParam(required = false) Integer limit) {
        List<AiPrompt> prompts = aiPromptService.listRandomEnabled(limit);
        List<String> result = prompts.stream().map(AiPrompt::getContent).collect(Collectors.toList());
        return ResultBean.success(result);
    }
}
