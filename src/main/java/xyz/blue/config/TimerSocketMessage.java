package xyz.blue.config;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xyz.blue.server.SocketServer;

//定时任务
@Component
@EnableScheduling
public class TimerSocketMessage {

    /**
     * 推送消息到前台
     */
    @Scheduled(cron = "*/5 * * * * * ")
    public void SocketMessage() {
        SocketServer.sendAll();
    }
}
