package com.zzp.travel.stage.web.portal;

import com.zzp.travel.stage.cms.order.domain.Order;
import com.zzp.travel.stage.cms.route.domain.TravelRoute;
import com.zzp.travel.stage.cms.route.service.TravelRouteService;
import com.zzp.travel.stage.role.user.service.UserService;
import com.zzp.travel.stage.web.base.BaseController;
import com.zzp.travel.stage.web.base.BaseTemplates;
import com.zzp.travel.stage.web.base.PageParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * <标题>
 * <p>
 * //TODO
 * TravelRoutePortalController.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v1.0.0
 * @date 2020/7/22 23:03
 * @see TravelRoutePortalController
 **/
@Slf4j
@Controller
public class TravelRoutePortalController extends BaseController {

    @Resource
    TravelRouteService travelRouteService;

    @Resource
    private BaseTemplates<TravelRoute> baseTemplates;

    @RequestMapping("/travelRoute")
    public ModelAndView travelRoute(PageParam<?> pageParam) {
        var count = 0L;
        try {
            count = travelRouteService.count2();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseTemplates.findList(pageParam, PORTAL_ROW, null, "portal/travelRoute", count,
                ((currentPage, pageSize, query) -> {
                    try {
                        return travelRouteService.findByPage(currentPage, pageSize);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    log.error("/travelRoute查不到路线数据");
                    return null;
                }));
    }

    @RequestMapping("/travelRoutePortalView")
    public ModelAndView travelRoutePortalView(String id) {
        return baseTemplates.editInfo(id, "portal/travelRouteView", i -> {
            try {
                return travelRouteService.findById((String) i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.error("查询查不到id为{}，的路线", i);
            return null;
        });
    }

    @RequestMapping("/travelRouteCreatOrder")
    public ModelAndView travelRouteCreatOrder(String id, HttpSession httpSession) {
        return baseTemplates.creatOrder(id, "portal/travelRouteView", httpSession, i -> {
                    try {
                        return travelRouteService.findById((String) i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    log.error("查询查不到id为{}，的路线", i);
                    return null;
                },
                (obj, tr) -> {
                    var order = (Order) obj;
                    order.setProductType(0);
                    order.setSetoffTime(tr.getStartTime());
                    return order;
                }, true);
    }
}
