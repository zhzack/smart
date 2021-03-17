package xyz.blue.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.blue.pojo.Device;
import xyz.blue.pojo.User;
import xyz.blue.server.SocketServer;
import xyz.blue.service.impl.DeviceServiceImpl;
import xyz.blue.service.impl.UserServiceImpl;

import java.util.ArrayList;

@Controller
public class DashboardController {

    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);
    @Autowired
    UserServiceImpl userService;
    @Autowired
    DeviceServiceImpl deviceService;


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
        return "pages/samples/login-2";
    }

    @GetMapping("/device")
    @ResponseBody
    public ArrayList<Device> devices_list() {
        ArrayList<Device> device = new ArrayList<>(deviceService.queryDeviceListByUserID(1));
        System.out.println(device);
        return device;
    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        try {//登录成功
            subject.login(usernamePasswordToken);
            return "redirect:admin";
        } catch (UnknownAccountException e) {//用户名不存在

            model.addAttribute("msg", "用户名不存在");

            return "pages/samples/login-2";
        } catch (IncorrectCredentialsException b) {//密码错误
            model.addAttribute("msg", "密码错误");

            return "pages/samples/login-2";
        }
    }

    @GetMapping("/logout")
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
        }
    }

    @RequestMapping("/insertUser")
//    @ResponseBody
    public String insertUser(String username, String password, Model model) {

        if (userService.selectByName(username) == null) {
            int i = userService.insert(new User(username, password));
            if (i >= 1) {
                logger.info("注册成功，请登录");
                return "pages/samples/login-2";
            } else {
                model.addAttribute("msg", "注册失败");
                logger.info("注册失败1");
                return "pages/samples/register-2";
            }

        } else {
            model.addAttribute("msg", "用户名已存在");
            logger.info("用户名已存在");
            return "pages/samples/register-2";
        }


        //            model.addAttribute("msg", "注册失败");

        //return "redirect:admin";
    }

    @RequestMapping("register")
    public String register() {
        return "pages/samples/register-2";
    }

}
