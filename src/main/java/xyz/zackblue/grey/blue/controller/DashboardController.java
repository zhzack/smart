package xyz.zackblue.grey.blue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class DashboardController {

    @RequestMapping("dashboard.html")
    public String list(Model model) {
        //Collection<Users> usersCollection = usersDao.getUsersCollection();
        model.addAttribute("Emps");

        return "/index";

    }

}
