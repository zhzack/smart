package xyz.zackblue.grey.blue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class EmpController {

   // Users_dao usersDao;

    @RequestMapping("table.html")
    public String list(Model model) {
        //Collection<Users> usersCollection = usersDao.getUsersCollection();
        model.addAttribute("Emps");
        return "/pages/tables/basic-table";
//        return "/pages/samples/login-2";

    }

}
