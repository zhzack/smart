package xyz.blue.server.messageTool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.blue.pojo.Client;
import xyz.blue.server.SocketServer;

public class OnMessageTool implements StatusConstant {
    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);

    public static void MsgInfoCode(String message, Client client) {
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


    public static void message(String message, Client client) {
        logger.info("信息");
       String msg= String.valueOf(message.charAt(IDENTITYCODE));
        switch (msg){
            case DEVICE:

                break;
            case USER:
                break;
            default:
                break;
        }

    }

    public static void request(String message, Client client) {
        logger.info("请求");
    }

    public static void respond(String message, Client client) {
        logger.info("响应");
    }

    public static void contest(String message, Client client) {
        logger.info("心跳");
    }

    public static void error_msgInfoCode(String message, Client client) {
        logger.info("不规范的");
    }
}