package xyz.blue.server.messageTool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.blue.pojo.Client;
import xyz.blue.pojo.Msg;
import xyz.blue.server.SocketServer;
import xyz.blue.service.MsgService;

public class OnMessageTool implements StatusConstant {
    @Autowired
    MsgService msgService;
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
        if (message.length() > MSGBEGIN) {
            Msg msg = new Msg(message);
            SocketServer.sendMessage(msg.getMsg_text(), Integer.parseInt(msg.getMsg_receive_id()));
            logger.info(msg.toString());
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