package com.zzp.travel.stage.web.manager;

import com.zzp.travel.stage.role.user.domain.User;
import com.zzp.travel.stage.role.user.service.UserService;
import com.zzp.travel.stage.utils.Tools;
import com.zzp.travel.stage.web.base.BaseController;
import com.zzp.travel.stage.web.base.PageParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <标题>
 * <p>
 *  //TODO
 *  UserController.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/20 17:27
 * @see  UserController
 **/
@Controller
@RequestMapping("/manager")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    @RequestMapping("/userList")
    public ModelAndView userList(PageParam<?> pageParam, @RequestParam(value = "query", required = false) String query) throws Exception {
        var mv = BaseController.getModelAndView();
        if(pageParam.getPageNumber()<1){
            pageParam =new PageParam<>();
            long count = 0;
            try {
                count = userService.count();
            } catch (Exception e) {
                e.printStackTrace();
            }
            pageParam.setCount(count);
            if(count<=10){
                pageParam.setSize(1);
            }else{
                pageParam.setSize(count%10==0?count/10:count/10+1);
            }
            pageParam.setPageNumber(1);
            pageParam.setPageSize(10);
        }
        var list = userService.findByPage(pageParam.getPageNumber(),pageParam.getPageSize(), query);
        mv.addObject("pageData", list);
        if (Tools.notEmpty(query)) {
            mv.addObject("query", query);
            pageParam.setCount(list.size());
            if (list.size() > pageParam.getPageSize()) {
                pageParam.setSize(list.size() / pageParam.getPageSize());
            } else {
                pageParam.setSize(1);
            }
        }
        mv.addObject("pageParam",pageParam);
        mv.setViewName("/user/allUsers");
        return mv;
    }
    @RequestMapping("/userAdd")
    public ModelAndView userAdd(){
        var mv = BaseController.getModelAndView();
        mv.addObject("entity",new User());
        mv.setViewName("user/userEdit");
        return mv;
    }

    @RequestMapping("/userView")
    public ModelAndView userView(String id){
        var mv = BaseController.getModelAndView();
        try {
            mv.addObject("entity",userService.findById(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("user/userView");
        return mv;
    }

    @RequestMapping("/userEdit")
    public ModelAndView userEdit(String id){
        var mv = BaseController.getModelAndView();
        try {
            mv.addObject("entity",userService.findById(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("user/userEdit");
        return mv;
    }

    @RequestMapping("/userSave")
    public ModelAndView userSave(HttpServletRequest request, String id, RedirectAttributes redirectAttributes) throws Exception {
        var mv = BaseController.getModelAndView();
        User entity = null;
        try {
            if(Tools.notEmpty(id)){
                entity = userService.findById(id);
            }else{
                entity = new User();
            }
            this.bindValidateRequestEntity(request,entity);
            if (Tools.isEmpty(entity.getId())){
                User object = userService.findByUserName(entity.getUserName());
                if (object != null) {
                    mv.addObject("message","用户名已存在!");
                    mv.addObject("entity",entity);
                    mv.setViewName("user/userEdit");
                    return mv;
                }
                entity.setId(Tools.getUUID());
                userService.save(entity);
            }else{
                userService.update(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.addObject("pageData", userService.findByPage(1, 10,null));
        var pageParam =new PageParam<>();
        long count = 0;
        try {
            count = userService.count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        pageParam.setCount(count);
        if(count<=10){
            pageParam.setSize(1);
        }else{
            pageParam.setSize(count%10==0?count/10:count/10+1);
        }
        pageParam.setPageNumber(1);
        pageParam.setPageSize(10);
        mv.addObject("pageParam",pageParam);
        mv.setViewName("user/allUsers");
        return mv;
    }

    @RequestMapping("/userDelete")
    public String userDelete(String id){
        if(Tools.notEmpty(id)){
            try {
                userService.deleteByid(id);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return REDIRECT+"/manager/userList";
    }
}
