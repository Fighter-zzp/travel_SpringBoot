package com.zzp.travel.stage.web.manager;

import com.zzp.travel.stage.cms.hotel.domain.Hotel;
import com.zzp.travel.stage.cms.strategy.domain.Strategy;
import com.zzp.travel.stage.cms.strategy.service.StrategyService;
import com.zzp.travel.stage.utils.Tools;
import com.zzp.travel.stage.web.base.BaseController;
import com.zzp.travel.stage.web.base.BaseTemplates;
import com.zzp.travel.stage.web.base.PageParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <标题>
 * <p>
 * //TODO
 * StrategyController.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v1.0.0
 * @date 2020/7/22 16:20
 * @see StrategyController
 **/
@Slf4j
@RequestMapping("/manager")
@Controller
public class StrategyController extends BaseController {
    @Resource
    private StrategyService strategyService;

    @Resource
    private BaseTemplates<Strategy> baseTemplates;

    @RequestMapping("/strategyList")
    public ModelAndView scenicSpotList(PageParam pageParam, @RequestParam(value = "query", required = false) String query) throws Exception {
        return baseTemplates.findList(pageParam, MANAGER_ROW, query,
                "strategy/strategyList",
                strategyService.count(),
                (currentPage, pageSize, keyword) -> strategyService.findByPage(currentPage, pageSize, keyword));
    }

    @RequestMapping("/strategyAdd")
    public ModelAndView scenicSpotAdd() {
        return baseTemplates.editInfo(null,
                "strategy/strategyEdit",
                o -> new Strategy());
    }

    @RequestMapping("/strategyView")
    public ModelAndView scenicSpotView(String id) {
        return baseTemplates.editInfo(id,
                "strategy/strategyView",
                i -> strategyService.findById((String) i));

    }

    @RequestMapping("/strategyEdit")
    public ModelAndView scenicSpotEdit(String id) {
        return baseTemplates.editInfo(id,
                "strategy/strategyEdit",
                i -> strategyService.findById((String) i));
    }

    @RequestMapping("/strategySave")
    public String scenicSpotSave(HttpServletRequest request, String id, @RequestParam("fileName") MultipartFile file) {
        baseTemplates.updateOrSave(request, this, file, id, "/strategy", Strategy.class,
                i -> strategyService.findById((String) i),
                e -> {
                    var strategy = (Strategy) e;
                    if (Tools.isEmpty(strategy.getId())) {
                        strategy.setId(Tools.getUUID());
                        strategyService.save(strategy);
                    } else {
                        strategyService.update(strategy);
                    }
                    return null;
                });
        return REDIRECT + "/manager/strategyList";
    }

    @RequestMapping("/strategyDelete")
    public String scenicSpotDelete(String id) {
        if (Tools.notEmpty(id)) {
            try {
                strategyService.deleteById(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return REDIRECT + "/manager/strategyList";
    }


}
