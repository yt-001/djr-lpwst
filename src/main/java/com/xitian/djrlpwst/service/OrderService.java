package com.xitian.djrlpwst.service;

import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.base.service.BaseService;
import com.xitian.djrlpwst.domain.entity.Order;
import com.xitian.djrlpwst.domain.query.OrderQuery;
import com.xitian.djrlpwst.domain.vo.OrderAdminVO;
import com.xitian.djrlpwst.domain.vo.OrderVO;

import java.time.LocalDateTime;

/**
 * 订单业务逻辑层接口
 */
public interface OrderService extends BaseService<Order> {
    
    /**
     * 生成订单编号
     * @return 订单编号
     */
    String generateOrderNo();

    /**
     * 获取分页数据
     * @param param 分页参数
     * @return 分页数据
     */
    PageBean<OrderVO> getPage(PageParam<OrderQuery> param);

    /**
     * 获取管理员分页数据
     * @param param 分页参数
     * @return 分页数据
     */
    PageBean<OrderAdminVO> getAdminPage(PageParam<OrderQuery> param);
    
    /**
     * 获取已支付但已过期的订单分页数据
     * @param param 分页参数
     * @return 分页数据
     */
    PageBean<OrderVO> getExpiredPaidOrders(PageParam<OrderQuery> param);
    
    /**
     * 获取待使用且未过期的订单分页数据
     * @param param 分页参数
     * @return 分页数据
     */
    PageBean<OrderVO> getPendingValidOrders(PageParam<OrderQuery> param);
}