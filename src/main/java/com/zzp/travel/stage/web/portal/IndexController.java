package com.zzp.travel.stage.web.portal;

import com.zzp.travel.stage.utils.Tools;
import com.zzp.travel.stage.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
/**
 * index页面显示
 * <p>
 *  //TODO
 *  IndexController.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/22 19:58
 * @see  IndexController
 **/
@Controller
public class IndexController extends BaseController {

    @RequestMapping("/index")
    public ModelAndView index(){
        var mv = BaseController.getModelAndView();
        /*setViewName()表示跳转到页面的位置/templates/* ,在配置文件yml*/
        mv.setViewName("portal/index");
        return mv;
    }

    @RequestMapping("/goLogin")
    public ModelAndView goLogin(HttpSession httpSession){
        if(Tools.notEmpty(httpSession.getAttribute("userName"))){
            return new ModelAndView("portal/index");
        }
        return new ModelAndView("portal/login");
    }

    @RequestMapping("/goLogout")
    public ModelAndView goLogout(HttpSession httpSession){
        if(Tools.notEmpty(httpSession.getAttribute("userName"))){
            httpSession.removeAttribute("userName");
        }
        return new ModelAndView("portal/login");
    }
}
