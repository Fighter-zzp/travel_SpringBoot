package com.zzp.travel.stage.web.manager;

import com.zzp.travel.stage.role.admin.service.AdminService;
import com.zzp.travel.stage.web.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 管理员页面登录
 * <p>
 *  页面 登录 退出
 *  LoginController.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/6/25 22:01
 * @see  LoginController
 **/
@Controller
@Slf4j
public class LoginController extends BaseController {

    @Resource
    private AdminService adminService;

    @GetMapping("/login")
    public String login(HttpServletRequest request){
        Object user = request.getSession().getAttribute("admin");
        if (user != null) {
            return REDIRECT+"/manager/index";
        }
        return "login";
    }

    @PostMapping("/loging")
    public String loging(String userName,String password,RedirectAttributes redirectAttributes,HttpServletRequest request){
        if (Strings.isEmpty(userName)||Strings.isEmpty(password)){
            redirectAttributes.addFlashAttribute("message","用户名密码不得为空!");
            return REDIRECT+"/login";
        }
        try {
            var admin = adminService.login(userName, password);
            if (Objects.isNull(admin)){
                redirectAttributes.addFlashAttribute("message","用户名不存在或密码错误!");
                return REDIRECT+"/login";
            }else{
                if (admin.getState() == 1) {
                    request.getSession().setAttribute("admin", admin);
                    return REDIRECT+"/manager/index";
                } else {
                    redirectAttributes.addFlashAttribute("message","账户已被停用!");
                    return REDIRECT+"/login";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return REDIRECT+"/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        Object user = request.getSession().getAttribute("admin");
        if (user != null) {
            request.getSession().removeAttribute("admin");
        }
        return "/login";
    }
}
