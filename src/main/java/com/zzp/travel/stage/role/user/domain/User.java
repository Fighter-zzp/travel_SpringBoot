package com.zzp.travel.stage.role.user.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.persistence.Table;
/**
 * 用户类
 * <p>
 *  //TODO
 *  User.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/20 16:59
 * @see  User
 **/

@Table(name = "t_pz_user")
@Data
public class User implements Serializable {
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
     * 身份证
     */
    private String icCode;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 省份
     */
    private Integer province;

    private static final long serialVersionUID = 1L;
}