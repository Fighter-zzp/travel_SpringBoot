package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
/**
 * 统一tk.mybatis接口
 * <p>
 *  人皆知有用之用，而莫知无用之用也
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/3/2 15:14
 * @see  CommonsMapper
 **/
public interface CommonsMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
