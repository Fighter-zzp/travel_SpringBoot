package com.zzp.travel.stage.role.user.service;

import com.zzp.travel.stage.role.user.domain.User;

import java.util.List;

/**
 * 用户服务接口
 * <p>
 *  //TODO
 *  UserService.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/20 17:00
 * @see  UserService
 **/
public interface UserService {
    /**
     * 数据条数
     * @return
     * @throws Exception
     */
    long count()throws Exception;

    /**
     * 查询用户
     * @param id
     * @return
     * @throws Exception
     */
    User findById(String id)throws Exception;

    /**
     * 查询用户
     * @param userName
     * @return
     * @throws Exception
     */
    User findByUserName(String userName)throws Exception;

    /**
     * 查询用户
     * @return
     * @throws Exception
     */
    List<User> findList()throws Exception;

    /**
     * 保存用户
     * @param user
     * @throws Exception
     */
    void save(User user)throws Exception;

    /**
     * 更新用户
     * @param user
     * @throws Exception
     */
    void update(User user)throws Exception;

    /**
     * 删除用户
     * @param id
     * @throws Exception
     */
    void deleteByid(String id)throws Exception;

    /**
     * 查询用户
     * @param currentPage
     * @param pageSize
     * @param query
     * @return
     * @throws Exception
     */
    List<User> findByPage(int currentPage, int pageSize, String query)throws Exception;

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     * @throws Exception
     */
    User login(String userName, String password)throws Exception;

    /**
     * 查询state1
     * @return
     */
    long state1count();

    /**
     * 查询state2
     * @return
     */
    long state2count();
}
