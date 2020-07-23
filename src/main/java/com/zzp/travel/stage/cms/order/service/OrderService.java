package com.zzp.travel.stage.cms.order.service;

import com.zzp.travel.stage.cms.order.domain.Order;
import com.zzp.travel.stage.web.base.PageParam;

import java.util.List;

/**
 * 订单服务
 * <p>
 * //TODO
 * OrderService.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v1.0.0
 * @date 2020/7/22 17:13
 * @see OrderService
 **/
public interface OrderService {
    /**
     * 统计
     * @return
     */
    long count();

    /**
     * 查询
     * @param id
     * @return
     */
    Order findById(String id);

    /**
     * 查询
     * @return
     */
    List<Order> findList();

    /**
     * save
     * @param notice
     * @throws Exception
     */
    void save(Order notice) throws Exception;

    /**+
     * 更新
     * @param notice
     * @throws Exception
     */
    void update(Order notice) throws Exception;

    /**
     * 删除
     * @param id
     * @throws Exception
     */
    void deleteById(String id) throws Exception;

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param query
     * @return
     */
    List<Order> findByPage(int currentPage, int pageSize, String query);

    /**
     * 分页查询
     * @param userId
     * @return
     */
    List<Order> findListByUserId(String userId);

    /**
     * 统计用户订单
     * @param userId
     * @return
     */
    long countByUserId(String userId);

    /**
     * 分页查询用户订单
     * @param currentPage
     * @param pageSize
     * @param userId
     * @return
     */
    PageParam<Order> findByPageByUserId(int currentPage, int pageSize, String userId);

    long state0count() throws Exception;

    long state1count() throws Exception;

    long state2count() throws Exception;
}
