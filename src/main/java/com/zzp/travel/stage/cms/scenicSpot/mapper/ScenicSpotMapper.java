package com.zzp.travel.stage.cms.scenicSpot.mapper;

import com.zzp.travel.stage.cms.scenicSpot.domain.ScenicSpot;
import com.zzp.travel.stage.cms.scenicSpot.provider.ScenicSpotSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <标题>
 * <p>
 * //TODO
 * ScenicSpotMapper.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v1.0.0
 * @date 2020/7/20 22:59
 * @see ScenicSpotMapper
 **/
@Mapper
@Component
public interface ScenicSpotMapper {
    @SelectProvider(type = ScenicSpotSqlProvider.class, method = "findById")
    @ResultMap("BaseResultMap")
    ScenicSpot findById(@Param("id") String id);

    @SelectProvider(type = ScenicSpotSqlProvider.class, method = "findList")
    @ResultMap("BaseResultMap")
    List<ScenicSpot> findList();

    @SelectProvider(type = ScenicSpotSqlProvider.class, method = "indexList")
    @ResultMap("BaseResultMap")
    List<ScenicSpot> indexList();

    @SelectProvider(type = ScenicSpotSqlProvider.class, method = "findListByQuery")
    @ResultMap("BaseResultMap")
    List<ScenicSpot> findListByQuery(@Param("query") String query);

    @InsertProvider(type = ScenicSpotSqlProvider.class, method = "save")
    void save(ScenicSpot article);

    @UpdateProvider(type = ScenicSpotSqlProvider.class, method = "update")
    void update(ScenicSpot article);

    @UpdateProvider(type = ScenicSpotSqlProvider.class, method = "deleteByid")
    void deleteByid(@Param("id") String id);

    @SelectProvider(type = ScenicSpotSqlProvider.class, method = "count")
    long count();

    @SelectProvider(type = ScenicSpotSqlProvider.class, method = "count2")
    long count2();

    @SelectProvider(type = ScenicSpotSqlProvider.class, method = "state0count")
    long state0count();

    @SelectProvider(type = ScenicSpotSqlProvider.class, method = "state1count")
    long state1count();

    @SelectProvider(type = ScenicSpotSqlProvider.class, method = "state2count")
    long state2count();
}