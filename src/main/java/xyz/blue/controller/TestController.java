package xyz.blue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.blue.pojo.Device;
import xyz.blue.service.impl.DeviceServiceImpl;
import xyz.blue.service.impl.UserServiceImpl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Controller

public class TestController {


    @Autowired
    UserServiceImpl userService;

    @Autowired
    DeviceServiceImpl deviceService;

    @GetMapping("/t")
    @ResponseBody
    public List<Device> get_device() {
        List<Device> d = deviceService.queryDeviceListByUserID(0);
        return d;
    }

    @GetMapping("/get_ws_ip")
    @ResponseBody
    public void get_ws_ip() throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println("Local HostAddress: " + addr.getHostAddress());

        System.out.println(InetAddress.getLocalHost().getHostAddress());
        String hostname = addr.getHostName();
        System.out.println("Local host name: " + hostname);
    }


}
