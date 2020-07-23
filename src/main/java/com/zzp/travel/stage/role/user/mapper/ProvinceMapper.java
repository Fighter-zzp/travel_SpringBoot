package com.zzp.travel.stage.role.user.mapper;

import com.zzp.travel.stage.role.user.domain.Province;
import com.zzp.travel.stage.role.user.vo.ProvinceVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.CommonsMapper;

import java.util.List;
@Mapper
public interface ProvinceMapper extends CommonsMapper<Province> {
    @Select("SELECT p.`id` PROVICE,COUNT(u.`PROVINCE`) COUNT FROM t_pz_province p LEFT JOIN t_pz_user u ON u.`PROVINCE` = p.`id` GROUP BY p.id ;")
    List<ProvinceVO> countProvince();
}