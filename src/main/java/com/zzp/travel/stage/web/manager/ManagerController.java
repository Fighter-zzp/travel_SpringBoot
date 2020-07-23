package com.zzp.travel.stage.web.manager;

import com.zzp.travel.stage.role.user.service.ProvinceService;
import com.zzp.travel.stage.web.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
/**
 * 管理员系统控制层
 * <p>
 *  //TODO
 *  ManagerController.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/19 21:51
 * @see  ManagerController
 **/
@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Resource
    private ProvinceService provinceService;

    @RequestMapping("/index")
    public ModelAndView manager() {
        ModelAndView mv = BaseController.getModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/main")
    public ModelAndView main() throws Exception {
        ModelAndView mv = BaseController.getModelAndView();
        mv.addObject("porvice",provinceService.getProvinceCount());
        mv.setViewName("main");
        return mv;
    }

    @RequestMapping("/404")
    public ModelAndView notFound() {
        ModelAndView mv = BaseController.getModelAndView();
        mv.setViewName("404");
        return mv;
    }

    @RequestMapping("/systemParameter")
    public ModelAndView systemParameter() {
        ModelAndView mv = BaseController.getModelAndView();
        mv.setViewName("systemParameter/systemParameter");
        return mv;
    }

    @RequestMapping("/test")
    public void test(@RequestBody String resmsg) throws IOException {
        System.out.println(resmsg);
    }

}