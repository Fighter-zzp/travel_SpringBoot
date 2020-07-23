package com.zzp.travel.stage.cms.insurance.service;


import com.zzp.travel.stage.cms.insurance.domain.Insurance;

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
 * @see  InsuranceService
 **/
public interface InsuranceService {
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
     Insurance findById(String id);

    /**
     * 攻略列表
     * @return
     */
     List<Insurance> findList();

    /**
     * 保存
     * @param insurance
     */
     void save(Insurance insurance);

    /**
     * 更新
     * @param insurance
     */
     void update(Insurance insurance);

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
    List<Insurance> findByPage(int currentPage, int pageSize, String query);

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<Insurance> findByPage(int currentPage, int pageSize);

    long state0count() throws Exception;

    long state1count() throws Exception;

    long state2count() throws Exception;

    long company0count()throws Exception;

    long company1count()throws Exception;

}
