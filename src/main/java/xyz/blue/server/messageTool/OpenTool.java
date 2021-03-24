package xyz.blue.server.messageTool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.blue.pojo.Client;
import xyz.blue.pojo.Device;
import xyz.blue.server.SocketServer;
import xyz.blue.service.DeviceService;
import xyz.blue.service.MsgService;
import xyz.blue.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OpenTool implements StatusConstant {
    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);

    String deviceId=null;
    String userId=null;
    Device  device=new Device();
    public void onopen(Client client, MsgService msgService, DeviceService deviceService, UserService userService) {
        logger.info(client.toString());
//        判断此链接是用户还是设备
        if (client.getIdentity() == DEVICE) {
            try {
                device = deviceService.query_deviceById(Integer.parseInt(client.getClient_id()));
            } catch (Exception e) {
                logger.info(String.valueOf(e)+"查询设备失败");
            }
//            查询设备列表，如果查询无结果，则向数据库插入
//            有结果则更新设备状态，并把设备列表设置给client对象
            try {
                if (device==null) {
                    logger.info(client.getClient_id() + "无设备");
                    deviceService.insert_deviceLog(new Device(client.getClient_id(), true));
                } else {
                    logger.info(client.getClient_id() + "更新状态");
                    deviceService.update_device_statusById(new Device(client.getClient_id(), true));
                }

                client.setDevice(device);
            } catch (Exception e) {
                logger.info(String.valueOf(e));
            }


        } else if (client.getIdentity() == USER) {
            try {
                client.setDeviceList(deviceService.queryDeviceListByUserID(Integer.parseInt(client.getClient_id())));
            } catch (Exception e) {
                logger.info(String.valueOf(client.getDeviceList()));
            }
        }

    }
}
