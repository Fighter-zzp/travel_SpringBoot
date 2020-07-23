package com.zzp.travel.stage.role.admin.mapper;

import com.zzp.travel.stage.role.admin.domain.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.CommonsMapper;

import java.util.List;

/**
 * AdminMapper
 * <p>
 *  //TODO
 *  AdminMapper.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/19 22:10
 * @see  AdminMapper
 **/
@Mapper
public interface AdminMapper extends CommonsMapper<Admin> {

    /**
     * 模糊查询
     * @param keyword
     * @return
     */
    @Select("SELECT * FROM t_pz_admin_user WHERE DELETE_STATUS=0 AND " +
            "USER_NAME LIKE #{keyword,jdbcType=VARCHAR} ORDER BY ADD_TIME DESC")
    List<Admin>  findListByQuery(String keyword);
}
