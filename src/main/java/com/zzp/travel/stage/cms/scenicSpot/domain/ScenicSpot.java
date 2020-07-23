package com.zzp.travel.stage.cms.scenicSpot.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.persistence.Table;
/**
 * <标题>
 * <p>
 *  //TODO
 *  ScenicSpot.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/20 23:06
 * @see  ScenicSpot
 **/
@Table(name = "t_cms_scenic_spot")
@Data
public class ScenicSpot implements Serializable {
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
    * 景点名称
    */
    private String spotName;

    /**
    * 景点简介
    */
    private String spotIntro;

    /**
    * 景点星级
    */
    private Integer spotStar;

    /**
    * 景点地址
    */
    private String spotAddress;

    /**
    * 开放时间
    */
    private String openTime;

    /**
    * 门票
    */
    private Double ticketsMessage;

    /**
    * 状态
    */
    private Integer state;

    private String imgUrl;

    private static final long serialVersionUID = 1L;
}