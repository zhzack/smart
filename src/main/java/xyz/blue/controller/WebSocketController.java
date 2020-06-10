package xyz.blue.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.blue.pojo.Device;
import xyz.blue.pojo.User;
import xyz.blue.server.SocketServer;
import xyz.blue.service.impl.DeviceServiceImpl;
import xyz.blue.service.impl.UserServiceImpl;

import java.util.List;




/**
 * websocket
 * 消息推送(个人和广播)
 */
@Controller
public class WebSocketController {

    @Autowired
    private SocketServer socketServer;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    DeviceServiceImpl deviceService;

    /**
     * 客户端页面
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String idnex() {

        return "index";
    }

    /**
     * 服务端页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin")
    public String admin(Model model) {
        int num = SocketServer.getOnlineNum();
        List<String> list = SocketServer.getOnlineUsers();
        model.addAttribute("num", num);
        User user= (User) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
        List<Device> device=deviceService.queryDeviceListByUserID(user.getUser_id());
        model.addAttribute("device", device);
        return "admin";
    }

    /**
     * 个人信息推送
     *
     * @return
     */
    @RequestMapping("sendmsg")
    @ResponseBody
    public String sendmsg(String msg, String client_id) {
        //第一个参数 :msg 发送的信息内容
        //第二个参数为用户长连接传的用户人数
        String[] persons = client_id.split(",");
        SocketServer.SendMany(msg, persons);
        return "success";
    }

    /**
     * 推送给所有在线用户
     *
     * @return
     */
    @RequestMapping("sendAll")
    @ResponseBody
    public String sendAll(String msg) {
        SocketServer.sendAll(msg);
        return "success";
    }
}
