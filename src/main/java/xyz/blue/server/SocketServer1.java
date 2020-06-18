//package xyz.blue.server;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import org.apache.shiro.SecurityUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import xyz.blue.pojo.Client;
//import xyz.blue.pojo.User;
//import xyz.blue.service.impl.DeviceServiceImpl;
//import xyz.blue.service.impl.UserServiceImpl;
//import xyz.blue.tools.nowdate;
//
//import javax.websocket.*;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//import java.util.List;
//import java.util.concurrent.CopyOnWriteArraySet;
//import java.util.concurrent.FutureTask;
//import java.util.stream.Collectors;
//
//@ServerEndpoint(value = "/socketServer/{userName}")
//@Component
//public class SocketServer1 implements Runnable{
//
//    private static final Logger logger = LoggerFactory.getLogger(SocketServer1.class);
//
//    @Autowired
//    DeviceServiceImpl deviceService;
//    @Autowired
//    private SocketServer1 socketServer;
//    @Autowired
//    UserServiceImpl userService;
//
//    user_Log user_log;
//    device_Log device_log;
//
//
//
//
//    /**
//     * 用线程安全的CopyOnWriteArraySet来存放客户端连接的信息
//     */
//    private static final CopyOnWriteArraySet<Client> socketServers = new CopyOnWriteArraySet<>();
//    public static final CopyOnWriteArraySet<String> msgs = new CopyOnWriteArraySet<>();
//
//    /**
//     * websocket封装的session,信息推送，就是通过它来信息推送
//     */
//    private Session session;
//
//    nowdate nowdate = new nowdate();
//
//    CallableThreadTest ctt = new CallableThreadTest();
//    FutureTask<Integer> ft = new FutureTask<>(ctt);
//    /**
//     * 服务端的userName,因为用的是set，每个客户端的username必须不一样，否则会被覆盖。
//     * 要想完成ui界面聊天的功能，服务端也需要作为客户端来接收后台推送用户发送的信息
//     */
//
//    /**
//     * 用户连接时触发，我们将其添加到
//     * 保存客户端连接信息的socketServers中
//     * <p>
//     * session
//     * userName
//     */
//    @OnOpen
//    public void open(Session session, @PathParam(value = "userName") String client_id) {
//
//        this.session = session;
//
//        socketServers.add(new Client(client_id, session));
////        new Thread("client_id").start();
//        //更新设备状态
//
//        int client_id_int = Integer.parseInt(client_id);
//        System.out.println(client_id_int + 2);
//
//
////        if(client_id_int>101877){
////
////            user_log.user_onopen(client_id_int);
////
////        }else {
////
////            device_log.device_onopen(client_id_int);
////
////        }
//
//
//        logger.info(nowdate.nowDate() + "客户端:【{}】连接成功", client_id);
//
//    }
//
//    /**
//     * 收到客户端发送信息时触发
//     * 我们将其推送给客户端(blue)
//     * 其实也就是服务端本身，为了达到前端聊天效果才这么做的
//     * <p>
//     * message
//     */
//    @OnMessage
//    public void onMessage(String message) {
//
//        Client client = socketServers.stream().filter(cli -> cli.getSession() == session)
//                .collect(Collectors.toList()).get(0);
//
//
////        JSONObject jsonMessage = JSON.parseObject(message);
////        //不为空且与当前登录用户名相符
////        if (jsonMessage.get("clientId") != null && jsonMessage.get("clientId") == client.getClient_id()) {
////
//////            判断连接用户属性，设备or用户
////            if (Integer.parseInt(client.getClient_id()) <= 100000) {
////                sendMessage((String) jsonMessage.get("command"), (String) jsonMessage.get("to"));
////            } else {
////                sendMessage((String) jsonMessage.get("command"), (String) jsonMessage.get("to"));
////            }
////        } else {
////            sendMessage(client.getClient_id() + "<--" + "信息发送有误，请检查后发送", client.getClient_id());
////        }
//
//
////
////		User loginUser = (User) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
////
////		sendMessage(client.getUserName()+"<--"+message, String.valueOf(loginUser.getUser_id()));
//
//        sendMessage(client.getClient_id() + "<--" + message);
//        msgs.add(message);
//        logger.info("客户端:【{}】发送信息:{}", client.getClient_id(), message);
//    }
//
//    /**
//     * 连接关闭触发，通过sessionId来移除
//     * socketServers中客户端连接信息
//     */
//    @OnClose
//    public void onClose() {
//        socketServers.forEach(client -> {
//            if (client.getSession().getId().equals(session.getId())) {
//
//                logger.info("客户端:【{}】断开连接", client.getClient_id());
//
//                System.out.println(socketServers.stream().map(Client::getClient_id));
//
//                socketServers.remove(client);
//
//            }
//        });
//    }
//
//    /**
//     * 发生错误时触发
//     * error
//     */
//    @OnError
//    public void onError(Throwable error) {
//        socketServers.forEach(client -> {
//            if (client.getSession().getId().equals(session.getId())) {
//                socketServers.remove(client);
//                logger.error("客户端:【{}】发生异常", client.getClient_id());
//                error.printStackTrace();
//            }
//        });
//    }
//
//    /**
//     * 信息发送的方法，通过客户端的userName
//     * 拿到其对应的session，调用信息推送的方法
//     * message
//     * userName
//     */
//    public synchronized static void sendMessage(String message) {
//
//
//        JSONObject jsonMessage = JSON.parseObject(message);
//        //不为空且与当前登录用户名相符
//        if (jsonMessage.get("clientId") != null) {
////            判断连接用户属性，设备or用户
//
//            socketServers.forEach(client -> {
//                if (jsonMessage.get("clientId").equals(client.getClient_id())) {
//                    try {
//                        client.getSession().getBasicRemote().sendText((String) jsonMessage.get("command"));
//
//                        logger.info("客户端:【{}】发送信息:{}", client.getClient_id(), jsonMessage.get("command"));
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//
//
//        } else {
//            sendMessage(jsonMessage.get("clientId") + "<--" + "信息发送有误，请检查后发送");
//        }
//
//    }
//
//    /**
//     * 获取服务端当前客户端的连接数量，
//     * 因为服务端本身也作为客户端接受信息，
//     * 所以连接总数还要减去服务端
//     * 本身的一个连接数
//     * <p>
//     * 这里运用三元运算符是因为客户端第一次在加载的时候
//     * 客户端本身也没有进行连接，-1 就会出现总数为-1的情况，
//     * 这里主要就是为了避免出现连接数为-1的情况
//     */
//    public synchronized static int getOnlineNum() {
//        return (int) socketServers.stream().filter(client -> !client.getClient_id().equals(123)).count();
//    }
//
//    /**
//     * 获取在线用户名，前端界面需要用到
//     *
//     * @return
//     */
//    public synchronized static List<String> getOnlineUsers() {
//
//        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
//
//        return socketServers.stream()
//                .map(Client::getClient_id)
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * 信息群发，我们要排除服务端自己不接收到推送信息
//     * 所以我们在发送的时候将服务端排除掉
//     * message
//     */
//    public synchronized static void sendAll(String message) {
//        //群发，不能发送给服务端自己
//        socketServers.stream().filter(cli -> !cli.getClient_id().equals(123))
//                .forEach(client -> {
//                    try {
//                        client.getSession().getBasicRemote().sendText(message);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                });
//
//        logger.info("服务端推送给所有客户端 :【{}】", message);
//    }
//
//    /**
//     * 多个人发送给指定的几个用户
//     * message
//     * persons
//     */
//    public synchronized static void SendMany(String message, String[] persons) {
//        for (String userName : persons) {
//            sendMessage(message);
//        }
//    }
//
//    /**
//     * When an object implementing interface <code>Runnable</code> is used
//     * to create a thread, starting the thread causes the object's
//     * <code>run</code> method to be called in that separately executing
//     * thread.
//     * <p>
//     * The general contract of the method <code>run</code> is that it may
//     * take any action whatsoever.
//     *
//     * @see Thread#run()
//     */
//    @Override
//    public void run() {
//        while (true){
//            if (msgs.size()>0){
//
//
//            }
//        }
//    }
//}
