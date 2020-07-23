package com.zzp.travel.stage.web.portal;


import com.zzp.travel.stage.cms.car.domain.Car;
import com.zzp.travel.stage.cms.car.service.CarService;
import com.zzp.travel.stage.cms.order.domain.Order;
import com.zzp.travel.stage.cms.order.service.OrderService;
import com.zzp.travel.stage.cms.route.domain.TravelRoute;
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
 * 车票
 * <p>
 * //TODO
 * CarPortalController.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v1.0.0
 * @date 2020/7/22 20:01
 * @see CarPortalController
 **/
@Controller
public class CarPortalController extends BaseController {
    @Resource
    private CarService carService;
    @Resource
    private UserService userService;
    @Resource
    private OrderService orderService;

    @Resource
    private BaseTemplates<Car> baseTemplates;

    @RequestMapping("/car")
    public ModelAndView carList(PageParam<?> pageParam) {
        long count = 0;
        try {
            count = carService.state1count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseTemplates.findList(pageParam,PORTAL_ROW,null,"portal/car",count,
                ((currentPage, pageSize, query) -> carService.findByPage(currentPage,pageSize)));
    }

    @RequestMapping("/carPortalView")
    public ModelAndView carView(String id) {
        return baseTemplates.editInfo(id,"portal/carView",i->carService.findById((String) i));
    }

    @RequestMapping("/carCreatOrder")
    public ModelAndView carCreatOrder(String id, HttpSession httpSession) {
        return baseTemplates.creatOrder(id,"portal/carView",httpSession,
                i->carService.findById((String)i),
                (obj,car)->{
                    var o = (Order)obj;
                    o.setProductType(3);
                    o.setSetoffTime(car.getStartDateAndTime());
                    return o;
                },true);
    }
}
