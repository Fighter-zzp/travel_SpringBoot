package com.zzp.travel.stage.cms.hotel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzp.travel.stage.cms.hotel.domain.Hotel;
import com.zzp.travel.stage.cms.hotel.mapper.HotelMapper;
import com.zzp.travel.stage.cms.hotel.service.HotelService;
import com.zzp.travel.stage.utils.Tools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.MapperUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <标题>
 * <p>
 * //TODO
 * HotelServiceImpl.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v1.0.0
 * @date 2020/7/22 0:49
 * @see HotelServiceImpl
 **/
@Slf4j
@Service
public class HotelServiceImpl implements HotelService {


    @Resource
    private HotelMapper hotelMapper;

    @Override
    public long count(){
        return hotelMapper.selectCountByExample(whereArgs("deleteStatus","0"));
    }

    @Override
    public Hotel findById(String id){
        return hotelMapper.selectOneByExample(whereArgs("deleteStatus","0","id",id));
    }

    @Override
    public List<Hotel> findList(){
        var example = whereArgs("deleteStatus", "0");
        example.orderBy("addTime").desc();
        return hotelMapper.selectByExample(example);
    }

    @Override
    public void save(Hotel article){
        article.setAddTime(new Date());
        hotelMapper.insertSelective(article);
    }

    @Override
    public void update(Hotel article){
        article.setModifyTime(new Date());
        var id = article.getId();
        article.setId(null);
        hotelMapper.updateByExampleSelective(article,whereArgs("deleteStatus", "0","id",id));
    }

    @Override
    public void deleteById(String id){
        var hotel = new Hotel();
        hotel.setId(id);
        hotel.setDeleteStatus(0);
        hotelMapper.updateByPrimaryKeySelective(hotel);
    }

    @Override
    public List<Hotel> findByPage(int currentPage, int pageSize, String query){
        List<Hotel> list;
        PageHelper.startPage(currentPage, pageSize);
        if (Tools.notEmpty(query)) {
            list = hotelMapper.findByQuery("%" + query + "%");
        } else {
            list = findList();
        }
        var pageInfo=new PageInfo<Hotel>(list);
        return pageInfo.getList();
    }

    @Override
    public List<Hotel> findByPage(int currentPage, int pageSize){
        List<Hotel> list ;
        PageHelper.startPage(currentPage, pageSize);
        list = hotelMapper.indexList();
        var pageInfo=new PageInfo<Hotel>(list);
        return pageInfo.getList();
    }

    @Override
    public long state0count() throws Exception {
        return hotelMapper.state0count();
    }

    @Override
    public long state1count() throws Exception {
        return hotelMapper.state1count();
    }

    @Override
    public long state2count() throws Exception {
        return hotelMapper.state2count();
    }


    /**
     * 再加工
     * @param args 偶数数目的参数
     * @return Example
     */
    private Example whereArgs(String... args) {
        return MapperUtils.whereArgs(Hotel.class,args);
    }
}
