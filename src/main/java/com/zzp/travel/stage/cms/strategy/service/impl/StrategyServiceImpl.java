package com.zzp.travel.stage.cms.strategy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzp.travel.stage.cms.hotel.domain.Hotel;
import com.zzp.travel.stage.cms.strategy.domain.Strategy;
import com.zzp.travel.stage.cms.strategy.mapper.StrategyMapper;
import com.zzp.travel.stage.cms.strategy.service.StrategyService;
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
 * @see  StrategyServiceImpl
 **/
@Slf4j
@Service
public class StrategyServiceImpl implements StrategyService {

    @Resource
    private StrategyMapper strategyMapper;

    @Override
    public long count() {
        return strategyMapper.selectCountByExample(whereArgs("deleteStatus","0"));
    }

    @Override
    public Strategy findById(String id) {
        return strategyMapper.selectOneByExample(whereArgs("deleteStatus","0","id",id));
    }

    @Override
    public List<Strategy> findList() {
        var example = whereArgs("deleteStatus", "0");
        example.orderBy("addTime").desc();
        return strategyMapper.selectByExample(example);
    }

    @Override
    public void save(Strategy strategy) {
        strategy.setAddTime(new Date());
        strategyMapper.insertSelective(strategy);
    }

    @Override
    public void update(Strategy strategy) {
        strategy.setModifyTime(new Date());
        var id = strategy.getId();
        strategy.setId(null);
        strategyMapper.updateByExampleSelective(strategy,whereArgs("deleteStatus", "0","id",id));
    }

    @Override
    public void deleteById(String id) {
        var strategy = new Strategy();
        strategy.setId(id);
        strategy.setDeleteStatus(0);
        strategyMapper.updateByPrimaryKeySelective(strategy);
    }

    @Override
    public List<Strategy> findByPage(int currentPage, int pageSize, String query) {
        List<Strategy> list;
        PageHelper.startPage(currentPage, pageSize);
        if (Tools.notEmpty(query)) {
            list = strategyMapper.findByQuery("%" + query + "%");
        } else {
            list = findList();
        }
        var pageInfo=new PageInfo<Strategy>(list);
        return pageInfo.getList();
    }

    @Override
    public List<Strategy> findByPage(int currentPage, int pageSize) {
        List<Strategy> list;
        PageHelper.startPage(currentPage, pageSize);
        list = strategyMapper.indexList();
        var pageInfo=new PageInfo<Strategy>(list);
        return pageInfo.getList();
    }

    @Override
    public long state0count()throws Exception{
        return  strategyMapper.state0count();
    }
    @Override
    public long state1count()throws Exception{
        return  strategyMapper.state1count();
    }
    @Override
    public long state2count()throws Exception{
        return  strategyMapper.state2count();
    }

    /**
     * 再加工
     * @param args 偶数数目的参数
     * @return Example
     */
    private Example whereArgs(String... args) {
        return MapperUtils.whereArgs(Strategy.class,args);
    }
}
