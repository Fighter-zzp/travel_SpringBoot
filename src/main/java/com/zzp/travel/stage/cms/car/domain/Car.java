package com.zzp.travel.stage.cms.car.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.persistence.Table;

/**
 * 车票
 * <p>
 *  //TODO
 *  Car.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/22 18:01
 * @see  Car
 **/
@Data
@Table(name = "t_cms_car")
public class Car implements Serializable {
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
    * 车票标题
    */
    private String title;

    /**
    * 出发地点
    */
    private String startPlace;

    /**
    * 到达地点
    */
    private String endPlace;

    /**
    * 出发日期跟时间
    */
    private String startDateAndTime;

    /**
    * 需要时间
    */
    private Double needTime;

    /**
    * 上车集中地
    */
    private String gatherPlace;

    /**
    * 车的类型，0是飞机，1是火车，2是汽车
    */
    private Integer type;

    /**
    * 图片
    */
    private String imgUrl;

    private Integer state;

    /**
    * 备注
    */
    private String remark;

    private Double price;

    private static final long serialVersionUID = 1L;
}