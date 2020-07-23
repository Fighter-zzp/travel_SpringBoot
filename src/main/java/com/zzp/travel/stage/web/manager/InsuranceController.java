package com.zzp.travel.stage.web.manager;

import com.zzp.travel.stage.cms.insurance.domain.Insurance;
import com.zzp.travel.stage.cms.insurance.service.InsuranceService;
import com.zzp.travel.stage.cms.scenicSpot.domain.ScenicSpot;
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
import org.springframework.web.multipart.MultipartFile;
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
 * @see InsuranceController
 **/
@Slf4j
@Controller
@RequestMapping("/manager")
public class InsuranceController extends BaseController {
    @Resource
    private InsuranceService insuranceService;

    @Resource
    private BaseTemplates<Insurance> baseTemplates;

    @GetMapping("/insuranceList")
    public ModelAndView insuranceList(PageParam<?> pageParam, @RequestParam(value = "query", required = false) String query) {
        return baseTemplates.findList(pageParam, MANAGER_ROW, query,
                "insurance/insuranceList",
                insuranceService.count(),
                ((currentPage, pageSize, k) -> insuranceService.findByPage(currentPage, pageSize, k)));
    }

    @GetMapping("/insuranceAdd")
    public ModelAndView insuranceAdd() {
        return baseTemplates.editInfo(null,
                "insurance/insuranceEdit",
                o -> new Insurance());
    }

    @GetMapping("/insuranceView")
    public ModelAndView insuranceView(String id) {
        return baseTemplates.editInfo(id,
                "insurance/insuranceEdit",
                i -> insuranceService.findById((String) i));

    }

    @GetMapping("/insuranceEdit")
    public ModelAndView insuranceEdit(String id) {
        return baseTemplates.editInfo(id,
                "insurance/insuranceEdit",
                i -> insuranceService.findById((String) i));
    }

    @PostMapping("/insuranceSave")
    public String insuranceSave(HttpServletRequest request, String id, @RequestParam("fileName") MultipartFile file) {
        baseTemplates.updateOrSave(request, this, file, id, "/insurance", Insurance.class,
                i -> insuranceService.findById((String) i),
                e -> {
                    var insurance = (Insurance) e;
                    if (Tools.isEmpty(insurance.getId())) {
                        insurance.setId(Tools.getUUID());
                        insuranceService.save(insurance);
                    } else {
                        insuranceService.update(insurance);
                    }
                    return null;
                });
        return REDIRECT + "/manager/insuranceList";
    }

    @GetMapping("/insuranceDelete")
    public String messageDelete(String id) {
        if (Tools.notEmpty(id)) {
            try {
                insuranceService.deleteById(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return REDIRECT + "/manager/insuranceList";
    }

}
