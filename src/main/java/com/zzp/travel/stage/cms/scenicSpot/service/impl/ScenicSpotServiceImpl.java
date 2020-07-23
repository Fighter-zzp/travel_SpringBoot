package com.zzp.travel.stage.cms.scenicSpot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzp.travel.stage.cms.scenicSpot.domain.ScenicSpot;

import com.zzp.travel.stage.cms.scenicSpot.mapper.ScenicSpotMapper;
import com.zzp.travel.stage.cms.scenicSpot.service.ScenicSpotService;
import com.zzp.travel.stage.utils.Tools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <标题>
 * <p>
 * //TODO
 * ScenicSpotServiceImpl.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v1.0.0
 * @date 2020/7/20 23:01
 * @see ScenicSpotServiceImpl
 **/
@Service
@Slf4j
public class ScenicSpotServiceImpl implements ScenicSpotService {

    @Resource
    private ScenicSpotMapper scenicSpotMapper;

    @Override
    public long count() throws Exception {
        return scenicSpotMapper.count();
    }

    @Override
    public long count2() throws Exception {
        return scenicSpotMapper.count2();
    }

    @Override
    public ScenicSpot findById(String id)  {
        return scenicSpotMapper.findById(id);
    }

    @Override
    public List<ScenicSpot> findList() throws Exception {
        return scenicSpotMapper.findList();
    }

    @Override
    public void save(ScenicSpot scenicSpot){
        scenicSpotMapper.save(scenicSpot);
    }

    @Override
    public void update(ScenicSpot scenicSpot)   {
        scenicSpotMapper.update(scenicSpot);
    }

    @Override
    public void deleteByid(String id)   {
        scenicSpotMapper.deleteByid(id);
    }

    @Override
    public List<ScenicSpot> findByPage(int currentPage, int pageSize, String query) {
        List<ScenicSpot> list;
        PageHelper.startPage(currentPage, pageSize);
        if (Tools.notEmpty(query)) {
            list = scenicSpotMapper.findListByQuery("%" + query + "%");
        } else {
            list = scenicSpotMapper.findList();
        }
        var pageInfo=new PageInfo<ScenicSpot>(list);
        return pageInfo.getList();
    }

    @Override
    public List<ScenicSpot> findByPage(int currentPage, int pageSize) {
        List<ScenicSpot> list ;
        PageHelper.startPage(currentPage, pageSize);
        list = scenicSpotMapper.indexList();
        var pageInfo=new PageInfo<ScenicSpot>(list);
        return pageInfo.getList();
    }

    @Override
    public long state0count()throws Exception{
        return scenicSpotMapper.state0count();
    }
    @Override
    public long state1count()throws Exception{
        return scenicSpotMapper.state1count();
    }
    @Override
    public long state2count()throws Exception{
        return scenicSpotMapper.state2count();
    }

}
