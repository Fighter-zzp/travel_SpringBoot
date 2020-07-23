package com.zzp.travel.stage.web.base.callback;
/**
 * 对象提供
 * <p>
 *  //TODO
 *  supplyObj.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/22 22:29
 * @see  ProductObj
 **/
public interface ProductObj<T> {
    /**
     * 获取对象
     * @param o
     * @param t
     * @return
     */
    Object getObj(Object o,T t);
}
