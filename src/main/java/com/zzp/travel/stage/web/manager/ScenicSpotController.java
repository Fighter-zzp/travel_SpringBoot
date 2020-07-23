package com.zzp.travel.stage.web.manager;

import com.zzp.travel.stage.cms.route.domain.TravelRoute;
import com.zzp.travel.stage.cms.scenicSpot.domain.ScenicSpot;
import com.zzp.travel.stage.cms.scenicSpot.service.ScenicSpotService;
import com.zzp.travel.stage.utils.Tools;
import com.zzp.travel.stage.web.base.BaseController;
import com.zzp.travel.stage.web.base.BaseTemplates;
import com.zzp.travel.stage.web.base.PageParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <标题>
 * <p>
 * //TODO
 * ScenicSpotController.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v1.0.0
 * @date 2020/7/21 20:00
 * @see ScenicSpotController
 **/
@Controller
@RequestMapping("/manager")
@Slf4j
public class ScenicSpotController extends BaseController {

    @Resource
    private ScenicSpotService scenicSpotService;

    @Resource
    private BaseTemplates<ScenicSpot> baseTemplates;

    @RequestMapping("/scenicSpotList")
    public ModelAndView scenicSpotList(PageParam pageParam, @RequestParam(value = "query", required = false) String query) throws Exception {
        return baseTemplates.findList(pageParam, MANAGER_ROW, query,
                "scenicSpot/scenicSpotList",
                scenicSpotService.count(),
                (currentPage, pageSize, keyword) ->
                {
                    try {
                        return scenicSpotService.findByPage(currentPage, pageSize, keyword);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    log.error("‘/scenicSpotList’地址方法异常，数据无法查询！ ");
                    return null;
                });
    }

    @RequestMapping("/scenicSpotAdd")
    public ModelAndView scenicSpotAdd() {
        return baseTemplates.editInfo(null,
                "scenicSpot/scenicSpotEdit",
                o -> new ScenicSpot());
    }

    @RequestMapping("/scenicSpotView")
    public ModelAndView scenicSpotView(String id) {
        return baseTemplates.editInfo(id,
                "scenicSpot/scenicSpotView",
                i -> scenicSpotService.findById((String) i));

    }

    @RequestMapping("/scenicSpotEdit")
    public ModelAndView scenicSpotEdit(String id) {
        return baseTemplates.editInfo(id,
                "scenicSpot/scenicSpotEdit",
                i -> scenicSpotService.findById((String) i));
    }

    @RequestMapping("/scenicSpotSave")
    public String scenicSpotSave(HttpServletRequest request, String id, @RequestParam("fileName") MultipartFile file) {
        baseTemplates.updateOrSave(request, this, file, id, "/scenicSpot", ScenicSpot.class,
                i -> scenicSpotService.findById((String) i),
                e -> {
                    var spot = (ScenicSpot) e;
                    if (Tools.isEmpty(spot.getId())) {
                        spot.setId(Tools.getUUID());
                        scenicSpotService.save(spot);
                    } else {
                        scenicSpotService.update(spot);
                    }
                    return null;
                });
        return REDIRECT + "/manager/scenicSpotList";
    }

    @RequestMapping("/scenicSpotDelete")
    public String scenicSpotDelete(String id) {
        if (Tools.notEmpty(id)) {
            try {
                scenicSpotService.deleteByid(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return REDIRECT + "/manager/scenicSpotList";
    }

}
