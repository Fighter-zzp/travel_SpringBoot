package com.zzp.travel.stage.cms.strategy.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.persistence.Table;

/**
 * 攻略
 * <p>
 *  //TODO
 *  Strategy.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/22 16:09
 * @see  Strategy
 **/
@Data
@Table(name = "t_cms_strategy")
public class Strategy implements Serializable {
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
    * logo图片地址
    */
    private String imgUrl;

    /**
    * 主题
    */
    private String title;

    /**
    * 等级
    */
    private Integer rating;

    /**
    * 简介
    */
    private String summary;

    /**
    * 内容图片地址
    */
    private String introUrl;

    private Integer state;

    private static final long serialVersionUID = 1L;
}