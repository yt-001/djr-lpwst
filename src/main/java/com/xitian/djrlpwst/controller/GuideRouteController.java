package com.xitian.djrlpwst.controller;

import com.xitian.djrlpwst.bean.ResultBean;
import com.xitian.djrlpwst.domain.dto.GuideRouteCreateDTO;
import com.xitian.djrlpwst.domain.dto.GuideRouteWorkflowUpdateDTO;
import com.xitian.djrlpwst.domain.entity.GuideRoute;
import com.xitian.djrlpwst.domain.vo.GuideRouteCardVO;
import com.xitian.djrlpwst.domain.vo.GuideRouteDetailVO;
import com.xitian.djrlpwst.service.GuideRouteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/guide-routes")
@Tag(name = "向导路线管理", description = "向导路线接口（暂不实现数据逻辑）")
public class GuideRouteController {

    @Autowired
    private GuideRouteService guideRouteService;

    @PostMapping
    @Operation(summary = "新增向导路线")
    public ResultBean<Long> add(@Valid @RequestBody GuideRouteCreateDTO createDTO) {
        GuideRoute entity = GuideRoute.builder()
                .name(createDTO.getName())
                .description(createDTO.getDescription())
                .coverImage(createDTO.getCoverImage())
                .totalDistance(createDTO.getTotalDistance())
                .totalDuration(createDTO.getTotalDuration())
                .status((byte) 1)
                .editStatus(createDTO.getEditStatus() == null ? (byte) 1 : createDTO.getEditStatus())
                .build();
        guideRouteService.save(entity);
        return ResultBean.success(entity.getId());
    }

    @PutMapping("/{id}")
    @Operation(summary = "修改向导路线基础信息")
    public ResultBean<Void> update(@PathVariable Long id, @Valid @RequestBody GuideRouteCreateDTO updateDTO) {
        if (id == null) {
            return ResultBean.success();
        }
        GuideRoute update = new GuideRoute();
        update.setId(id);
        update.setName(updateDTO.getName());
        update.setDescription(updateDTO.getDescription());
        update.setCoverImage(updateDTO.getCoverImage());
        update.setTotalDistance(updateDTO.getTotalDistance());
        update.setTotalDuration(updateDTO.getTotalDuration());
        update.setStatus((byte) 1);
        if (updateDTO.getEditStatus() != null) {
            update.setEditStatus(updateDTO.getEditStatus());
        }
        guideRouteService.updateById(update);
        return ResultBean.success();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除向导路线")
    public ResultBean<Void> delete(@PathVariable Long id) {
        return ResultBean.success();
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询向导路线详情（包含节点与连线）")
    public ResultBean<GuideRouteDetailVO> getById(@PathVariable Long id) {
        GuideRouteDetailVO detail = guideRouteService.getRouteDetail(id);
        return ResultBean.success(detail);
    }

    @PutMapping("/{id}/workflow")
    @Operation(summary = "更新向导路线工作流布局和连线")
    public ResultBean<Void> updateWorkflow(@PathVariable Long id, @RequestBody GuideRouteWorkflowUpdateDTO dto) {
        guideRouteService.updateRouteWorkflow(id, dto);
        return ResultBean.success();
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询向导路线")
    public ResultBean<Object> page() {
        return ResultBean.success(null);
    }

    @GetMapping("/cards")
    @Operation(summary = "向导图首页卡片列表（已发布）")
    public ResultBean<List<GuideRouteCardVO>> cards() {
        List<GuideRouteCardVO> list = guideRouteService.getHomeCardList();
        return ResultBean.success(list);
    }

    @GetMapping("/drafts")
    @Operation(summary = "向导图草稿卡片列表（暂存）")
    public ResultBean<List<GuideRouteCardVO>> draftCards() {
        List<GuideRouteCardVO> list = guideRouteService.getDraftCardList();
        return ResultBean.success(list);
    }
}
