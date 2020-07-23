package com.zzp.travel.stage.cms.hotel.mapper;

import com.zzp.travel.stage.cms.hotel.domain.Hotel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.CommonsMapper;

import java.util.List;

/**
 * <标题>
 * <p>
 *  //TODO
 *  HotelMapper.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/22 0:43
 * @see  HotelMapper
 **/
@Mapper
public interface HotelMapper extends CommonsMapper<Hotel> {
    /**
     * 通过query查询
     * @param query
     * @return
     */
    @Select("SELECT * FROM t_cms_hotel WHERE DELETE_STATUS=0 AND HOTEL_NAME LIKE #{query,jdbcType=VARCHAR} ORDER BY ADD_TIME DESC")
    @ResultMap("BaseResultMap")
    List<Hotel> findByQuery(String query);

    /**
     * 查询
     * @return
     */
    @Select("SELECT * FROM t_cms_hotel WHERE DELETE_STATUS=0 AND STATE=1 ORDER BY ADD_TIME DESC")
    @ResultMap("BaseResultMap")
    List<Hotel> indexList();


    @Select("SELECT count(*) FROM t_cms_hotel WHERE DELETE_STATUS=0 ")
     long count2();
    @Select("SELECT count(*) FROM t_cms_hotel WHERE DELETE_STATUS=0 AND STATE=0")
    long state0count();
    @Select("SELECT count(*) FROM t_cms_hotel WHERE DELETE_STATUS=0 AND STATE=1")
    long state1count();
    @Select("SELECT count(*) FROM t_cms_hotel WHERE DELETE_STATUS=0 AND STATE=2")
    long state2count();
}