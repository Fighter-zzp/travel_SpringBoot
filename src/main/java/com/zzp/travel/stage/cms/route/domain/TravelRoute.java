package com.zzp.travel.stage.cms.route.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.persistence.Table;
/**
 * 旅游路线类
 * <p>
 *  //TODO
 *  TravelRoute.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/20 22:31
 * @see  TravelRoute
 **/
@Table(name = "t_cms_travel_route")
@Data
public class TravelRoute implements Serializable {
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
    * 标题
    */
    private String title;

    /**
    * 出发地点
    */
    private String startSite;

    /**
    * 结束地点
    */
    private String endSite;

    /**
    * 结束日期
    */
    private String endTime;

    /**
    * 出团日期
    */
    private String startTime;

    /**
    * 持续天数
    */
    private Integer day;

    /**
    * 产品编号
    */
    private String productCode;

    /**
    * 价格
    */
    private Double price;

    /**
    * 状态
    */
    private Integer state;

    private String imgUrl;

    private String intro;

    private static final long serialVersionUID = 1L;
}