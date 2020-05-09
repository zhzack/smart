package xyz.blue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ToolsController {

    @RequestMapping({"/pages/table.html", "/pages/table"})
    public String table() {
        return "/pages/tables/basic-table";
    }

    @RequestMapping({"/pages/elements.html", "/pages/elements"})
    public String elements() {
        return "/pages/forms/basic_elements";
    }


}
