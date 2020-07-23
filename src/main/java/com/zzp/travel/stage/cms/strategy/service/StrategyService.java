package com.zzp.travel.stage.cms.strategy.service;

import com.zzp.travel.stage.cms.strategy.domain.Strategy;

import java.util.List;

/**
 * 攻略服务
 * <p>
 *  //TODO
 *  StrategyService.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/22 16:10
 * @see  StrategyService
 **/
public interface StrategyService {
    /**
     * 统计
     * @return
     */
    long count();

    /**
     * 根据id查询攻略
     * @param id
     * @return
     */
     Strategy findById(String id);

    /**
     * 攻略列表
     * @return
     */
     List<Strategy> findList();

    /**
     * 保存
     * @param strategy
     */
     void save(Strategy strategy);

    /**
     * 更新
     * @param strategy
     */
     void update(Strategy strategy);

    /**
     * 删除
     * @param id
     */
    void deleteById(String id);

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param query
     * @return
     */
    List<Strategy> findByPage(int currentPage, int pageSize, String query);

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<Strategy> findByPage(int currentPage, int pageSize);

    long state0count() throws Exception;

    long state1count() throws Exception;

    long state2count() throws Exception;
}
