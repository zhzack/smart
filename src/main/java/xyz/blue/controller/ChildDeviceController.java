package xyz.blue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.blue.pojo.ChildDevice;
import xyz.blue.service.ChildDeviceService;
import xyz.blue.service.DeviceMsgService;
import xyz.blue.service.impl.DeviceServiceImpl;
import xyz.blue.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Controller

public class ChildDeviceController {


    @Autowired
    UserServiceImpl userService;
    @Autowired
    DeviceServiceImpl deviceService;
    @Autowired
    DeviceMsgService deviceMsgService;
    @Autowired
    ChildDeviceService childDeviceService;

    /*
     * q_:查询
     * cd_:子设备
     * by:通过
     * Id:设备号
     * */
    @GetMapping("/q_cd_byId")
    @ResponseBody
    public List<ChildDevice> q_cd_byId(@RequestParam(value = "device_id") int device_id) {
        //JSONObject
        System.out.println(device_id);
        if (device_id != 0) {
            try {
                return childDeviceService.query_cdBydId(device_id);
            } catch (NullPointerException e) {
                System.out.println("查询子设备失败");
                return null;
            }
        } else {
            return null;
        }

    }

    @GetMapping("/getData")
    @ResponseBody
    public List<Double> getData(int device_id) {
        //JSONObject
        if (device_id != 0) {
            List<Double> data = new ArrayList<>();
            data.add((Math.random() * 100) % 30);
            data.add((Math.random() * 100) % 40);
            data.add((Math.random() * 100) % 20);
            return data;
        } else {
            return null;
        }
    }


}
