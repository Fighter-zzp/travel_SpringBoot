package com.zzp.travel.stage.web.manager;

import com.zzp.travel.stage.role.admin.domain.Admin;
import com.zzp.travel.stage.role.admin.service.AdminService;
import com.zzp.travel.stage.utils.Tools;
import com.zzp.travel.stage.web.base.BaseController;
import com.zzp.travel.stage.web.base.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/manager")
public class AdminController extends BaseController {

    @Resource
    private AdminService adminService;

    @RequestMapping("/adminList")
    public ModelAndView adminList(PageParam pageParam, @RequestParam(value = "query", required = false) String query) throws Exception {
        ModelAndView mv = BaseController.getModelAndView();
        if(pageParam.getPageNumber()<1){
            pageParam =new PageParam();
            long count = 0;
            try {
                count = adminService.count();
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
        var list = adminService.findByPage(pageParam.getPageNumber(),pageParam.getPageSize(), query);
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
        mv.setViewName("admin/adminList");
        return mv;
    }


    @RequestMapping("/adminAdd")
    public ModelAndView adminAdd(){
        ModelAndView mv = BaseController.getModelAndView();
        mv.addObject("entity",new Admin());
        mv.setViewName("admin/adminEdit");
        return mv;
    }

    @RequestMapping("/adminView")
    public ModelAndView adminView(String id){
        ModelAndView mv = BaseController.getModelAndView();
        try {
            mv.addObject("entity",adminService.findById(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("admin/adminView");
        return mv;
    }

    @RequestMapping("/adminEdit")
    public ModelAndView adminEdit(String id){
        ModelAndView mv = BaseController.getModelAndView();
        try {
            mv.addObject("entity",adminService.findById(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("admin/adminEdit");
        return mv;
    }

    @RequestMapping("/adminSave")
    public ModelAndView adminSave(HttpServletRequest request, String id) throws Exception {
        ModelAndView mv = BaseController.getModelAndView();
        Admin entity = null;
        try {
            if(Tools.notEmpty(id)){
                entity = adminService.findById(id);
            }else{
                entity = new Admin();
            }
            this.bindValidateRequestEntity(request,entity);
            if (Tools.isEmpty(entity.getId())){
                Admin object = adminService.findByUserName(entity.getUserName());
                if (object != null) {
                    mv.addObject("message","用户名已存在!");
                    mv.addObject("entity",entity);
                    mv.setViewName("admin/adminEdit");
                    return mv;
                }
                entity.setId(Tools.getUUID());
                adminService.save(entity);
            }else{
                adminService.update(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.addObject("pageData", adminService.findByPage(1, 10,null));
        PageParam pageParam =new PageParam();
        long count = 0;
        try {
            count = adminService.count();
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
        mv.setViewName("admin/adminList");
        return mv;
    }

    @RequestMapping("/adminDelete")
    public String adminDelete(String id){
        if(Tools.notEmpty(id)){
            try {
                adminService.deleteById(id);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return REDIRECT+"/manager/adminList";
    }
}