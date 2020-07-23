package com.zzp.travel.stage.web.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 注意
 * <p>
 *  //TODO
 *  AttentionController.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/22 20:29
 * @see  AttentionController
 **/
@Controller
public class AttentionController {
    @RequestMapping("/attention")
    public String attention(){
        return "portal/attention";
    }
}
