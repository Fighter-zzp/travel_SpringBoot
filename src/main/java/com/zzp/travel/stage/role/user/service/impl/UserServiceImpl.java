package com.zzp.travel.stage.role.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzp.travel.stage.role.user.domain.User;
import com.zzp.travel.stage.role.user.mapper.UserMapper;
import com.zzp.travel.stage.role.user.service.UserService;
import com.zzp.travel.stage.utils.Tools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
 * <标题>
 * <p>
 *  //TODO
 *  UserServiceImpl.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/20 17:03
 * @see  UserServiceImpl
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public long count() throws Exception {
        var example = new Example(User.class);
        example.createCriteria().andEqualTo("deleteStatus",0);
        return userMapper.selectCountByExample(example);
    }

    @Override
    public User findById(String id) throws Exception {
        var example = new Example(User.class);
        example.createCriteria()
                .andEqualTo("deleteStatus", 0)
                .andEqualTo("id", id);
        return userMapper.selectOneByExample(example);
    }

    @Override
    public User findByUserName(String userName) throws Exception {
        var example = new Example(User.class);
        example.createCriteria()
                .andEqualTo("deleteStatus", 0)
                .andEqualTo("userName", userName);
        return userMapper.selectOneByExample(example);
    }

    @Override
    public List<User> findList() throws Exception {
        var example = new Example(User.class);
        example.createCriteria().andEqualTo("deleteStatus", 0);
        example.orderBy("addTime").desc();
        return userMapper.selectByExample(example);
    }

    @Override
    public void save(User user) throws Exception {
        user.setAddTime(new Date());
        userMapper.insertSelective(user);
    }

    @Override
    public void update(User user) throws Exception {
        user.setModifyTime(new Date());
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void deleteByid(String id) throws Exception {
        var user = new User();
        user.setId(id);
        user.setDeleteStatus(1);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<User> findByPage(int currentPage, int pageSize, String query) throws Exception {
        List<User> list ;
        PageHelper.startPage(currentPage, pageSize);
        if (Tools.notEmpty(query)) {
            list = userMapper.findListByQuery("%" + query + "%");
        } else {
            list = findList();
        }
        var pageInfo=new PageInfo<User>(list);
        return pageInfo.getList();
    }

    @Override
    public User login(String userName, String password) throws Exception {
        var example = new Example(User.class);
        example.createCriteria()
                .andEqualTo("userName",userName)
                .andEqualTo("password",password);
        return userMapper.selectOneByExample(example);
    }

    @Override
    public long state1count() {
        var example = new Example(User.class);
        example.createCriteria()
                .andEqualTo("deleteStatus",0)
                .andEqualTo("state",1);
        return userMapper.selectCountByExample(example);
    }

    @Override
    public long state2count() {
        var example = new Example(User.class);
        example.createCriteria()
                .andEqualTo("deleteStatus",0)
                .andEqualTo("state",2);
        return userMapper.selectCountByExample(example);
    }
}
