package xyz.blue.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.blue.config.StatusConstant;
import xyz.blue.pojo.Client;

public class OnMessageTool implements StatusConstant {
    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);

    public static void onMessage(String message, Client client) {
        if (!message.isEmpty()) {
            //判断信息信息识别码位置
            if (message.length() >= ENDINFOCODE) {
                String msgInfoCode = message.substring(BEGININFOCODE, ENDINFOCODE);

                switch (msgInfoCode) {
                    case MESSAGE:
                        logger.info("信息");
                        message(message, client);
                        break;
                    case REQUEST:
                        logger.info("请求");
                        request(message, client);
                        break;
                    case RESPOND:
                        logger.info("响应");
                        respond(message, client);
                        break;
                    case CONTEST:
                        logger.info("心跳");
                        contest(message, client);
                        break;
                    default:
                        logger.info("不规范的");
                        error_msgInfoCode(message, client);
                        break;
                }
            }
        }
    }


    public static void message(String message, Client client) {

    }

    public static void request(String message, Client client) {

    }

    public static void respond(String message, Client client) {

    }

    public static void contest(String message, Client client) {

    }

    public static void error_msgInfoCode(String message, Client client) {

    }
}