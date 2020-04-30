package com.ky.ykt.controller;

import com.ky.ykt.entity.PersonUploadEntity;
import com.ky.ykt.service.PersonUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 */
@Controller
@RequestMapping("/ky-ykt/weChat")
public class WeChatController {

    @Autowired
    PersonUploadService personUploadService;

    @RequestMapping(value = "/weChatSelect", method = RequestMethod.GET)
    public String weChatSelect(HttpServletRequest request, Model model) {
        Map map = new HashMap();
        String name = request.getParameter("name");
        String idCardNo = request.getParameter("idCardNo");
        String bankCardNo = request.getParameter("bankCardNo");
        if (name != null && !name.isEmpty() && idCardNo != null && !idCardNo.isEmpty() && bankCardNo != null && !bankCardNo.isEmpty()) {
            map.put("name", name);
            map.put("idCardNo", idCardNo);
            map.put("bankCardNo", bankCardNo);
            List<PersonUploadEntity> personUploadEntities = (List<PersonUploadEntity>) personUploadService.queryWechatList(map);
            if (personUploadEntities.isEmpty()) {
                return "wechatError";
            }
            model.addAttribute("wechatlist", personUploadEntities);
            return "wechatList";
        }
        return "wechatError";
    }

}
