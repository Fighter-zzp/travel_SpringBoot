package com.zzp.travel.stage.common.template;

import com.github.pagehelper.PageHelper;
import com.zzp.travel.stage.cms.message.domain.Message;
import com.zzp.travel.stage.web.base.PageParam;
import com.zzp.travel.stage.web.base.callback.ListCallBack;
import org.springframework.stereotype.Component;

/**
 * 通用模板
 * <p>
 *  //TODO
 *  CommonTemplate.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/22 17:29
 * @see  CommonTemplate
 **/
@Component
public class CommonTemplate<T> {

    public PageParam<T> findByPageByUserId(int currentPage, int pageSize, String userId, ListCallBack<T> callBack){
        var pageParam = new PageParam<T>();
        var page = PageHelper.startPage(currentPage, pageSize);
        var list = callBack.listCallBack(currentPage,pageSize,userId);
        pageParam.setResult(list);
        pageParam.setSize(page.getPages());
        pageParam.setCount(page.getTotal());
        pageParam.setPageNumber(currentPage);
        pageParam.setPageSize(pageSize);
        return pageParam;
    }

}
