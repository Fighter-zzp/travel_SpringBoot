package com.zzp.travel.stage.cms.insurance.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzp.travel.stage.cms.insurance.domain.Insurance;
import com.zzp.travel.stage.cms.insurance.mapper.InsuranceMapper;
import com.zzp.travel.stage.cms.insurance.service.InsuranceService;
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
 * InsuranceServiceImpl.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v1.0.0
 * @date 2020/7/22 16:13
 * @see InsuranceServiceImpl
 **/
@Slf4j
@Service
public class InsuranceServiceImpl implements InsuranceService {

    @Resource
    private InsuranceMapper insuranceMapper;

    @Override
    public long count() {
        return insuranceMapper.selectCountByExample(whereArgs("deleteStatus", "0"));
    }

    @Override
    public Insurance findById(String id) {
        return insuranceMapper.selectOneByExample(whereArgs("deleteStatus", "0", "id", id));
    }

    @Override
    public List<Insurance> findList() {
        var example = whereArgs("deleteStatus", "0");
        example.orderBy("addTime").desc();
        return insuranceMapper.selectByExample(example);
    }

    @Override
    public void save(Insurance insurance) {
        insurance.setAddTime(new Date());
        insuranceMapper.insertSelective(insurance);
    }

    @Override
    public void update(Insurance insurance) {
        insurance.setModifyTime(new Date());
        var id = insurance.getId();
        insurance.setId(null);
        insuranceMapper.updateByExampleSelective(insurance, whereArgs("deleteStatus", "0", "id", id));
    }

    @Override
    public void deleteById(String id) {
        var strategy = new Insurance();
        strategy.setId(id);
        strategy.setDeleteStatus(0);
        insuranceMapper.updateByPrimaryKeySelective(strategy);
    }

    @Override
    public List<Insurance> findByPage(int currentPage, int pageSize, String query) {
        List<Insurance> list;
        PageHelper.startPage(currentPage, pageSize);
        if (Tools.notEmpty(query)) {
            list = insuranceMapper.findByQuery("%" + query + "%");
        } else {
            list = findList();
        }
        var pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public List<Insurance> findByPage(int currentPage, int pageSize) {
        List<Insurance> list;
        PageHelper.startPage(currentPage, pageSize);
        list = insuranceMapper.indexList();
        var pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public long state0count() throws Exception {
        return insuranceMapper.state0count();
    }

    @Override
    public long state1count() throws Exception {
        return insuranceMapper.state1count();
    }

    @Override
    public long state2count() throws Exception {
        return insuranceMapper.state2count();
    }

    @Override
    public long company0count() throws Exception {
        return insuranceMapper.company0count();
    }

    @Override
    public long company1count() throws Exception {
        return insuranceMapper.company1count();
    }

    /**
     * 再加工
     *
     * @param args 偶数数目的参数
     * @return Example
     */
    private Example whereArgs(String... args) {
        return MapperUtils.whereArgs(Insurance.class, args);
    }
}
