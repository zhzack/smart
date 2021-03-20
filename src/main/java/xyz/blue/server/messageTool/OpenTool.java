package xyz.blue.server.messageTool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.blue.pojo.Client;
import xyz.blue.pojo.Device;
import xyz.blue.server.SocketServer;
import xyz.blue.service.impl.DeviceServiceImpl;

import java.util.List;

public class OpenTool implements StatusConstant {
    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);
    @Autowired
    DeviceServiceImpl deviceService;

    public void onopen(Client client) {
//        判断此链接是用户还是设备
        if (client.getIdentity() == DEVICE) {
            List<Device> deviceList = null;
            try {
                deviceList = deviceService.query_deviceById(Integer.parseInt(client.getClient_id()));
            } catch (Exception e) {
                logger.info(String.valueOf(e));
            }
//            查询设备列表，如果查询无结果，则向数据库插入
//            有结果则更新设备状态，并把设备列表设置给client对象
            try {
                if (deviceList == null) {
                    deviceService.insert_device(new Device(client.getClient_id(), true));
                } else {
                    deviceService.update_device_statusById(new Device(client.getClient_id(), true));
                }
                client.setDeviceList(deviceList);
            } catch (Exception e) {
                logger.info(String.valueOf(e));
            }


        } else if (client.getIdentity() == USER) {
            client.setDeviceList(deviceService.queryDeviceListByUserID(Integer.parseInt(client.getClient_id())));
        }

    }
}
