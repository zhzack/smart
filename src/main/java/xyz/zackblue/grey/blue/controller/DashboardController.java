package xyz.zackblue.grey.blue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.zackblue.grey.blue.dao.Users_dao;
import xyz.zackblue.grey.blue.pojo.Users;

import java.util.Collection;

@Controller

public class DashboardController {

    @RequestMapping("dashboard.html")
    public String list(Model model) {
        //Collection<Users> usersCollection = usersDao.getUsersCollection();
        Collection<Users> users= Users_dao.getUsersCollection();
        model.addAttribute("users",users);

        return "/index";

    }

}
