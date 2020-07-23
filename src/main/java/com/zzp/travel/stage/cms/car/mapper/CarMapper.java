package com.zzp.travel.stage.cms.car.mapper;

import com.zzp.travel.stage.cms.car.domain.Car;
import com.zzp.travel.stage.cms.strategy.domain.Strategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.CommonsMapper;

import java.util.List;

/**
 * <标题>
 * <p>
 *  //TODO
 *  CarMapper.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/22 16:30
 * @see  CarMapper
 **/
@Mapper
public interface CarMapper extends CommonsMapper<Car> {

    /**
     * 通过query查询
     * @param query
     * @return
     */
    @Select("SELECT * FROM t_cms_car WHERE DELETE_STATUS=0 AND TITLE LIKE #{query,jdbcType=VARCHAR} ORDER BY ADD_TIME DESC")
    @ResultMap("BaseResultMap")
    List<Car> findByQuery(String query);

    /**
     * 查询
     * @return
     */
    @Select("SELECT * FROM t_cms_car WHERE DELETE_STATUS=0 AND STATE=1 ORDER BY ADD_TIME DESC")
    @ResultMap("BaseResultMap")
    List<Car> indexList();



    @Select("SELECT count(*) FROM t_cms_car WHERE DELETE_STATUS=0 AND STATE=0")
    long state0count();
    @Select("SELECT count(*) FROM t_cms_car WHERE DELETE_STATUS=0 AND STATE=1")
    long state1count();
    @Select("SELECT count(*) FROM t_cms_car WHERE DELETE_STATUS=0 AND STATE=2")
    long state2count();

    @Select("SELECT count(*) FROM t_cms_car WHERE DELETE_STATUS=0 AND TYPE=0")
    long type0count();
    @Select("SELECT count(*) FROM t_cms_car WHERE DELETE_STATUS=0 AND TYPE=1")
    long type1count();
    @Select("SELECT count(*) FROM t_cms_car WHERE DELETE_STATUS=0 AND TYPE=2")
    long type2count();

}