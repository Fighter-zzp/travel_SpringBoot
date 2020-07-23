package com.zzp.travel.stage.role.admin.service;

import com.zzp.travel.stage.role.admin.domain.Admin;

import java.util.List;

/**
 * 管理员服务接口
 * <p>
 * //TODO
 * AdminService.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v1.0.0
 * @date 2020/7/19 22:01
 * @see AdminService
 **/
public interface AdminService {
    /**
     * 统计用户量
     * @return
     * @throws Exception
     */
    long count() throws Exception;

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     * @throws Exception
     */
    Admin login(String userName, String password) throws Exception;

    /**
     * 通过id查admin
     * @param id
     * @return
     * @throws Exception
     */
    Admin findById(String id) throws Exception;

    /**
     * 查找所有管理员
     * @return
     * @throws Exception
     */
    List<Admin> findList() throws Exception;

    /**
     * 保存管理员信息
     * @param admin
     * @throws Exception
     */
    void save(Admin admin) throws Exception;

    /**
     * 更新管理员信息
     * @param admin
     * @throws Exception
     */
    void update(Admin admin) throws Exception;

    /**
     * 通过id删除
     * @param id
     * @throws Exception
     */
    void deleteById(String id) throws Exception;

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param query
     * @return
     * @throws Exception
     */
    List<Admin> findByPage(int currentPage, int pageSize, String query) throws Exception;

    Admin findByUserName(String userName);
}

