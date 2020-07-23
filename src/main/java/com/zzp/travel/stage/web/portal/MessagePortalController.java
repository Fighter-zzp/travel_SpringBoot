package com.zzp.travel.stage.web.portal;


import com.zzp.travel.stage.cms.message.domain.Message;
import com.zzp.travel.stage.cms.message.service.MessageService;
import com.zzp.travel.stage.role.user.service.UserService;
import com.zzp.travel.stage.utils.Tools;
import com.zzp.travel.stage.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * <标题>
 * <p>
 *  //TODO
 *  MessagePortalController.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/22 22:40
 * @see  MessagePortalController
 **/
@Controller
public class MessagePortalController extends BaseController {

    @Resource
    private MessageService messageService;
    @Resource
    private UserService userService;

    @RequestMapping("/myMessage")
    public ModelAndView myMessage(HttpSession httpSession) throws Exception {
        var mv = BaseController.getModelAndView();
        var user = userService.findByUserName(httpSession.getAttribute("userName").toString());
        mv.addObject("messageCount", messageService.countByUserId(user.getId()));
        mv.setViewName("portal/myMessage");
        return mv;
    }

    @RequestMapping("/saveMessage")
    public ModelAndView saveMeessage(HttpSession httpSession, HttpServletRequest request) throws Exception {
        var mv = BaseController.getModelAndView();
        var user = userService.findByUserName(httpSession.getAttribute("userName").toString());
        var message = new Message();
        this.bindValidateRequestEntity(request,message);
        message.setId(Tools.getUUID());
        message.setUserId(user.getId());
        message.setUserName(user.getUserName());
        message.setName(user.getName());
        message.setState(1);
        messageService.save(message);
        mv.addObject("messageCount", messageService.countByUserId(user.getId()));
        mv.addObject("message","保存成功！");
        mv.setViewName("portal/myMessage");
        return mv;
    }

    @RequestMapping("/messageList")
    public ModelAndView messageList(
            HttpSession httpSession,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "7") int pageSize
    ) throws Exception {
        var mv = BaseController.getModelAndView();
        var user = userService.findByUserName(httpSession.getAttribute("userName").toString());
        var pageParam = messageService.findByPageByUserId(pageNum,pageSize,user.getId());
        mv.addObject("pageData", pageParam.getResult());
        mv.addObject("pageParam",pageParam);
        mv.setViewName("portal/messageList");
        return mv;
    }
}
