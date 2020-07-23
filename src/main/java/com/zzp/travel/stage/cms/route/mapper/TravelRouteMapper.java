package com.zzp.travel.stage.cms.route.mapper;

import com.zzp.travel.stage.cms.route.domain.TravelRoute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.CommonsMapper;

import java.util.List;

/**
 * <标题>
 * <p>
 *  //TODO
 *  TravelRouteMapper.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/20 22:14
 * @see  TravelRouteMapper
 **/
@Mapper
public interface TravelRouteMapper extends CommonsMapper<TravelRoute> {
     TravelRoute findById(@Param("id") String id);

     List<TravelRoute> findList();

     List<TravelRoute> indexList();

     List<TravelRoute> findListByQuery(@Param("query") String query);

     void save(TravelRoute articleClass);

     void update(TravelRoute articleClass);

     void deleteById(@Param("id") String id);

     long count();

     long count2();

     long state0count();
     long state1count();
     long state2count();
}