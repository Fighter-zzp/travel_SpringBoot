package com.zzp.travel.stage.web.manager;

import com.zzp.travel.stage.cms.order.domain.Order;
import com.zzp.travel.stage.cms.order.service.OrderService;
import com.zzp.travel.stage.utils.Tools;
import com.zzp.travel.stage.web.base.BaseController;
import com.zzp.travel.stage.web.base.BaseTemplates;
import com.zzp.travel.stage.web.base.PageParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <标题>
 * <p>
 * //TODO
 * MessageController.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v1.0.0
 * @date 2020/7/22 15:42
 * @see OrderController
 **/
@Slf4j
@Controller
@RequestMapping("/manager")
public class OrderController extends BaseController {
    @Resource
    private OrderService orderService;

    @Resource
    private BaseTemplates<Order> baseTemplates;

    @GetMapping("/orderList")
    public ModelAndView orderList(PageParam<?> pageParam, @RequestParam(value = "query", required = false) String query) {
        return baseTemplates.findList(pageParam, MANAGER_ROW, query,
                "order/orderList",
                orderService.count(),
                ((currentPage, pageSize, k) -> orderService.findByPage(currentPage, pageSize, k)));
    }

    @GetMapping("/orderAdd")
    public ModelAndView orderAdd() {
        return baseTemplates.editInfo(null,
                "order/orderEdit",
                o -> new Order());
    }

    @GetMapping("/orderView")
    public ModelAndView messageView(String id) {
        return baseTemplates.editInfo(id,
                "order/orderEdit",
                i -> orderService.findById((String) i));

    }

    @GetMapping("/orderEdit")
    public ModelAndView orderEdit(String id) {
        return baseTemplates.editInfo(id,
                "order/orderEdit",
                i -> orderService.findById((String) i));
    }

    @PostMapping("/orderSave")
    public String orderSave(HttpServletRequest request, String id) {
        Order entity;
        try {
            if (Tools.notEmpty(id)) {
                entity = orderService.findById(id);
            } else {
                entity = new Order();
            }
            this.bindValidateRequestEntity(request, entity);
            if (Tools.isEmpty(entity.getId())) {
                entity.setId(Tools.getUUID());
                orderService.save(entity);
            } else {
                orderService.update(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return REDIRECT + "/manager/orderList";
    }

    @GetMapping("/orderDelete")
    public String orderDelete(String id) {
        if (Tools.notEmpty(id)) {
            try {
                orderService.deleteById(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return REDIRECT + "/manager/orderList";
    }

}
