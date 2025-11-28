package com.xitian.djrlpwst.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.base.service.BaseService;
import com.xitian.djrlpwst.domain.entity.Comment;
import com.xitian.djrlpwst.domain.query.CommentQuery;
import com.xitian.djrlpwst.domain.vo.AttractionCommentVO;

public interface CommentService extends BaseService<Comment> {
    
    /**
     * 分页查询景点评论
     * @param page 分页参数
     * @param query 查询条件
     * @return 景点评论分页数据
     */
    PageBean<AttractionCommentVO> getAttractionComments(Page<Comment> page, CommentQuery query);
    
    /**
     * 添加景点评论
     * @param userId 用户ID
     * @param attractionId 景点ID
     * @param content 评论内容
     * @param rating 评分
     * @return 是否添加成功
     */
    boolean addAttractionComment(Long userId, Long attractionId, String content, Integer rating);
    
    /**
     * 删除景点评论（逻辑删除）
     * @param commentId 评论ID
     * @param userId 用户ID（用于权限验证）
     * @return 是否删除成功
     */
    boolean deleteAttractionComment(Long commentId, Long userId);
}