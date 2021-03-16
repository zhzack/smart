package xyz.blue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class ApiController {


    @RequestMapping("/pages/api")
    public String login(String username) {

        return "pages/samples/login-2";
    }


    @PostMapping("/hello")
    public String hello(@RequestParam("deviceInfo") String name) {
        return "name：" + name;
    }

}
