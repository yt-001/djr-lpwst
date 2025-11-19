package com.xitian.djrlpwst.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.base.service.BaseServiceImpl;
import com.xitian.djrlpwst.converter.OrderConverter;
import com.xitian.djrlpwst.domain.entity.Order;
import com.xitian.djrlpwst.domain.query.OrderQuery;
import com.xitian.djrlpwst.domain.vo.OrderAdminVO;
import com.xitian.djrlpwst.domain.vo.OrderVO;
import com.xitian.djrlpwst.mapper.OrderMapper;
import com.xitian.djrlpwst.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {
    
    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private OrderConverter orderConverter;
    
    // 订单编号生成器
    private final AtomicLong orderNoGenerator = new AtomicLong(System.currentTimeMillis());
    
    @Override
    public String generateOrderNo() {
        // 生成订单编号：当前日期时间 + 自增序列
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String sequence = String.format("%06d", orderNoGenerator.getAndIncrement() % 1000000);
        return "ORD" + dateTime + sequence;
    }

    @Override
    public PageBean<OrderVO> getPage(PageParam<OrderQuery> param) {
        // 获取分页参数
        int pageNum = param.getPageNum();
        int pageSize = param.getPageSize();
        OrderQuery query = param.getQuery();
        
        // 创建分页对象和查询条件构造器
        Page<Order> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Order> wrapper = Wrappers.lambdaQuery();
        
        // 构建查询条件
        if (query != null) {
            if (StringUtils.hasText(query.getOrderNo())) {
                wrapper.like(Order::getOrderNo, query.getOrderNo());
            }
            
            if (query.getUserId() != null) {
                wrapper.eq(Order::getUserId, query.getUserId());
            }
            
            if (query.getProductType() != null) {
                wrapper.eq(Order::getProductType, query.getProductType());
            }
            
            // 处理多个状态查询
            if (query.getStatusList() != null && !query.getStatusList().isEmpty()) {
                wrapper.in(Order::getStatus, query.getStatusList());
            }
        }
        
        // 根据前端传递的排序字段和排序方向进行排序
        String sortField = param.getSortField();
        Sort.Direction sortDirection = param.getSortDirection();
        
        // 支持多种排序字段
        if (StringUtils.hasText(sortField)) {
            switch (sortField) {
                case "orderNo":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getOrderNo);
                    } else {
                        wrapper.orderByAsc(Order::getOrderNo);
                    }
                    break;
                case "createTime":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getCreateTime);
                    } else {
                        wrapper.orderByAsc(Order::getCreateTime);
                    }
                    break;
                case "totalAmount":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getTotalAmount);
                    } else {
                        wrapper.orderByAsc(Order::getTotalAmount);
                    }
                    break;
                default:
                    // 不支持的排序字段，不添加排序条件，使用数据库默认顺序
                    break;
            }
        }
        // 如果没有指定排序字段，则不添加排序条件，使用数据库默认顺序
        
        // 执行分页查询
        Page<Order> result = orderMapper.selectPage(page, wrapper);
        
        // 转换结果为VO对象
        List<OrderVO> voList = orderConverter.toVOList(result.getRecords());
        
        // 封装分页结果
        PageBean<OrderVO> pageBean = new PageBean<>();
        pageBean.setTotal(result.getTotal());
        pageBean.setList(voList);
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        
        return pageBean;
    }

    @Override
    public PageBean<OrderAdminVO> getAdminPage(PageParam<OrderQuery> param) {
        // 获取分页参数
        int pageNum = param.getPageNum();
        int pageSize = param.getPageSize();
        OrderQuery query = param.getQuery();
        
        // 创建分页对象和查询条件构造器
        Page<Order> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Order> wrapper = Wrappers.lambdaQuery();
        
        // 构建查询条件
        if (query != null) {
            if (StringUtils.hasText(query.getOrderNo())) {
                wrapper.like(Order::getOrderNo, query.getOrderNo());
            }
            
            if (query.getUserId() != null) {
                wrapper.eq(Order::getUserId, query.getUserId());
            }
            
            if (query.getProductType() != null) {
                wrapper.eq(Order::getProductType, query.getProductType());
            }
            
            // 处理多个状态查询
            if (query.getStatusList() != null && !query.getStatusList().isEmpty()) {
                wrapper.in(Order::getStatus, query.getStatusList());
            }
        }
        
        // 根据前端传递的排序字段和排序方向进行排序
        String sortField = param.getSortField();
        Sort.Direction sortDirection = param.getSortDirection();
        
        // 支持多种排序字段
        if (StringUtils.hasText(sortField)) {
            switch (sortField) {
                case "orderNo":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getOrderNo);
                    } else {
                        wrapper.orderByAsc(Order::getOrderNo);
                    }
                    break;
                case "createTime":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getCreateTime);
                    } else {
                        wrapper.orderByAsc(Order::getCreateTime);
                    }
                    break;
                case "totalAmount":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getTotalAmount);
                    } else {
                        wrapper.orderByAsc(Order::getTotalAmount);
                    }
                    break;
                default:
                    // 不支持的排序字段，不添加排序条件，使用数据库默认顺序
                    break;
            }
        }
        // 如果没有指定排序字段，则不添加排序条件，使用数据库默认顺序
        
        // 执行分页查询
        Page<Order> result = orderMapper.selectPage(page, wrapper);
        
        // 转换结果为VO对象
        List<OrderAdminVO> voList = orderConverter.toAdminVOList(result.getRecords());
        
        // 封装分页结果
        PageBean<OrderAdminVO> pageBean = new PageBean<>();
        pageBean.setTotal(result.getTotal());
        pageBean.setList(voList);
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        
        return pageBean;
    }
    
    @Override
    public PageBean<OrderVO> getExpiredPaidOrders(PageParam<OrderQuery> param) {
        // 获取分页参数
        int pageNum = param.getPageNum();
        int pageSize = param.getPageSize();
        OrderQuery query = param.getQuery();
        
        // 创建分页对象和查询条件构造器
        Page<Order> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Order> wrapper = Wrappers.lambdaQuery();
        
        // 设置查询条件：状态为1(已支付)且过期时间早于当前时间
        wrapper.eq(Order::getStatus, 1);
        wrapper.lt(Order::getExpireTime, LocalDateTime.now());
        
        // 构建其他查询条件
        if (query != null) {
            if (StringUtils.hasText(query.getOrderNo())) {
                wrapper.like(Order::getOrderNo, query.getOrderNo());
            }
            
            if (query.getUserId() != null) {
                wrapper.eq(Order::getUserId, query.getUserId());
            }
            
            if (query.getProductType() != null) {
                wrapper.eq(Order::getProductType, query.getProductType());
            }
        }
        
        // 根据前端传递的排序字段和排序方向进行排序
        String sortField = param.getSortField();
        Sort.Direction sortDirection = param.getSortDirection();
        
        // 支持多种排序字段
        if (StringUtils.hasText(sortField)) {
            switch (sortField) {
                case "orderNo":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getOrderNo);
                    } else {
                        wrapper.orderByAsc(Order::getOrderNo);
                    }
                    break;
                case "createTime":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getCreateTime);
                    } else {
                        wrapper.orderByAsc(Order::getCreateTime);
                    }
                    break;
                case "totalAmount":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getTotalAmount);
                    } else {
                        wrapper.orderByAsc(Order::getTotalAmount);
                    }
                    break;
                case "expireTime":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getExpireTime);
                    } else {
                        wrapper.orderByAsc(Order::getExpireTime);
                    }
                    break;
                default:
                    // 不支持的排序字段，不添加排序条件，使用数据库默认顺序
                    break;
            }
        } else {
            // 默认按过期时间倒序排列
            wrapper.orderByDesc(Order::getExpireTime);
        }
        
        // 执行分页查询
        Page<Order> result = orderMapper.selectPage(page, wrapper);
        
        // 转换结果为VO对象
        List<OrderVO> voList = orderConverter.toVOList(result.getRecords());
        
        // 封装分页结果
        PageBean<OrderVO> pageBean = new PageBean<>();
        pageBean.setTotal(result.getTotal());
        pageBean.setList(voList);
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        
        return pageBean;
    }
    
    @Override
    public PageBean<OrderVO> getPendingValidOrders(PageParam<OrderQuery> param) {
        // 获取分页参数
        int pageNum = param.getPageNum();
        int pageSize = param.getPageSize();
        OrderQuery query = param.getQuery();
        
        // 创建分页对象和查询条件构造器
        Page<Order> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Order> wrapper = Wrappers.lambdaQuery();
        
        // 设置查询条件：状态为1(已支付)且过期时间晚于当前时间
        wrapper.eq(Order::getStatus, 1);
        wrapper.gt(Order::getExpireTime, LocalDateTime.now());
        
        // 构建其他查询条件
        if (query != null) {
            if (StringUtils.hasText(query.getOrderNo())) {
                wrapper.like(Order::getOrderNo, query.getOrderNo());
            }
            
            if (query.getUserId() != null) {
                wrapper.eq(Order::getUserId, query.getUserId());
            }
            
            if (query.getProductType() != null) {
                wrapper.eq(Order::getProductType, query.getProductType());
            }
        }
        
        // 根据前端传递的排序字段和排序方向进行排序
        String sortField = param.getSortField();
        Sort.Direction sortDirection = param.getSortDirection();
        
        // 支持多种排序字段
        if (StringUtils.hasText(sortField)) {
            switch (sortField) {
                case "orderNo":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getOrderNo);
                    } else {
                        wrapper.orderByAsc(Order::getOrderNo);
                    }
                    break;
                case "createTime":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getCreateTime);
                    } else {
                        wrapper.orderByAsc(Order::getCreateTime);
                    }
                    break;
                case "totalAmount":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getTotalAmount);
                    } else {
                        wrapper.orderByAsc(Order::getTotalAmount);
                    }
                    break;
                case "expireTime":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getExpireTime);
                    } else {
                        wrapper.orderByAsc(Order::getExpireTime);
                    }
                    break;
                default:
                    // 不支持的排序字段，不添加排序条件，使用数据库默认顺序
                    break;
            }
        } else {
            // 默认按过期时间升序排列
            wrapper.orderByAsc(Order::getExpireTime);
        }
        
        // 执行分页查询
        Page<Order> result = orderMapper.selectPage(page, wrapper);
        
        // 转换结果为VO对象
        List<OrderVO> voList = orderConverter.toVOList(result.getRecords());
        
        // 封装分页结果
        PageBean<OrderVO> pageBean = new PageBean<>();
        pageBean.setTotal(result.getTotal());
        pageBean.setList(voList);
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        
        return pageBean;
    }
}