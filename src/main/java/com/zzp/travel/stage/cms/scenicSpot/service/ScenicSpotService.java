package com.zzp.travel.stage.cms.scenicSpot.service;

import com.zzp.travel.stage.cms.scenicSpot.domain.ScenicSpot;

import java.util.List;

/**
 * <标题>
 * <p>
 *  //TODO
 *  sss.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/20 23:00
 * @see  ScenicSpotService
 **/

public interface ScenicSpotService {

    public long count()throws Exception;

    public long count2()throws Exception;

    public ScenicSpot findById(String id);

    public List<ScenicSpot> findList()throws Exception;

    public void save(ScenicSpot scenicSpot);

    public void update(ScenicSpot scenicSpot);

    public void deleteByid(String id);

    public List<ScenicSpot> findByPage(int currentPage, int pageSize, String query)throws Exception;

    public List<ScenicSpot> findByPage(int currentPage, int pageSize)throws Exception;

    long state0count() throws Exception;

    long state1count() throws Exception;

    long state2count() throws Exception;
}
