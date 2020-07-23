package com.zzp.travel.stage.role.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzp.travel.stage.role.admin.domain.Admin;
import com.zzp.travel.stage.role.admin.mapper.AdminMapper;
import com.zzp.travel.stage.role.admin.service.AdminService;
import com.zzp.travel.stage.utils.Tools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 管理员服务实现类
 * <p>
 *  //TODO
 *  AdminServiceImpl.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/19 22:09
 * @see  AdminServiceImpl
 **/
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public long count() throws Exception {
        var example = new Example(Admin.class);
        example.createCriteria()
                .andEqualTo("deleteStatus",0);
        return adminMapper.selectCountByExample(example);
    }

    @Override
    public Admin login(String userName, String password) throws Exception {
        var example = new Example(Admin.class);
        example.createCriteria()
                .andEqualTo("deleteStatus",0)
                .andEqualTo("userName",userName)
                .andEqualTo("password",password);
        return adminMapper.selectOneByExample(example);
    }

    @Override
    public Admin findById(String id) throws Exception {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Admin> findList() throws Exception {
        var example = new Example(Admin.class);
        example.createCriteria()
                .andEqualTo("deleteStatus",0);
        example.orderBy("addTime").desc();
        return adminMapper.selectByExample(example);
    }

    @Override
    public void save(Admin admin) throws Exception {
        admin.setAddTime(new Date());
        adminMapper.insertSelective(admin);
    }

    @Override
    public void update(Admin admin) throws Exception {
        admin.setModifyTime(new Date());
        adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public void deleteById(String id) throws Exception {
        var admin = new Admin();
        admin.setId(id);
        admin.setDeleteStatus(1);
        adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public List<Admin> findByPage(int currentPage, int pageSize, String query) throws Exception {
        List<Admin> list;
        PageHelper.startPage(currentPage, pageSize);
        if (Tools.notEmpty(query)) {
            list = adminMapper.findListByQuery("%" + query + "%");
        } else {
            list = findList();
        }
        var pageInfo= new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public Admin findByUserName(String userName) {
        var example = new Example(Admin.class);
        example.createCriteria()
                .andEqualTo("deleteStatus",0)
                .andEqualTo("userName",userName);
        return adminMapper.selectOneByExample(example);
    }
}
