package com.zzp.travel.stage.cms.car.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzp.travel.stage.cms.car.domain.Car;
import com.zzp.travel.stage.cms.car.mapper.CarMapper;
import com.zzp.travel.stage.cms.car.service.CarService;
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
 *  //TODO
 *  StrategyServiceImpl.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/22 16:13
 * @see  CarServiceImpl
 **/
@Slf4j
@Service
public class CarServiceImpl implements CarService {

    @Resource
    private CarMapper carMapper;

    @Override
    public long count() {
        return carMapper.selectCountByExample(whereArgs("deleteStatus","0"));
    }

    @Override
    public Car findById(String id) {
        return carMapper.selectOneByExample(whereArgs("deleteStatus","0","id",id));
    }

    @Override
    public List<Car> findList() {
        var example = whereArgs("deleteStatus", "0");
        example.orderBy("addTime").desc();
        return carMapper.selectByExample(example);
    }

    @Override
    public void save(Car car) {
        car.setAddTime(new Date());
        carMapper.insertSelective(car);
    }

    @Override
    public void update(Car car) {
        car.setModifyTime(new Date());
        var id = car.getId();
        car.setId(null);
        carMapper.updateByExampleSelective(car,whereArgs("deleteStatus", "0","id",id));
    }

    @Override
    public void deleteById(String id) {
        var car = new Car();
        car.setId(id);
        car.setDeleteStatus(0);
        carMapper.updateByPrimaryKeySelective(car);
    }

    @Override
    public List<Car> findByPage(int currentPage, int pageSize, String query) {
        List<Car> list;
        PageHelper.startPage(currentPage, pageSize);
        if (Tools.notEmpty(query)) {
            list = carMapper.findByQuery("%" + query + "%");
        } else {
            list = findList();
        }
        var pageInfo=new PageInfo<Car>(list);
        return pageInfo.getList();
    }

    @Override
    public List<Car> findByPage(int currentPage, int pageSize) {
        List<Car> list;
        PageHelper.startPage(currentPage, pageSize);
        list = carMapper.indexList();
        var pageInfo=new PageInfo<Car>(list);
        return pageInfo.getList();
    }

    @Override
    public long state0count()throws Exception{
        return  carMapper.state0count();
    }
    @Override
    public long state1count()throws Exception{
        return  carMapper.state1count();
    }
    @Override
    public long state2count()throws Exception{
        return  carMapper.state2count();
    }
    @Override
    public long type0count()throws Exception{
        return  carMapper.type0count();
    }
    @Override
    public long type1count()throws Exception{
        return  carMapper.type1count();
    }
    @Override
    public long type2count()throws Exception{
        return  carMapper.type2count();
    }

    /**
     * 再加工
     * @param args 偶数数目的参数
     * @return Example
     */
    private Example whereArgs(String... args) {
        return MapperUtils.whereArgs(Car.class,args);
    }
}
