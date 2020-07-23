package com.zzp.travel.stage.web.base;

import com.google.common.collect.Lists;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * BaseController
 * <p>
 *  //TODO
 *  控制视图
 *  BaseController.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/6/25 21:52
 * @see  BaseController
 **/
public class BaseController {
    /**
     * 设置锁
     */
    private static Lock lock = new ReentrantLock();

    /**
     * 视图池
     */
    private static List<ModelAndView> modelAndViewsPool = Lists.newArrayListWithCapacity(20);

    /**
     * 重定向标记(客户端行为)
     */
    public static final String REDIRECT = "redirect:";

    /**
     * 数据条数（管理系统）
     */
    public static final int MANAGER_ROW = 10;

    /**
     * 数据条数（主站）
     */
    public static final int PORTAL_ROW = 7;

    /**
     * 数据转换组件
     */
    @Resource
    protected ConversionService conversionService;

    /**
     * 过去ModelView
     * @return {@link ModelAndView}
     */
    public static ModelAndView getModelAndView(){
        lock.lock();
        try {
            if (modelAndViewsPool.size() > 0) {
                return modelAndViewsPool.remove(0);
            }
        }finally{
            lock.unlock();
        }
        return new ModelAndView();
    }

    /**
     * 邦定表单数据并且校验数据
     * @param request
     * @param entity
     * @throws ConstraintViolationException
     */
    protected void bindValidateRequestEntity(HttpServletRequest request, Object entity) throws ConstraintViolationException {
        var binder = new ServletRequestDataBinder(entity);
        binder.setConversionService(conversionService);
        binder.bind(request);
    }
}
