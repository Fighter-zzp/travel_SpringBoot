package com.zzp.travel.stage.web.portal;


import com.zzp.travel.stage.role.user.domain.User;
import com.zzp.travel.stage.role.user.service.UserService;
import com.zzp.travel.stage.utils.Tools;
import com.zzp.travel.stage.web.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <标题>
 * <p>
 *  //TODO
 *  UserPortalController.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/22 23:15
 * @see  UserPortalController
 **/
@Controller
@Slf4j
public class UserPortalController extends BaseController {

    @Resource
    private UserService userService;

    @RequestMapping("/userLoging")
    public ModelAndView userLoging(String userName, String password, RedirectAttributes redirectAttributes, HttpSession httpSession){
        if(Tools.notEmpty(httpSession.getAttribute("userName"))){
            return new ModelAndView(new RedirectView("/userCenter"));
        }
        if (Tools.isEmpty(userName)||Tools.isEmpty(password)){
            redirectAttributes.addFlashAttribute("message","用户名密码不得为空!");
            return new ModelAndView(new RedirectView("/goLogin"));
        }
        try {
            var user = userService.login(userName, password);
            if (Tools.isEmpty(user)){
                redirectAttributes.addFlashAttribute("message","用户名不存在或密码错误!");
                return new ModelAndView(new RedirectView("/goLogin"));
            }else{
                if (user.getState() == 1) {
                    httpSession.setAttribute("userName",userName);
                    return new ModelAndView(new RedirectView("/userCenter"));
                } else {
                    redirectAttributes.addFlashAttribute("message","账户已被注销!");
                    return new ModelAndView(new RedirectView("/goLogin"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView(new RedirectView("/goLogin"));
    }

    @RequestMapping("/register")
    public ModelAndView register(){
        var mv = BaseController.getModelAndView();
        mv.setViewName("portal/register");
        return mv;
    }

    @RequestMapping("/registering")
    public ModelAndView registering(HttpServletRequest request, String checkPassword, RedirectAttributes redirectAttributes) throws Exception{
        var user = new User();
        this.bindValidateRequestEntity(request,user);
        if(Tools.isEmpty(user)){
            redirectAttributes.addFlashAttribute("message","用户名密码不得为空!");
            return new ModelAndView(new RedirectView("/register"));
        }
        if(!user.getPassword().equals(checkPassword)){
            redirectAttributes.addFlashAttribute("message","密码与确认密码不一致!");
            return new ModelAndView(new RedirectView("/register"));
        }
        var entity = userService.findByUserName(user.getUserName());
        if (entity != null) {
            redirectAttributes.addFlashAttribute("message","用户名已存在!");
            return new ModelAndView(new RedirectView("/register"));
        }
        try {
            user.setId(Tools.getUUID());
            user.setState(1);
            userService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("message","注册成功，请登陆!");
        return new ModelAndView(new RedirectView("/goLogin"));
    }

    @RequestMapping("/personInfo")
    public ModelAndView personInfo(HttpSession httpSession){
        var mv = BaseController.getModelAndView();
        try {
            mv.addObject("entity",userService.findByUserName(httpSession.getAttribute("userName").toString()));
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("portal/personalIntro");
        return mv;
    }

    @RequestMapping("/personSave")
    public String personSave(HttpServletRequest request, String id){
        User entity = null;
        try{
            if(Tools.notEmpty(id)){
                entity = userService.findById(id);
            }
            this.bindValidateRequestEntity(request,entity);
            userService.update(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return REDIRECT+"/userCenter";
    }

    @RequestMapping("/changePassword")
    public ModelAndView changePassword(HttpSession httpSession){
        var mv = BaseController.getModelAndView();
        mv.setViewName("portal/changePassword");
        return mv;
    }

    @RequestMapping("/changePasswordSave")
    public ModelAndView changePasswordSave(String password,String newPassword,String checkPassword,HttpSession httpSession) throws Exception {
        var mv = BaseController.getModelAndView();
        if(Tools.isEmpty(password)||Tools.isEmpty(newPassword)||Tools.isEmpty(checkPassword)){
            mv.addObject("message","密码输入不能为空！");
            mv.setViewName("portal/changePassword");
            return mv;
        }
        var user = userService.findByUserName(httpSession.getAttribute("userName").toString());
        if(!user.getPassword().equals(password)){
            mv.addObject("message","原密码输入不正确！");
            mv.setViewName("portal/changePassword");
            return mv;
        }else if(!newPassword.equals(checkPassword)){
            mv.addObject("message","新密码与确认密码不一致！");
            mv.setViewName("portal/changePassword");
            return mv;
        }else{
            user.setPassword(newPassword);
            userService.update(user);
        }
        mv.setViewName("portal/userCenter");
        return mv;
    }
}
