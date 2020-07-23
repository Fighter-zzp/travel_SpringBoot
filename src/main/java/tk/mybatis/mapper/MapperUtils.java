package tk.mybatis.mapper;

import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;
/**
 * Mapper 的工具类
 * <p>
 *  //TODO
 *  MapperUtils.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/22 1:26
 * @see  MapperUtils
 **/
public class MapperUtils {
    /**
     * Mapper中添加where后的赋值
     * @param tClass 反射类型
     * @param args 参数 偶数
     * @param <T> 泛型对象 主要生产Example
     * @return {@link Example}
     */
    public static <T> Example whereArgs(Class<T> tClass, String... args) {
        var example = new Example(tClass);
        if (args.length == 0) {
            return example;
        }
        var criteria = example.createCriteria();
        Assert.isTrue(args.length%2==0,"参数数目必须是偶数");
        for (var i=0;i<args.length-1;i+=2) {
            criteria = criteria.andEqualTo(args[i], args[i + 1]);
        }
        return example;
    }
}
