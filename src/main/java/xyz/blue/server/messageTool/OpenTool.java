package xyz.blue.server.messageTool;

import com.alibaba.fastjson.JSONObject;
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

    String deviceId = null;
    String userId = null;
    Device device = new Device();

    public void onopen(Client client, MsgService msgService, DeviceService deviceService, UserService userService) {
        logger.info(client.toString());
//        判断此链接是用户还是设备
        if (client.getIdentity() == DEVICE) {
            try {
                device = deviceService.query_deviceById(Integer.parseInt(client.getClient_id()));
            } catch (Exception e) {
                logger.info(String.valueOf(e) + "查询设备失败");
            }
//            查询设备列表，如果查询无结果，则向数据库插入
//            有结果则更新设备状态，并把设备列表设置给client对象
            try {
                if (device == null) {
                    logger.info(client.getClient_id() + "无设备");
                    deviceService.insert_deviceLog(new Device(client.getClient_id(), true));
                } else {
                    logger.info(client.getClient_id() + "更新状态");
                    deviceService.update_device_statusById(new Device(client.getClient_id(), true));
                    logger.info("设备信息"+String.valueOf(deviceService.query_deviceById(Integer.parseInt(client.getClient_id()))));
                    client.getDevice().setDevice_status(true);
                    client.getDevice().setUser_id(deviceService.query_deviceById(Integer.parseInt(client.getClient_id())).getUser_id());

//                    查询设备归属人，更新归属人设备列表设备状态
                    Device device1 = deviceService.query_deviceById(Integer.parseInt(client.getClient_id()));
                    logger.info(String.valueOf(device1.getUser_id()));
                    SocketServer.socketServers.forEach(client1 -> {
                        logger.info(client1.getClient_id()+"asdasda");
                        if (client1.getIdentity().equals(USER) && client1.getClient_id().equals(String.valueOf(device1.getUser_id()))) {
                            client1.getDeviceList().forEach(device2 -> {
                                logger.info("设备id"+device2.getDevice_id());
                                logger.info("用户id"+client.getClient_id());
                                if (device2.getDevice_id().equals(client.getClient_id())){
                                    device2.setDevice_status(true);
                                    logger.info(String.valueOf(device2.getDevice_status()));
                                    logger.info(String.valueOf(device2.getUser_id()));
                                    SocketServer.sendMessage(JSONObject.toJSONString(device2), String.valueOf(device2.getUser_id()));
                                }
                            });
                        }
                    });
                    logger.info(SocketServer.socketServers.toString());
                }

                client.setDevice(device);
            } catch (Exception e) {
                logger.info(String.valueOf(e));
            }


        } else if (client.getIdentity() == USER) {
            try {
                List<Device> deviceList=deviceService.queryDeviceListByUserID(Integer.parseInt(client.getClient_id()));
                client.setDeviceList(deviceList);
            } catch (Exception e) {
                logger.info(String.valueOf(client.getDeviceList()));
            }
        }

    }
}
