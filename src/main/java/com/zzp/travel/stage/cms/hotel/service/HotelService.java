package com.zzp.travel.stage.cms.hotel.service;

import com.zzp.travel.stage.cms.hotel.domain.Hotel;

import java.util.List;

/**
 * 酒店服务
 * <p>
 * //TODO
 * HotelService.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v1.0.0
 * @date 2020/7/22 0:46
 * @see HotelService
 **/
public interface HotelService {
    /**
     * 计数
     * @return
     * @throws Exception
     */
    long count();

    /**
     * 查询酒店
     * @param id
     * @return
     * @throws Exception
     */
    Hotel findById(String id);

    /**
     * 查询酒店
     * @return
     * @throws Exception
     */
    List<Hotel> findList();

    /**
     * 保存信息
     * @param article
     * @throws Exception
     */
    void save(Hotel article);

    /**
     * 更新信息
     * @param article
     * @throws Exception
     */
    void update(Hotel article);

    /**+
     * 删除
     * @param id
     * @throws Exception
     */
    void deleteById(String id);

    /**
     * 查询酒店
     * @param currentPage
     * @param pageSize
     * @param query
     * @return
     * @throws Exception
     */
    List<Hotel> findByPage(int currentPage, int pageSize, String query);

    /**
     * 查询酒店
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    List<Hotel> findByPage(int currentPage, int pageSize);

    long state0count() throws Exception;

    long state1count() throws Exception;

    long state2count() throws Exception;

}
