package xyz.blue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class ApiController {


    @RequestMapping("/pages/api")
    public String login(String username) {

        return "pages/samples/login-2";
    }


}
