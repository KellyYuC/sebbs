package com.rgsj3.sebbs.controller;

import com.rgsj3.sebbs.repository.*;
import com.rgsj3.sebbs.service.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Resource
    UserService userService;

    @Resource
    ZoneService zoneService;

    @Resource
    TopicService topicService;

    @Resource
    ReplyService replyService;

    @Resource
    FileService fileService;

    @RequestMapping("/home")
    public String home(Model model,
                       HttpServletRequest httpServletRequest) {
        userService.loginUser(model, httpServletRequest);
        zoneService.listZone(model);
        return "home";
    }

    @RequestMapping("/board/{id}")
    public String board(Model model,
                        HttpServletRequest httpServletRequest,
                        @PathVariable("id") Integer id,
                        @RequestParam(value = "start", defaultValue = "0") Integer start) {
        userService.loginUser(model, httpServletRequest);
        topicService.listTopic(model, id, start);
        return "board";
    }

    @RequestMapping("/topic/{id}")
    public String topic(Model model,
                        HttpServletRequest httpServletRequest,
                        @PathVariable("id") Integer id,
                        @RequestParam(value = "start", defaultValue = "0") Integer start) {
        userService.loginUser(model, httpServletRequest);
        replyService.listReply(model, id, start);
        return "topic";
    }

    @RequestMapping("/self")
    public String self(Model model, HttpServletRequest httpServletRequest) {
        userService.loginUser(model, httpServletRequest);

        return "self";
    }

    @RequestMapping("/download")
    public String download(Model model, HttpServletRequest httpServletRequest){
        userService.loginUser(model, httpServletRequest);
        fileService.listFile(model);
        return "/download";
    }
   @RequestMapping("/course")
    public String course(Model model, HttpServletRequest httpServletRequest) {
        userService.loginUser(model, httpServletRequest);
        if (httpServletRequest.getSession().getAttribute("user") == null) {
            return "redirect:/home";
        }
        userService.courseManagement(model,httpServletRequest);
        return "course";
    }

}
