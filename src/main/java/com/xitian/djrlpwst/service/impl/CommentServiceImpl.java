package com.xitian.djrlpwst.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.base.service.BaseServiceImpl;
import com.xitian.djrlpwst.converter.AttractionCommentConverter;
import com.xitian.djrlpwst.converter.CommentConverter;
import com.xitian.djrlpwst.domain.entity.Attraction;
import com.xitian.djrlpwst.domain.entity.Comment;
import com.xitian.djrlpwst.domain.entity.User;
import com.xitian.djrlpwst.domain.query.CommentQuery;
import com.xitian.djrlpwst.domain.vo.AttractionCommentVO;
import com.xitian.djrlpwst.domain.vo.CommentVO;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.mapper.AttractionMapper;
import com.xitian.djrlpwst.mapper.CommentMapper;
import com.xitian.djrlpwst.mapper.UserMapper;
import com.xitian.djrlpwst.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {
    
    @Autowired
    private CommentMapper commentMapper;
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private AttractionMapper attractionMapper;
    
    @Autowired
    private AttractionCommentConverter attractionCommentConverter;

    @Autowired
    private CommentConverter commentConverter;

    private static final DateTimeFormatter DATE_TIME_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    @Override
    public PageBean<AttractionCommentVO> getAttractionComments(Page<Comment> page, CommentQuery query) {
        // 构建查询条件
        LambdaQueryWrapper<Comment> wrapper = Wrappers.lambdaQuery();
        wrapper.isNotNull(Comment::getAttractionId); // 只查询景点评论
        
        // 添加其他查询条件
        if (query != null) {
            if (query.getUserId() != null) {
                wrapper.eq(Comment::getUserId, query.getUserId());
            }
            
            if (query.getRating() != null) {
                wrapper.eq(Comment::getRating, query.getRating());
            }
            
            if (query.getIsApproved() != null) {
                wrapper.eq(Comment::getIsApproved, query.getIsApproved());
            }
            
            // 添加景点ID查询条件
            if (query.getAttractionId() != null) {
                wrapper.eq(Comment::getAttractionId, query.getAttractionId());
            }
        }
        
        // 按创建时间倒序排列
        wrapper.orderByDesc(Comment::getCreateTime);
        
        // 执行分页查询
        Page<Comment> commentPage = commentMapper.selectPage(page, wrapper);
        
        // 获取用户ID和景点ID列表
        List<Long> userIds = commentPage.getRecords().stream()
            .map(Comment::getUserId)
            .distinct()
            .collect(Collectors.toList());
            
        List<Long> attractionIds = commentPage.getRecords().stream()
            .map(Comment::getAttractionId)
            .distinct()
            .collect(Collectors.toList());
        
        // 批量查询用户和景点信息
        List<User> users = userMapper.selectBatchIds(userIds);
        List<Attraction> attractions = attractionMapper.selectBatchIds(attractionIds);
        
        // 转换为VO对象
        List<AttractionCommentVO> voList = attractionCommentConverter.toVOList(
            commentPage.getRecords(), users, attractions);
        
        // 封装分页结果
        PageBean<AttractionCommentVO> pageBean = new PageBean<>();
        pageBean.setTotal(commentPage.getTotal());
        pageBean.setList(voList);
        pageBean.setPageNum((int) commentPage.getCurrent());
        pageBean.setPageSize((int) commentPage.getSize());
        
        return pageBean;
    }
    
    @Override
    public boolean addAttractionComment(Long userId, Long attractionId, String content, Integer rating) {
        // 创建评论对象
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setAttractionId(attractionId);
        comment.setContent(content);
        comment.setRating(rating);
        comment.setIsApproved(0); // 默认未审核
        
        // 保存到数据库
        return commentMapper.insert(comment) > 0;
    }
    
    @Override
    public boolean deleteAttractionComment(Long commentId, Long userId) {
        // 查询评论是否存在且属于该用户
        LambdaQueryWrapper<Comment> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Comment::getId, commentId)
               .eq(Comment::getUserId, userId)
               .isNotNull(Comment::getAttractionId); // 确保是景点评论
        
        Comment comment = commentMapper.selectOne(wrapper);
        if (comment == null) {
            return false; // 评论不存在或不属于该用户
        }
        
        // 删除评论（逻辑删除）
        return commentMapper.deleteById(commentId) > 0;
    }

    @Override
    public PageBean<CommentVO> getAttractionCommentsAdmin(PageParam<CommentQuery> param) {
        return getAdminPageByType(param, "attraction");
    }

    @Override
    public PageBean<CommentVO> getRestaurantCommentsAdmin(PageParam<CommentQuery> param) {
        return getAdminPageByType(param, "restaurant");
    }

    @Override
    public PageBean<CommentVO> getAccommodationCommentsAdmin(PageParam<CommentQuery> param) {
        return getAdminPageByType(param, "accommodation");
    }

    private PageBean<CommentVO> getAdminPageByType(PageParam<CommentQuery> param, String type) {
        Page<Comment> page = new Page<>(param.getPageNum(), param.getPageSize());
        LambdaQueryWrapper<Comment> wrapper = Wrappers.lambdaQuery();
        if ("attraction".equals(type)) {
            wrapper.isNotNull(Comment::getAttractionId);
        } else if ("restaurant".equals(type)) {
            wrapper.isNotNull(Comment::getRestaurantId);
        } else if ("accommodation".equals(type)) {
            wrapper.isNotNull(Comment::getAccommodationId);
        }
        CommentQuery query = param.getQuery();
        if (query != null) {
            if (query.getUserId() != null) {
                wrapper.eq(Comment::getUserId, query.getUserId());
            }
            if (query.getRating() != null) {
                wrapper.eq(Comment::getRating, query.getRating());
            }
            if (query.getIsApproved() != null) {
                wrapper.eq(Comment::getIsApproved, query.getIsApproved());
            }
            if (StringUtils.hasText(query.getKeyword())) {
                wrapper.like(Comment::getContent, query.getKeyword());
            }
            LocalDateTime start = parseStart(query.getCreateTimeStart());
            if (start != null) {
                wrapper.ge(Comment::getCreateTime, start);
            }
            LocalDateTime end = parseEnd(query.getCreateTimeEnd());
            if (end != null) {
                wrapper.le(Comment::getCreateTime, end);
            }
            if ("attraction".equals(type) && query.getAttractionId() != null) {
                wrapper.eq(Comment::getAttractionId, query.getAttractionId());
            }
            if ("restaurant".equals(type) && query.getRestaurantId() != null) {
                wrapper.eq(Comment::getRestaurantId, query.getRestaurantId());
            }
            if ("accommodation".equals(type) && query.getAccommodationId() != null) {
                wrapper.eq(Comment::getAccommodationId, query.getAccommodationId());
            }
        }
        String sortField = param.getSortField();
        Sort.Direction sortDirection = param.getSortDirection();
        if (StringUtils.hasText(sortField)) {
            boolean isAsc = sortDirection == Sort.Direction.ASC;
            switch (sortField) {
                case "id":
                    wrapper.orderBy(true, isAsc, Comment::getId);
                    break;
                case "rating":
                    wrapper.orderBy(true, isAsc, Comment::getRating);
                    break;
                case "isApproved":
                    wrapper.orderBy(true, isAsc, Comment::getIsApproved);
                    break;
                case "createTime":
                    wrapper.orderBy(true, isAsc, Comment::getCreateTime);
                    break;
                case "updateTime":
                    wrapper.orderBy(true, isAsc, Comment::getUpdateTime);
                    break;
                default:
                    wrapper.orderByDesc(Comment::getCreateTime);
            }
        } else {
            wrapper.orderByDesc(Comment::getCreateTime);
        }
        Page<Comment> result = commentMapper.selectPage(page, wrapper);
        List<CommentVO> voList = commentConverter.toVOList(result.getRecords());
        PageBean<CommentVO> pageBean = new PageBean<>();
        pageBean.setTotal(result.getTotal());
        pageBean.setList(voList);
        pageBean.setPageNum((int) result.getCurrent());
        pageBean.setPageSize((int) result.getSize());
        return pageBean;
    }

    private LocalDateTime parseStart(String value) {
        if (!StringUtils.hasText(value)) return null;
        String v = value.trim();
        if (v.length() == 10) return LocalDateTime.parse(v + " 00:00:00", DATE_TIME_FMT);
        return LocalDateTime.parse(v, DATE_TIME_FMT);
    }

    private LocalDateTime parseEnd(String value) {
        if (!StringUtils.hasText(value)) return null;
        String v = value.trim();
        if (v.length() == 10) return LocalDateTime.parse(v + " 23:59:59", DATE_TIME_FMT);
        return LocalDateTime.parse(v, DATE_TIME_FMT);
    }
}
