package com.zzp.travel.stage.web.portal;

import com.zzp.travel.stage.cms.insurance.domain.Insurance;
import com.zzp.travel.stage.cms.insurance.service.InsuranceService;
import com.zzp.travel.stage.cms.order.domain.Order;
import com.zzp.travel.stage.cms.order.service.OrderService;
import com.zzp.travel.stage.role.user.service.UserService;
import com.zzp.travel.stage.utils.Tools;
import com.zzp.travel.stage.web.base.BaseController;
import com.zzp.travel.stage.web.base.BaseTemplates;
import com.zzp.travel.stage.web.base.PageParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 保险
 * <p>
 * //TODO
 * InsurancePortalController.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v1.0.0
 * @date 2020/7/22 20:05
 * @see InsurancePortalController
 **/
@Controller
public class InsurancePortalController extends BaseController {
    @Resource
    private InsuranceService insuranceService;

    @Resource
    private BaseTemplates<Insurance> baseTemplates;

    @RequestMapping("/insurance")
    public ModelAndView insurance(PageParam<?> pageParam) {
        var mv = BaseController.getModelAndView();
        if (pageParam.getPageNumber() < 1) {
            pageParam = new PageParam();
            long count = 0;
            try {
                count = insuranceService.state1count();
            } catch (Exception e) {
                e.printStackTrace();
            }
            pageParam.setCount(count);
            if (count <= 7) {
                pageParam.setSize(1);
            } else {
                pageParam.setSize(count % 7 == 0 ? count / 7 : count / 7 + 1);
            }
            pageParam.setPageNumber(1);
            pageParam.setPageSize(7);
        }
        mv.addObject("pageData", insuranceService.findByPage(pageParam.getPageNumber(), pageParam.getPageSize()));
        mv.addObject("pageParam", pageParam);
        mv.setViewName("portal/insurance");
        return mv;
    }

    @RequestMapping("/insurancePortalView")
    public ModelAndView insurancePortalView(String id) {
        var mv = BaseController.getModelAndView();
        try {
            mv.addObject("entity", insuranceService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("portal/insuranceView");
        return mv;
    }

    @RequestMapping("/insuranceCreatOrder")
    public ModelAndView insurancePortalCreatOrder(String id, HttpSession httpSession) {
        return baseTemplates.creatOrder(id, "portal/insuranceView", httpSession,
                i -> insuranceService.findById((String) i),
                (obj, ins) -> {
                    var o = (Order) obj;
                    o.setProductType(4);
                    o.setSetoffTime(Tools.date2Str(new Date(), "yyyy-MM-dd"));
                    return o;
                },true);
    }
}
