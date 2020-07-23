package com.zzp.travel.stage.role.user.mapper;

import com.zzp.travel.stage.role.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.CommonsMapper;

import java.util.List;

/**
 * <标题>
 * <p>
 *  //TODO
 *  UserMapper.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/20 16:59
 * @see  UserMapper
 **/
@Mapper
public interface UserMapper extends CommonsMapper<User> {
    /**
     * 分页查询用户列表
     * @param query
     * @return
     */
    @Select("SELECT * FROM t_pz_user WHERE DELETE_STATUS=0 AND USER_NAME LIKE #{query,jdbcType=VARCHAR} ORDER BY ADD_TIME DESC")
    List<User> findListByQuery(@Param("query")String query);
}