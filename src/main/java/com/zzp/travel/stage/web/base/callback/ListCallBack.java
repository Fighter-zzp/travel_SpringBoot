package com.zzp.travel.stage.web.base.callback;

import java.util.List;

/**
 * <标题>
 * <p>
 *  //TODO
 *  BaseCallBack.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/20 23:22
 * @see  ListCallBack
 **/
public interface ListCallBack<T> {
    /**
     * 回调数据列表
     * @param currentPage 当前页
     * @param pageSize 条数
     * @param query 关键字
     * @return list
     */
    List<T> listCallBack(int currentPage, int pageSize, String query);
}
