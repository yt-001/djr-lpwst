package com.xitian.djrlpwst.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.ResultBean;
import com.xitian.djrlpwst.bean.StatusCode;
import com.xitian.djrlpwst.bean.base.BaseController;
import com.xitian.djrlpwst.domain.dto.AttractionCommentDTO;
import com.xitian.djrlpwst.domain.entity.Comment;
import com.xitian.djrlpwst.domain.query.CommentQuery;
import com.xitian.djrlpwst.domain.vo.AttractionCommentVO;
import com.xitian.djrlpwst.domain.vo.CommentVO;
import com.xitian.djrlpwst.service.CommentService;
import com.xitian.djrlpwst.util.BeanUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@Tag(name = "评论管理", description = "评论管理接口")
public class CommentController extends BaseController<Comment> {
    
    @Autowired
    private CommentService commentService;
    
    @PostMapping("/page")
    @Operation(summary = "分页查询评论")
    public ResultBean<PageBean<CommentVO>> page(@RequestBody PageParam<CommentQuery> param) {
        PageBean<CommentVO> page = commentService.getAttractionCommentsAdmin(param);
        return ResultBean.success(page);
    }
    
    @PostMapping("/attractions/page")
    @Operation(summary = "分页查询景点评论")
    public ResultBean<PageBean<AttractionCommentVO>> attractionCommentsPage(@RequestBody PageParam<CommentQuery> param) {
        // 创建分页对象
        Page<Comment> page = new Page<>(param.getPageNum(), param.getPageSize());
        
        // 获取分页数据
        PageBean<AttractionCommentVO> pageBean = commentService.getAttractionComments(page, param.getQuery());
        
        return ResultBean.success(pageBean);
    }

    @PostMapping("/admin/attractions/page")
    @Operation(summary = "管理员分页查询景点评论")
    public ResultBean<PageBean<CommentVO>> adminAttractionCommentsPage(@RequestBody PageParam<CommentQuery> param) {
        return ResultBean.success(commentService.getAttractionCommentsAdmin(param));
    }

    @PostMapping("/admin/restaurants/page")
    @Operation(summary = "管理员分页查询餐饮评论")
    public ResultBean<PageBean<CommentVO>> adminRestaurantCommentsPage(@RequestBody PageParam<CommentQuery> param) {
        return ResultBean.success(commentService.getRestaurantCommentsAdmin(param));
    }

    @PostMapping("/admin/accommodations/page")
    @Operation(summary = "管理员分页查询住宿评论")
    public ResultBean<PageBean<CommentVO>> adminAccommodationCommentsPage(@RequestBody PageParam<CommentQuery> param) {
        return ResultBean.success(commentService.getAccommodationCommentsAdmin(param));
    }
    
    @PostMapping("/attractions/add")
    @Operation(summary = "添加景点评论")
    public ResultBean<Void> addAttractionComment(@RequestBody AttractionCommentDTO dto) {
        boolean result = commentService.addAttractionComment(
            dto.getUserId(), dto.getAttractionId(), dto.getContent(), dto.getRating());
            
        if (result) {
            return ResultBean.success();
        } else {
            return ResultBean.fail(StatusCode.BUSINESS_ERROR, "评论添加失败");
        }
    }
    
    @DeleteMapping("/attractions/{commentId}")
    @Operation(summary = "删除景点评论")
    public ResultBean<Void> deleteAttractionComment(@PathVariable Long commentId,
                                                    @RequestParam Long userId) {
        boolean result = commentService.deleteAttractionComment(commentId, userId);
        
        if (result) {
            return ResultBean.success();
        } else {
            return ResultBean.fail(StatusCode.BUSINESS_ERROR, "评论删除失败，可能评论不存在或无权限删除");
        }
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询评论")
    public ResultBean<CommentVO> getById(@PathVariable Long id) {
        // TODO: 实现根据ID查询逻辑
        return ResultBean.success();
    }
    
    @PostMapping
    @Operation(summary = "新增评论")
    public ResultBean<Void> add(@RequestBody Comment entity) {
        // TODO: 实现新增逻辑
        return ResultBean.success();
    }
    
    @PutMapping
    @Operation(summary = "修改评论")
    public ResultBean<Void> update(@RequestBody Comment entity) {
        if (entity.getId() == null) {
            return ResultBean.fail(StatusCode.VALIDATION_ERROR, "评论ID不能为空");
        }
        Comment existing = commentService.getById(entity.getId());
        if (existing == null) {
            return ResultBean.fail(StatusCode.DATA_NOT_FOUND, "评论不存在");
        }
        BeanUtil.copyNonNullProperties(entity, existing);
        commentService.updateById(existing);
        return ResultBean.success();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "删除评论")
    public ResultBean<Void> delete(@PathVariable Long id) {
        boolean ok = commentService.removeById(id);
        if (!ok) {
            return ResultBean.fail(StatusCode.DATA_NOT_FOUND, "评论不存在");
        }
        return ResultBean.success();
    }
    
    @PostMapping("/list")
    @Operation(summary = "列表查询评论")
    public ResultBean<List<CommentVO>> list(@RequestBody CommentQuery query) {
        // TODO: 实现列表查询逻辑
        return ResultBean.success();
    }
}
