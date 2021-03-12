package xyz.blue.server;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.blue.config.StatusConstant;
import xyz.blue.pojo.Client;
import xyz.blue.pojo.User;
import xyz.blue.service.impl.DeviceServiceImpl;
import xyz.blue.service.impl.UserServiceImpl;
import xyz.blue.tools.NowDate;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

@ServerEndpoint(value = "/ws/{userName}")
@Component
public class SocketServer implements StatusConstant {

    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);

    @Autowired
    DeviceServiceImpl deviceService;
    @Autowired
    private SocketServer socketServer;
    @Autowired
    UserServiceImpl userService;


    /**
     * 用线程安全的CopyOnWriteArraySet来存放客户端连接的信息
     */
    public static final CopyOnWriteArraySet<Client> socketServers = new CopyOnWriteArraySet<>();

    /**
     * websocket封装的session,信息推送，就是通过它来信息推送
     */

    private Session session;

    NowDate nowdate = new NowDate();
//    /**
//     * 服务端的userName,因为用的是set，每个客户端的username必须不一样，否则会被覆盖。
//     * 要想完成ui界面聊天的功能，服务端也需要作为客户端来接收后台推送用户发送的信息
//     */

    /**
     * 用户连接时触发，我们将其添加到
     * 保存客户端连接信息的socketServers中
     * <p>
     * session
     * userName
     */
    @OnOpen
    public void open(Session session, @PathParam(value = "userName") Integer client_id) {

        this.session = session;
        boolean noDevice = true;
        for (Client client : socketServers) {
            if (Objects.equals(client.getClient_id(), client_id)) {
                noDevice = false;
                onClose();
                break;
            }
        }
        if (noDevice) {
            socketServers.add(new Client(client_id, session));

            logger.info(nowdate.nowDate() + "客户端:【{}】连接成功", client_id);

        }


    }

    public synchronized static void sendAll() {
        if (!socketServers.isEmpty()) {
            //群发，不能发送给服务端自己
            socketServers.stream().filter(cli -> !cli.getClient_id().equals(123))
                    .forEach(client -> {
                        try {
                            client.getSession().getBasicRemote().sendText("ok");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            logger.info("服务端推送给所有客户端,心跳");
        } else {
            logger.info("现在没有设备连接");
        }
    }


    /**
     * 连接关闭触发，通过sessionId来移除
     * socketServers中客户端连接信息
     */
    @OnClose
    public void onClose() {
        socketServers.forEach(client -> {
            if (client.getSession().getId().equals(session.getId())) {

                logger.info("客户端:【{}】断开连接", client.getClient_id());

//                System.out.println(socketServers.stream().map(Client::getClient_id));

                socketServers.remove(client);

            }
        });
    }

    /**
     * 信息群发，我们要排除服务端自己不接收到推送信息
     * 所以我们在发送的时候将服务端排除掉
     * message
     *
     */
    public synchronized static void sendAll(Map<String, Object> message) {
        if (!socketServers.isEmpty()) {
            //群发，不能发送给服务端自己
            socketServers.stream().filter(cli -> !cli.getClient_id().equals(123))
                    .forEach(client -> {
                        try {
                            client.getSession().getBasicRemote().sendText(message.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            logger.info("服务端推送给所有客户端 :【{}】", message);
        } else {
            logger.info("现在没有设备连接");
        }

    }

    /**
     * 信息发送的方法，通过客户端的userName
     * 拿到其对应的session，调用信息推送的方法
     * message
     * client_id
     */
    public synchronized static void sendMessage(String message, int client_id) {

        socketServers.forEach(client -> {
            if ((client.getClient_id()) == client_id) {
                try {
                    client.getSession().getBasicRemote().sendText(message + "");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 获取服务端当前客户端的连接数量，
     * 因为服务端本身也作为客户端接受信息，
     * 所以连接总数还要减去服务端
     * 本身的一个连接数
     * <p>
     * 这里运用三元运算符是因为客户端第一次在加载的时候
     * 客户端本身也没有进行连接，-1 就会出现总数为-1的情况，
     * 这里主要就是为了避免出现连接数为-1的情况
     */
    public synchronized static int getOnlineNum() {
        return (int) socketServers.stream().filter(client -> !client.getClient_id().equals(123)).count();
    }

    /**
     * 获取在线用户名，前端界面需要用到
     *
     */
    public synchronized static List<Integer> getOnlineUsers() {

        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("loginUser");

        return socketServers.stream()
                .map(Client::getClient_id)
                .collect(Collectors.toList());
    }

    /**
     * 收到客户端发送信息时触发
     * 我们将其推送给客户端(blue)
     * 其实也就是服务端本身，为了达到前端聊天效果才这么做的
     * <p>
     * message
     */
    @OnMessage
    public void onMessage(String message) {
        Client client = socketServers.stream().filter(cli -> cli.getSession() == session)
                .collect(Collectors.toList()).get(0);

        OnMessageTool.onMessage(message, client);
//        DeviceMsg deviceMsg = JsonToString.ToDeviceMsg(message);
//        logger.info(message);
//        if (deviceMsg != null) {
////            sendMessage(client.getClient_id() + "<--" + deviceMsg.getMsg(), deviceMsg.getTo_user_id());
//            sendMessage(message, deviceMsg.getTo_user_id());
//
//            logger.info("客户端:【{}】向客户端【{}】发送信息:{}", client.getClient_id(), deviceMsg.getTo_user_id(), deviceMsg.getMsg());
//        } else {
//            sendMessage("您的数据格式有误,请检查后发送<----->" + message, client.getClient_id());
//        }

    }

    /**
     * 发生错误时触发
     * error
     */
    @OnError
    public void onError(Throwable error) {
        if (!socketServers.isEmpty()) {
            socketServers.forEach(client -> {
                if (client.getSession().getId().equals(session.getId())) {
                    socketServers.remove(client);
                    logger.error("客户端:【{}】发生异常", client.getClient_id());
                    error.printStackTrace();
                }
            });
        }

    }

    /**
     * 多个人发送给指定的几个用户
     * message
     * persons
     */
    public synchronized static void SendMany(String message, int[] persons) {
        for (int client_id : persons) {
            sendMessage(message, client_id);
        }
    }

}