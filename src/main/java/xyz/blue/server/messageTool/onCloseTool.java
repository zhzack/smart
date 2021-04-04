package xyz.blue.server.messageTool;/**
 * @author Blue
 * @date 2021-03-25 19:12
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.blue.pojo.Client;
import xyz.blue.pojo.Device;
import xyz.blue.server.SocketServer;
import xyz.blue.service.DeviceService;
import xyz.blue.service.UserService;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @description:
 * @author: Blue
 * @time: 2021/3/25 19:12
 */

public class onCloseTool implements StatusConstant {
    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);

    Device device = new Device();

    public void onClose(Client client, DeviceService deviceService, UserService userService, CopyOnWriteArraySet<Client> socketServers) {
        //        判断此链接是用户还是设备
        if (client.getIdentity() == DEVICE) {

            deviceService.update_device_statusById(new Device(client.getClient_id(), false));
            socketServers.forEach(client1 -> {
               if (client1.getClient_id().equals(String.valueOf(client.getDevice().getUser_id()))){
                   client1.getDeviceList().forEach(device1 -> {
                       if (device1.getDevice_id().equals(client.getClient_id())){
                           device1.setDevice_status(false);
                       }
                   });
               }
            });
        } else if (client.getIdentity() == USER) {
            try {
                client.setDeviceList(deviceService.queryDeviceListByUserID(Integer.parseInt(client.getClient_id())));
            } catch (Exception e) {
                logger.info(String.valueOf(client.getDeviceList()));
            }
        }
    }
}
