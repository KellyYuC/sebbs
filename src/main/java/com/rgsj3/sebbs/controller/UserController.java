package com.rgsj3.sebbs.controller;

import com.rgsj3.sebbs.domain.Result;
import com.rgsj3.sebbs.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("/login")
    public Result login(@RequestParam("name") String name,
                        @RequestParam("password") String password,
                        HttpServletRequest httpServletRequest) {
        return userService.login(name, password, httpServletRequest);
    }

    @RequestMapping("/logout")
    public Result logout(HttpServletRequest httpServletRequest) {
        return userService.logout(httpServletRequest);
    }
    
     @RequestMapping("/modify")
    public Result modifyUserInfo(@RequestBody Map<String,String> map,
                                 HttpServletRequest httpServletRequest){
        String userName = map.get("userName");
        String userEmail = map.get("userEmail");
        String userPassword = map.get("userPassword");
        String userPassword2 = map.get("userPassword2");

        if(userName.length() == 0)
            return Result.error(1,"姓名为空");
        else if(userEmail.length() == 0)
            return Result.error(2,"email为空");
        else if (!userEmail.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"))
            return Result.error(2,"email错误");
        else if((userPassword.length()!=0 || userPassword2.length() !=0) && !userPassword.equals(userPassword2))
            return Result.error(3,"密码不同");
        return userService.modifyInfo(userName, userEmail, userPassword,httpServletRequest);
    }

}
