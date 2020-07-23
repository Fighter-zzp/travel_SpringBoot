package com.zzp.travel.stage.cms.order.mapper;

import com.zzp.travel.stage.cms.order.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.CommonsMapper;

import java.util.List;

/**
 * <标题>
 * <p>
 *  //TODO
 *  MessageMapper.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/22 15:15
 * @see  OrderMapper
 **/
@Mapper
public interface OrderMapper extends CommonsMapper<Order> {
    /**
     * 通过query查询
     * @param query
     * @return
     */
    @Select("SELECT * FROM t_yw_order WHERE DELETE_STATUS=0 AND USER_NAME LIKE #{query,jdbcType=VARCHAR} ORDER BY ADD_TIME DESC")
    @ResultMap("BaseResultMap")
    List<Order> findByQuery(String query);

    /**
     * 查询
     * @param userId
     * @return
     */
    @Select("SELECT count(*) FROM t_yw_order WHERE USER_ID = #{userId} AND DELETE_STATUS=0")
    long countByUserId(@Param("userId")String userId);

    /**
     * 通过userId查询
     * @param userId
     * @return
     */
    @Select("SELECT * FROM t_yw_order WHERE USER_ID=#{userId} AND DELETE_STATUS=0 ORDER BY ADD_TIME DESC")
    @ResultMap("BaseResultMap")
    List<Order> findListByUserId(@Param("userId")String userId);

    @Select("SELECT count(*) FROM t_yw_order WHERE DELETE_STATUS=0 AND PRODUCT_TYPE=0")
    long state0count();

    @Select("SELECT count(*) FROM t_yw_order WHERE DELETE_STATUS=0 AND PRODUCT_TYPE=1")
    long state1count();

    @Select("SELECT count(*) FROM t_yw_order WHERE DELETE_STATUS=0 AND PRODUCT_TYPE=2")
    long state2count();

}