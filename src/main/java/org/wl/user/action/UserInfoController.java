package org.wl.user.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wl.user.entity.UserInfo;
import org.wl.user.service.UserInfoService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value="list")
    public String list(Model model){
        List<UserInfo> list = userInfoService.selectAll();
        model.addAttribute("list",list);
        return "user/list.jsp";
    }
}
