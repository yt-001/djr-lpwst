package com.xitian.djrlpwst.controller;

import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.ResultBean;
import com.xitian.djrlpwst.bean.StatusCode;
import com.xitian.djrlpwst.domain.entity.AiGreeting;
import com.xitian.djrlpwst.domain.entity.AiPrompt;
import com.xitian.djrlpwst.domain.query.AiGreetingQuery;
import com.xitian.djrlpwst.domain.query.AiPromptQuery;
import com.xitian.djrlpwst.service.AiGreetingService;
import com.xitian.djrlpwst.service.AiPromptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai/admin")
@Tag(name = "AI 管理", description = "AI 问候语与推荐问题管理接口")
public class AdminAiController {

    @Autowired
    private AiGreetingService aiGreetingService;

    @Autowired
    private AiPromptService aiPromptService;

    @PostMapping("/greetings/page")
    @Operation(summary = "分页查询问候语")
    public ResultBean<PageBean<AiGreeting>> pageGreetings(@RequestBody PageParam<AiGreetingQuery> param) {
        if (param.getQuery() == null) {
            param.setQuery(new AiGreetingQuery());
        }
        return ResultBean.success(aiGreetingService.getPage(param));
    }

    @GetMapping("/greetings/{id}")
    @Operation(summary = "查询问候语详情")
    public ResultBean<AiGreeting> getGreeting(@PathVariable Long id) {
        AiGreeting entity = aiGreetingService.getById(id);
        if (entity == null) {
            return ResultBean.fail(StatusCode.DATA_NOT_FOUND, "问候语不存在");
        }
        return ResultBean.success(entity);
    }

    @PostMapping("/greetings")
    @Operation(summary = "新增问候语")
    public ResultBean<Void> createGreeting(@RequestBody AiGreeting entity) {
        aiGreetingService.save(entity);
        return ResultBean.success();
    }

    @PutMapping("/greetings")
    @Operation(summary = "更新问候语")
    public ResultBean<Void> updateGreeting(@RequestBody AiGreeting entity) {
        if (entity.getId() == null) {
            return ResultBean.fail(StatusCode.VALIDATION_ERROR, "ID 不能为空");
        }
        AiGreeting existing = aiGreetingService.getById(entity.getId());
        if (existing == null) {
            return ResultBean.fail(StatusCode.DATA_NOT_FOUND, "问候语不存在");
        }
        aiGreetingService.updateById(entity);
        return ResultBean.success();
    }

    @DeleteMapping("/greetings/{id}")
    @Operation(summary = "删除问候语")
    public ResultBean<Void> deleteGreeting(@PathVariable Long id) {
        boolean ok = aiGreetingService.removeById(id);
        if (!ok) {
            return ResultBean.fail(StatusCode.DATA_NOT_FOUND, "问候语不存在");
        }
        return ResultBean.success();
    }

    @PostMapping("/prompts/page")
    @Operation(summary = "分页查询推荐问题")
    public ResultBean<PageBean<AiPrompt>> pagePrompts(@RequestBody PageParam<AiPromptQuery> param) {
        if (param.getQuery() == null) {
            param.setQuery(new AiPromptQuery());
        }
        return ResultBean.success(aiPromptService.getPage(param));
    }

    @GetMapping("/prompts/{id}")
    @Operation(summary = "查询推荐问题详情")
    public ResultBean<AiPrompt> getPrompt(@PathVariable Long id) {
        AiPrompt entity = aiPromptService.getById(id);
        if (entity == null) {
            return ResultBean.fail(StatusCode.DATA_NOT_FOUND, "推荐问题不存在");
        }
        return ResultBean.success(entity);
    }

    @PostMapping("/prompts")
    @Operation(summary = "新增推荐问题")
    public ResultBean<Void> createPrompt(@RequestBody AiPrompt entity) {
        aiPromptService.save(entity);
        return ResultBean.success();
    }

    @PutMapping("/prompts")
    @Operation(summary = "更新推荐问题")
    public ResultBean<Void> updatePrompt(@RequestBody AiPrompt entity) {
        if (entity.getId() == null) {
            return ResultBean.fail(StatusCode.VALIDATION_ERROR, "ID 不能为空");
        }
        AiPrompt existing = aiPromptService.getById(entity.getId());
        if (existing == null) {
            return ResultBean.fail(StatusCode.DATA_NOT_FOUND, "推荐问题不存在");
        }
        aiPromptService.updateById(entity);
        return ResultBean.success();
    }

    @DeleteMapping("/prompts/{id}")
    @Operation(summary = "删除推荐问题")
    public ResultBean<Void> deletePrompt(@PathVariable Long id) {
        boolean ok = aiPromptService.removeById(id);
        if (!ok) {
            return ResultBean.fail(StatusCode.DATA_NOT_FOUND, "推荐问题不存在");
        }
        return ResultBean.success();
    }
}
