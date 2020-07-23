package com.zzp.travel.stage.web.manager;

import com.zzp.travel.stage.cms.hotel.domain.Hotel;
import com.zzp.travel.stage.cms.hotel.service.HotelService;
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
 * HotelController.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v1.0.0
 * @date 2020/7/22 2:17
 * @see HotelController
 **/
@Slf4j
@Controller
@RequestMapping("/manager")
public class HotelController extends BaseController {
    @Resource
    private HotelService hotelService;

    @Resource
    private BaseTemplates<Hotel> baseTemplates;

    @RequestMapping("/hotelList")
    public ModelAndView scenicSpotList(PageParam pageParam, @RequestParam(value = "query", required = false) String query) throws Exception {
        return baseTemplates.findList(pageParam, MANAGER_ROW, query,
                "hotel/hotelList",
                hotelService.count(),
                (currentPage, pageSize, keyword) -> hotelService.findByPage(currentPage, pageSize, keyword));
    }

    @RequestMapping("/hotelAdd")
    public ModelAndView scenicSpotAdd() {
        return baseTemplates.editInfo(null,
                "hotel/hotelEdit",
                o -> new Hotel());
    }

    @RequestMapping("/hotelView")
    public ModelAndView scenicSpotView(String id) {
        return baseTemplates.editInfo(id,
                "hotel/hotelView",
                i -> hotelService.findById((String) i));

    }

    @RequestMapping("/hotelEdit")
    public ModelAndView scenicSpotEdit(String id) {
        return baseTemplates.editInfo(id,
                "hotel/hotelEdit",
                i -> hotelService.findById((String) i));
    }

    @RequestMapping("/hotelSave")
    public String scenicSpotSave(HttpServletRequest request, String id, @RequestParam("fileName") MultipartFile file) {
        baseTemplates.updateOrSave(request, this, file, id, "/hotel", Hotel.class,
                i -> hotelService.findById((String) i),
                e -> {
                    var hotel = (Hotel) e;
                    if (Tools.isEmpty(hotel.getId())) {
                        hotel.setId(Tools.getUUID());
                        hotelService.save(hotel);
                    } else {
                        hotelService.update(hotel);
                    }
                    return null;
                });
        return REDIRECT + "/manager/hotelList";
    }

    @RequestMapping("/hotelDelete")
    public String scenicSpotDelete(String id) {
        if (Tools.notEmpty(id)) {
            try {
                hotelService.deleteById(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return REDIRECT + "/manager/hotelList";
    }

}
