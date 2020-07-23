package com.zzp.travel.stage.cms.insurance.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.persistence.Table;
/**
 * 保险
 * <p>
 *  //TODO
 *  Insurance.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/22 18:02
 * @see  Insurance
 **/
@Data
@Table(name = "t_cms_insurance")
public class Insurance implements Serializable {
    private String id;

    private String addUserId;

    private Date addTime;

    private Integer deleteStatus;

    private String modifyUserId;

    private Date modifyTime;

    /**
    * 保险公司
    */
    private String title;

    private Integer insuranceCompany;

    private Double price;

    private Integer type;

    private String resume;

    private Integer state;

    private String imgUrl;

    private static final long serialVersionUID = 1L;
}