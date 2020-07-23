package com.zzp.travel.stage.web.portal;


import com.zzp.travel.stage.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * <标题>
 * <p>
 *  //TODO
 *  UserCenterController.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/22 23:15
 * @see  UserCenterController
 **/
@Controller
public class UserCenterController extends BaseController {

    @RequestMapping("/userCenter")
    public ModelAndView userCenter(){
        var mv = BaseController.getModelAndView();
        mv.setViewName("portal/userCenter");
        return mv;
    }

}
