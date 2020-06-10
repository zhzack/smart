package xyz.blue.server;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.blue.service.impl.DeviceServiceImpl;

public class device_Log {

    @Autowired
    DeviceServiceImpl deviceService;

    public void device_onopen(int client_id) {

//        Device device = new Device(client_id, true);
        //deviceService.updateDeviceStatus(device);

    }
}
