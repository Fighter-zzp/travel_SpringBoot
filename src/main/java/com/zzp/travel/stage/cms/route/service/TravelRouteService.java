package com.zzp.travel.stage.cms.route.service;

import com.zzp.travel.stage.cms.route.domain.TravelRoute;

import java.util.List;

/**
 * 路线服务
 * <p>
 * //TODO
 * TravelRouteService.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v1.0.0
 * @date 2020/7/20 22:37
 * @see TravelRouteService
 **/
public interface TravelRouteService {
    /**
     * 统计路线
     *
     * @return
     * @throws Exception
     */
    long count() throws Exception;

    /**
     * 统计路线
     *
     * @return
     * @throws Exception
     */
    long count2() throws Exception;

    /**
     * 查询路线
     *
     * @param id
     * @return
     * @throws Exception
     */
    TravelRoute findById(String id) throws Exception;

    /**
     * 路线集
     *
     * @return
     * @throws Exception
     */
    List<TravelRoute> findList() throws Exception;

    /**
     * 保存路线
     *
     * @param travelRoute
     * @throws Exception
     */
    void save(TravelRoute travelRoute) throws Exception;

    /**
     * 更新路线
     *
     * @param travelRoute
     * @throws Exception
     */
    void update(TravelRoute travelRoute) throws Exception;

    /**
     * 删除路线
     *
     * @param id
     * @throws Exception
     */
    void deleteById(String id) throws Exception;

    /**
     * 查询路线
     *
     * @param currentPage
     * @param pageSize
     * @param query
     * @return
     * @throws Exception
     */
    List<TravelRoute> findByPage(int currentPage, int pageSize, String query) throws Exception;

    /**
     * 查询路线
     *
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    List<TravelRoute> findByPage(int currentPage, int pageSize) throws Exception;

    long state0count() throws Exception;

    long state1count() throws Exception;

    long state2count() throws Exception;
}

