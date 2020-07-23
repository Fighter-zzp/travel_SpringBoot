package com.zzp.travel.stage.web.portal;

import com.zzp.travel.stage.cms.strategy.domain.Strategy;
import com.zzp.travel.stage.cms.strategy.service.StrategyService;
import com.zzp.travel.stage.web.base.BaseController;
import com.zzp.travel.stage.web.base.BaseTemplates;
import com.zzp.travel.stage.web.base.PageParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * <标题>
 * <p>
 *  //TODO
 *  StrategyPortalController.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/22 22:59
 * @see  StrategyPortalController
 **/
@Controller
@Slf4j
public class StrategyPortalController extends BaseController {

    @Resource
    private StrategyService strategyService;

    @Resource
    private BaseTemplates<Strategy> baseTemplates;

    @RequestMapping("/strategy")
    public ModelAndView strategy(PageParam<?> pageParam) throws Exception {
        return baseTemplates.findList(pageParam,PORTAL_ROW,null,"portal/strategy",strategyService.state1count(),
                ((currentPage, pageSize, query) -> strategyService.findByPage(currentPage,pageSize)));
    }

    @RequestMapping("/strategyPortalView")
    public ModelAndView strategyPortalView(String id){
        return baseTemplates.editInfo(id,"portal/strategyView",i->strategyService.findById((String) i));
    }
}
