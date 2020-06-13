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
import xyz.blue.tools.NowDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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

    NowDate nowdate = new NowDate();

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
//        int num = SocketServer.getOnlineNum();
//        List<String> list = SocketServer.getOnlineUsers();

        //获取当前登录用户信息
        User user = this.getUser();
        try {
            Integer user_id = user.getUser_id();
        } catch (NullPointerException e) {
            System.out.println(nowdate.nowDate() + "用户未登录");
            return "redirect:login";
        }

        //查询当前登录用户拥有设备
        List<Device> device = getDevice(user.getUser_id());
        //在线设备
        List<Device> deviceOnLines = getDeviceOnLines(device);


        model.addAttribute("device", device);
        //在线设备数量
        model.addAttribute("num", deviceOnLines.size());
        model.addAttribute("deviceOnLines", deviceOnLines);
        return "smart";
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


    @RequestMapping("devicesendmsg")
    @ResponseBody
    public String devicesendmsg(String msg, String client_id) {
        int User_id;
        try {
            User_id = deviceService.query_deviceById(Integer.parseInt(client_id)).getUser_id();
            String[] persons = client_id.split(",");
            SocketServer.SendMany(msg, persons);
        } catch (NullPointerException e) {
            return "未注册从设备";
        }
        return User_id+"";
    }


    @RequestMapping("addDevice")
    @ResponseBody
    //DeviceId
    public String addDevice(String DeviceName, String DeviceMac) {

        if (DeviceMac != null && DeviceName != null) {
            Device device = new Device(DeviceName, DeviceMac, getUser().getUser_id());
            deviceService.insert_device(device);
            return "success";
        } else {
            return "fail";
        }

    }

    @RequestMapping("queryUserId")
    @ResponseBody
    //DeviceId
    public String queryUserId(int deviceId) {
        Object object = null;

        if (deviceId != 0) {

            try {
                object = deviceService.query_deviceById(deviceId).getUser_id();
            } catch (NullPointerException e) {
                //e.printStackTrace();
                System.out.println(nowdate.nowDate() + "未注册设备登陆");
            }

            if (object == null) {

                return nowdate.nowDate() + "未绑定账户，请进入管理员页面添加";
            } else {
                return object.toString();
            }

        } else {
            return "请输入设备id";
        }


    }

    @RequestMapping("smart")
    public String smart(Model model) {
        return "smart";
    }

    /**
     * 推送给所有在线用户
     *
     * @return
     */
    @RequestMapping("sendAll")
    @ResponseBody
    public String sendAll(String msg) {

        List<String> onLineDevice = new ArrayList<>();

        for (Device deviceOnLine : getDeviceOnLines(getDevice(getUser().getUser_id()))) {
            onLineDevice.add(deviceOnLine.getDevice_id());
        }
        String[] onLineDeviceS = onLineDevice.toArray(new String[0]);
        SocketServer.SendMany(msg, onLineDeviceS);

        return "success";
    }

    private User getUser() {
        return (User) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
    }

    private List<Device> getDevice(Integer user_id) {

        return deviceService.queryDeviceListByUserID(user_id);
    }

    private List<Device> getDeviceOnLines(List<Device> device) {
        //在线设备
        List<Device> deviceOnLines = new ArrayList<>();

        SocketServer.socketServers.forEach(client -> {
            for (Device deviceOnline : device) {
                if (Objects.equals(client.getClient_id(), deviceOnline.getDevice_id())) {
                    deviceOnLines.add(deviceOnline);
                }
            }
        });
        return deviceOnLines;
    }

}
