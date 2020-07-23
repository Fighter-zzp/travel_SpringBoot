package com.zzp.travel.stage.web.base.callback;
/**
 * 操作接口
 * <p>
 *  //TODO
 *  OptCallback.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/21 20:15
 * @see  OptCallback
 **/
public interface OptCallback<T> {
    /**
     * 操作方法，完成某种操作
     * @param sth
     * @return
     */
    T doOpt(Object sth);
}
