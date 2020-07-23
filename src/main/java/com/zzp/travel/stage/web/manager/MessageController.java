package com.zzp.travel.stage.web.manager;

import com.zzp.travel.stage.cms.hotel.domain.Hotel;
import com.zzp.travel.stage.cms.message.domain.Message;
import com.zzp.travel.stage.cms.message.service.MessageService;
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
 * @see MessageController
 **/
@Slf4j
@Controller
@RequestMapping("/manager")
public class MessageController extends BaseController {
    @Resource
    private MessageService messageService;

    @Resource
    private BaseTemplates<Message> baseTemplates;

    @GetMapping("/messageList")
    public ModelAndView messageList(PageParam<?> pageParam, @RequestParam(value = "query", required = false) String query) {
        return baseTemplates.findList(pageParam, MANAGER_ROW, query,
                "message/messageList",
                messageService.count(),
                ((currentPage, pageSize, k) -> messageService.findByPage(currentPage, pageSize, k)));
    }

    @GetMapping("/messageAdd")
    public ModelAndView messageAdd() {
        return baseTemplates.editInfo(null,
                "message/messageEdit",
                o -> new Message());
    }

    @GetMapping("/messageView")
    public ModelAndView messageView(String id) {
        return baseTemplates.editInfo(id,
                "message/messageEdit",
                i -> messageService.findById((String) i));

    }

    @GetMapping("/messageEdit")
    public ModelAndView messageEdit(String id) {
        return baseTemplates.editInfo(id,
                "message/messageEdit",
                i -> messageService.findById((String) i));
    }

    @PostMapping("/messageSave")
    public String messageSave(HttpServletRequest request, String id) {
        Message entity;
        try {
            if (Tools.notEmpty(id)) {
                entity = messageService.findById(id);
            } else {
                entity = new Message();
            }
            this.bindValidateRequestEntity(request, entity);
            if (Tools.isEmpty(entity.getId())) {
                entity.setId(Tools.getUUID());
                messageService.save(entity);
            } else {
                messageService.update(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return REDIRECT + "/manager/messageList";
    }

    @GetMapping("/messageDelete")
    public String messageDelete(String id) {
        if (Tools.notEmpty(id)) {
            try {
                messageService.deleteByid(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return REDIRECT + "/manager/messageList";
    }

}
