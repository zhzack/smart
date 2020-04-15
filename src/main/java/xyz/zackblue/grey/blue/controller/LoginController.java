package xyz.zackblue.grey.blue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController {
    @RequestMapping("/main")
    //@ResponseBody
    /*
     * @Responsebody注解表示该方法的返回的结果直接写入 HTTP 响应正文中，一般在异步获取数据时使用；
     * 在使用@RequestMapping后，返回值通常解析为跳转路径，加上@Responsebody后返回结果不会被解析为跳转路径，而是直接写入HTTP 响应正文中。例如，异步获取json数据，加上@Responsebody注解后，就会直接返回json数据。
     * @RequestBody注解则是将 HTTP 求正文插入方法中，使用适合的HttpMessageConverter将请求体写入某个对象。
     *
     * */
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model, HttpSession session
            ) {
//        @RequestParam("KeepSigned") String KeepSigned,
//        System.out.println("username is:"+username);
//        System.out.println("password is:"+password);
//        System.out.println("KeepSigned is:"+KeepSigned);
        if ("1".equals(password)) {
            session.setAttribute("loginUser",username);
            return "redirect:/index.html";
        } else {
            model.addAttribute("msg", "用户名或者密码错误");
            return "redirect:/";
        }
        //return "ok";
    }
}
