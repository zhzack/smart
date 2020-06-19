package xyz.blue.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.blue.pojo.Device;
import xyz.blue.pojo.DeviceMsg;
import xyz.blue.pojo.User;
import xyz.blue.service.DeviceMsgService;
import xyz.blue.service.impl.DeviceServiceImpl;
import xyz.blue.service.impl.UserServiceImpl;

import java.util.List;

@Controller

public class DashboardController {


    @Autowired
    UserServiceImpl userService;
    @Autowired
    DeviceServiceImpl deviceService;
    @Autowired
    DeviceMsgService deviceMsgService;


    @GetMapping("/que")
    @ResponseBody
    public List<User> lis() {

        List<User> usersList = userService.queryUserList();
        System.out.println(usersList.toString());
        return usersList;
    }

    @GetMapping("/insert")
    @ResponseBody
    public String insert() {

        User user = new User("test", "qwe");
//        int i = userService.addUser(user);
        System.out.println(userService.addUser(user) + "66666");
        return userService.addUser(user) + "66666";
    }

    @GetMapping("/j")
    @ResponseBody
    public String string() {
        //从字符串解析JSON对象
        JSONObject obj = JSON.parseObject("{\"runoob\":\"菜鸟教程\"}");
        //从字符串解析JSON数组
        JSONArray arr = JSON.parseArray("[\"菜鸟教程\",\"RUNOOB\"]\n");

        //将JSON对象转化为字符串
        String objStr = JSON.toJSONString(obj);
        //将JSON数组转化为字符串
        String arrStr = JSON.toJSONString(arr);
        test_sql();
        return "<h1>" + obj.get("runoob.") + "</h1>" + "<h1>" + arr.get(0)+ "</h1>" + "<h1>" + arrStr + "</h1>";
    }

    @GetMapping("/t")
    @ResponseBody
    public String test_sql() {
        deviceMsgService.insert_msg(new DeviceMsg(4, 101878, "13"));
        return "";
//        List<Device> devices = deviceService.queryDeviceList();
//
//        Device device = devices.get(3);
//
////        deviceService.del_deviceById(3);
//
//        device.setDevice_name("test111");
//        device.setUser_id(101879);
//        deviceService.insert_device(device);
//
//        device.setDevice_name("test222");
////        device.setDevice_id(null);
//        device.setUser_id(101879);
//        //deviceService.update_deviceById(device);
//
//        List<Device> devicesS = deviceService.queryDeviceList();
//
//        ArrayList devicesSS = new ArrayList();
//
//        for (Device devic : devices) {
//
//            devicesSS.add("<h1>" + devic + "\n\n\n\n\n</h1>");
//        }
//        return devicesSS.toString();
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
        return "pages/samples/login-2";
    }

    @GetMapping("/device")
    @ResponseBody
    public List<Device> devices_list() {

        List<Device> device = deviceService.queryDeviceList();
        device.addAll(deviceService.queryDeviceListByUserID(101878));
        System.out.println(device.toString());
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
    @ResponseBody
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
        }
    }

    @GetMapping("/insertUser")
    @ResponseBody
    public void insertUser() {

    }

    @RequestMapping("register-2.html")
    public String register() {
        return "pages/samples/register-2";
    }

}
