package com.zzp.travel.stage.role.admin.domain;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 管理员类
 * <p>
 *  //TODO
 *  Admin.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/19 22:05
 * @see  Admin
 **/

@SuppressWarnings("serial")
@Data
@Table(name = "t_pz_admin_user")
public class Admin implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     * 添加人ID
     */
    private String addUserId;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 删除标志
     */
    private Integer deleteStatus;

    /**
     * 修改人ID
     */
    private String modifyUserId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String linkTel;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 状态
     */
    private Integer state;

    private static final long serialVersionUID = 1L;
}