package com.zzp.travel.stage.cms.message.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.persistence.Table;

/**
 * 留言
 * <p>
 *  //TODO
 *  Message.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/22 15:29
 * @see  Message
 **/
@Data
@Table(name = "t_cms_message")
public class Message implements Serializable {
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
    * 用户ID
    */
    private String userId;

    /**
    * 用户名
    */
    private String userName;

    /**
    * 真实姓名
    */
    private String name;

    /**
    * 标题
    */
    private String title;

    /**
    * 内容
    */
    private String content;

    /**
    * 状态
    */
    private Integer state;

    private static final long serialVersionUID = 1L;
}