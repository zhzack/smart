package xyz.zackblue.grey.blue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.zackblue.grey.blue.dao.Users_dao;
import xyz.zackblue.grey.blue.pojo.Users;

import java.util.Collection;

@Controller
@RequestMapping("table.html")
public class EmpController {

    Users_dao usersDao;


    public String list(Model model) {
        Collection<Users> usersCollection = usersDao.getUsersCollection();
        model.addAttribute("emps", usersCollection);
        return "table.html";
    }

}
