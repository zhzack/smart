package xyz.blue.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.blue.pojo.User;
import xyz.blue.service.impl.UserServiceImpl;

import java.util.List;

@Controller

public class DashboardController {


    public DashboardController(UserServiceImpl userService) {
        this.userService = userService;
    }


    final
    UserServiceImpl userService;

    @GetMapping("/que")
    @ResponseBody
    public List<User> lis() {

        List<User> usersList = userService.queryUserList();
        System.out.println(usersList.toString());
        return usersList;
    }

    @GetMapping("/quer")
    @ResponseBody
    public User list() {

        User users = userService.queryUserByName("22");
        System.out.println(users.toString());
        return users;
    }

    @GetMapping("/noach")
    @ResponseBody
    public String noach() {

        return "未经授权不能访问";
    }

    @RequestMapping({"index", "index.html"})
    public String index() {
        return "index";
    }
    //        registry.addViewController("/login.html").setViewName("/pages/samples/login-2");
    //        registry.addViewController("/").setViewName("/pages/samples/login-2");
    @RequestMapping({"/", "login.html"})
    public String toLogin() {
        return "/pages/samples/login-2";
    }


    @RequestMapping("/login")
    public String login(String username, String password, Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        try {//登录成功
            subject.login(usernamePasswordToken);

            return "redirect:/dashborad.html";
        } catch (UnknownAccountException e) {//用户名不存在

            model.addAttribute("msg", "用户名不存在");

            return "/pages/samples/login-2";
        } catch (IncorrectCredentialsException b) {//密码错误
            model.addAttribute("msg", "密码错误");

            return "/pages/samples/login-2";
        }


    }
}
