package xyz.blue.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.blue.pojo.Device;
import xyz.blue.pojo.DeviceMsg;
import xyz.blue.pojo.User;
import xyz.blue.pojo.UserMsg;
import xyz.blue.server.SocketServer;
import xyz.blue.service.DeviceMsgService;
import xyz.blue.service.UserMsgService;
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
    @Autowired
    DeviceMsgService deviceMsgService;
    @Autowired
    UserMsgService userMsgService;

    NowDate nowdate = new NowDate();

    /**
     * 客户端页面
     */
    @RequestMapping(value = "/index")
    public String idnex() {
        return "index";
    }

    /**
     * 服务端页面
     */
    @RequestMapping(value = "/admin")
    public String admin() {
//        int num = SocketServer.getOnlineNum();
//        List<String> list = SocketServer.getOnlineUsers();

        //获取当前登录用户信息
        User user = this.getUser();
        try {
            Integer user_id = user.getUser_id();
            if (user_id > 0) {
                System.out.println(user_id + "登录");
            }
        } catch (NullPointerException e) {
            System.out.println(nowdate.nowDate() + "用户未登录");
            return "redirect:login";
        }

//        //查询当前登录用户拥有设备
//        List<Device> device = getDevice(user.getUser_id());
//        //在线设备
//        List<Device> deviceOnLines = getDeviceOnLines(device);


//        model.addAttribute("device", device);
//        //在线设备数量
//        model.addAttribute("num", deviceOnLines.size());
//        model.addAttribute("deviceOnLines", deviceOnLines);
        return "smart";
    }

    /**
     * 个人信息推送
     */
    @RequestMapping("sendmsg")
    @ResponseBody
    public String sendmsg(String msg, String client_id) {
        //第一个参数 :msg 发送的信息内容
        //第二个参数为用户长连接传的用户人数

        String[] persons = client_id.split(",");
        String[] clients_id = new String[persons.length];
        for (int i = 0; i < persons.length; i++) {
            clients_id[i] = persons[i];
//            userMsgService.insert_msg(new UserMsg(getUser().getUser_id(), clients_id[i], msg));
        }

        SocketServer.SendMany(msg, clients_id);
        return "success";
    }

    @RequestMapping("getDevicesByUserId")
    @ResponseBody
    public JSONObject getDevicesByUserId() {
        JSONObject object = new JSONObject();
        try {
            User user = getUser();
            List<Device> devices = getDevice(user.getUser_id());
            List<Device> devicesOnLine = getDeviceOnLines(devices);

            object.put("devices", devices);
            object.put("devicesOnLine", devicesOnLine);
        } catch (NullPointerException e) {
            object.put("server", "未查询到您的设备");
        }

        return object;
    }

    @RequestMapping("getOnlineDevicesByUserId")
    @ResponseBody
    public List<Device> getOnlineDevicesByUserId() throws NullPointerException {
        User user = getUser();
        List<Device> devices = getDevice(user.getUser_id());
        return getDeviceOnLines(devices);
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

    @RequestMapping("noParameter_device")
    @ResponseBody
    public void noParameter_device() {

    }

    /*
     * 这里的删除设备，并没有真正的把设备从表内删除，
     * 而是更改设备所属人。让当前登录账户不再拥有此设备
     * */
//    @RequestMapping("del_device")
//    @ResponseBody
//    public String del_device(int deviceId) {
//        int userId = getUser().getUser_id();
//        String s = "失败";
//        if (userId != 0) {
//            Device device = deviceService.query_deviceById(deviceId);
//            if (userId == device.getUser_id()) {
//
//                try {
//                    deviceService.update_deviceById(new Device(deviceId, 101101));
//                    s = "删除设备:" + deviceId + "成功";
//                    System.out.println(s);
//
//                } catch (Exception e) {
//                    s = "删除设备:" + deviceId + "失败";
//                    System.out.println(s);
//
//                }
//            } else {
//                s = "删除设备:" + deviceId + "失败，" + "当前用户没有此设备";
//                System.out.println(s);
//
//            }
//        }
//        return s;
//    }

    /*通过用户id查询发送的数据*/
    @GetMapping("/queryUserMsgByID")
    @ResponseBody
    public List<UserMsg> queryUserMsgByID() {
//        userMsgService.insert_msg(new UserMsg(101878, 4, "55"));
        return userMsgService.queryUserMsgByID(getUser().getUser_id());

    }

    /*通过查询device_msg表中的to_user_id查询*/
    @GetMapping("/queryDeviceMsgByID")
    @ResponseBody
    public List<DeviceMsg> queryDeviceMsgByID() {
//        deviceMsgService.insert_msg(new DeviceMsg(4, 101878, "58545"));
        return deviceMsgService.queryDeviceMsgByID(getUser().getUser_id());
    }

    @RequestMapping("UserSandMsg")
    @ResponseBody
    public int UserSandMsg(int deviceId, String msg) {
        return getUser().getUser_id();
    }

    /*
     * 设备发送信息
     * */
   /* @RequestMapping("devicesendmsg")
    @ResponseBody
    public String devicesendmsg(String msg, String client_id) {
        int User_id;
        try {
            User_id = deviceService.query_deviceById(Integer.parseInt(client_id)).getUser_id();
            String[] persons = client_id.split(",");

            int[] clients_id = new int[persons.length];
            for (int i = 0; i < persons.length; i++) {
                clients_id[i] = Integer.parseInt(persons[i]);
            }
            SocketServer.SendMany(msg, clients_id);
        } catch (NullPointerException e) {
            return "未注册从设备";
        }
        return User_id + "";
    }*/

//    @RequestMapping("DeviceSendMsg")
//    @ResponseBody
//    //DeviceId
//    public String devicesendmsg(int deviceId, String msg) {
//        int object = 0;
//
//        if (deviceId != 0) {
//
//            try {
//                object = deviceService.query_deviceById(deviceId).getUser_id();
//                try {
//                    DeviceMsg deviceMsg = new DeviceMsg(deviceId, object, msg);
//                    System.out.println(deviceMsg);
//                    deviceMsgService.insert_msg(deviceMsg);
//                } catch (NullPointerException e) {
//                    System.out.println(nowdate.nowDate() + "插入信息失败");
//                }
//            } catch (NullPointerException e) {
//                System.out.println(nowdate.nowDate() + "未注册设备登陆");
//            }
//            if (object == 0) {
//                return nowdate.nowDate() + "未绑定账户，请进入管理员页面添加";
//            } else {
//                return "" + object;
//            }
//        } else {
//            return "请输入设备id";
//        }
//    }

    @RequestMapping("smart")
    public String smart() {
        return "smart";
    }

    /**
     * 推送给所有在线用户
     */
    @RequestMapping("sendAll")
    @ResponseBody
    public String sendAll(String msg) {

        List<Integer> onLineDevice = new ArrayList<>();

        for (Device deviceOnLine : getDeviceOnLines(getDevice(getUser().getUser_id()))) {
            onLineDevice.add(deviceOnLine.getDevice_id());
        }
        Integer[] onLineDeviceS = onLineDevice.toArray(new Integer[0]);

        String[] clients_id = new String[onLineDeviceS.length];
        for (int i = 0; i < onLineDeviceS.length; i++) {
            clients_id[i] = String.valueOf(onLineDeviceS[i]);
        }

        SocketServer.SendMany(msg, clients_id);

        return "success";
    }

    private User getUser() {
        try {
            return (User) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
        } catch (NullPointerException e) {
            return new User("体验用户", "123");
        }
    }

    /*获取当前用户拥有的设备*/
    private List<Device> getDevice(Integer user_id) {

        try {
            return deviceService.queryDeviceListByUserID(user_id);
        } catch (NullPointerException e) {
            return new ArrayList<>();
        }

    }

    /*获取当前用户在线的设备*/
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
