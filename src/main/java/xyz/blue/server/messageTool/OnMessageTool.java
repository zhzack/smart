package xyz.blue.server.messageTool;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.blue.pojo.Client;
import xyz.blue.pojo.Device;
import xyz.blue.pojo.Msg;
import xyz.blue.server.SocketServer;
import xyz.blue.service.DeviceService;
import xyz.blue.service.MsgService;
import xyz.blue.service.UserService;

import static java.lang.Integer.parseInt;


public class OnMessageTool implements StatusConstant {


    MsgService msgService;

    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);


    public void MsgInfoCode(String message, Client client, MsgService msgService, DeviceService deviceService, UserService userService) {
        if (this.msgService == null) {
            this.msgService = msgService;
        }
        if (!message.isEmpty()) {
            //判断信息信息识别码位置
            if (message.length() >= ENDINFOCODE) {
                String msgInfoCode = message.substring(BEGININFOCODE, ENDINFOCODE);
                switch (msgInfoCode) {
                    case MESSAGE:

                        message(message, client);
                        break;
                    case REQUEST:

                        request(message, client);
                        break;
                    case RESPOND:

                        respond(message, client);
                        break;
                    case CONTEST:

                        contest(message, client);
                        break;
                    default:

                        error_msgInfoCode(message, client);
                        break;
                }
            }
        }
    }


    public void message(String message, Client client) {

        if (message.length() > MSGBEGIN) {
            Msg msg = new Msg(message);
            if (msg.getMsg_sent_id().equals(client.getClient_id())) {
                try {
                    SocketServer.sendMessage(msg.getMsg_text(), msg.getMsg_receive_id());
                    if (msgService != null) {
                        msgService.insert(msg);
                    } else {
                        logger.info("null");
                    }
                } catch (Exception e) {
                    logger.info(String.valueOf(e));
                    logger.info("接收者id不符");
                }
            } else {
                logger.info("接收者" + msg.getMsg_sent_id());
                logger.info("连接者client" + client.getClient_id());
                logger.info("连接者msg" + msg.getMsg_sent_id());
                logger.info("连接者id不符");
            }
            logger.info(msg.toString());
        }
    }

    public static void request(String message, Client client) {


        switch (message.substring(BEGINREQUESTCODE, ENDREQUESTCODE)
        ) {
            case DEVICELIST:
                JSONObject deviceList=new JSONObject();
                deviceList.put("info","01");
                deviceList.put("deviceList",client.getDeviceList());
                SocketServer.sendMessage(String.valueOf(deviceList), client.getClient_id());

//                SocketServer.sendMessage("01" + JSONObject.toJSONString(client.getDeviceList()), client.getClient_id());
//                logger.info(JSONObject.toJSONString(client.getDeviceList()));
                logger.info("用户id为" + client.getClient_id() + "请求设备列表");
                break;
            case USERID:
                SocketServer.sendMessage(client.getDevice().getUser_id() + "", client.getClient_id());
                logger.info(client.getDevice().getUser_id() + "");
                logger.info("设备id为" + client.getClient_id() + "请求所属用户");
                break;
            case TEMPERATURE:
                logger.info("设备"+client.getClient_id()+"向"+client.getDevice().getUser_id()+"发送温度");
                JSONObject temperature=new JSONObject();
                temperature.put("temperatureHumidityCode",TEMPERATURE);
                temperature.put("data",message.substring(ENDREQUESTCODE));
                temperature.put("device_id",parseInt(client.getClient_id()));
                temperature.put("info","02");
                logger.info(String.valueOf(temperature));
                SocketServer.sendMessage(String.valueOf(temperature), String.valueOf(client.getDevice().getUser_id()));
                break;
            case HUMIDITY:
                logger.info("设备"+client.getClient_id()+"向"+client.getDevice().getUser_id()+"发送湿度");
                JSONObject humidity=new JSONObject();
                humidity.put("temperatureHumidityCode",HUMIDITY);
                humidity.put("data",message.substring(ENDREQUESTCODE));
                humidity.put("device_id",parseInt(client.getClient_id()));
                humidity.put("info","02");
                logger.info(String.valueOf(humidity));
                SocketServer.sendMessage(String.valueOf(humidity), String.valueOf(client.getDevice().getUser_id()));
                break;

            default:
                logger.info(client.getClient_id() + ":请求有误");
                break;

        }
    }

    public static void respond(String message, Client client) {
        switch (message.substring(BEGINRESPONDCODE, ENDRESPONDCODE)
        ) {
            case TEMPERATURE:

                break;

            case HUMIDITY:

                break;

        }


        logger.info("响应");
    }

    public static void contest(String message, Client client) {
        logger.info(client.getClient_id()+"心跳");
    }

    public static void error_msgInfoCode(String message, Client client) {
        logger.info(message);
        logger.info("不规范的");

    }
}