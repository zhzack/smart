package xyz.blue.pojo;

import java.util.Date;

public class UserMsg {
    private int user_msg_id;
    private int user_id;
    private int to_device_id;
    private String msg;
    private Date date;

    @Override
    public String toString() {
        return "UserMsg{" +
                "user_id=" + user_id +
                ", to_device_id=" + to_device_id +
                ", msg='" + msg + '\'' +
                '}';
    }

    public int getUser_msg_id() {
        return user_msg_id;
    }

    public void setUser_msg_id(int user_msg_id) {
        this.user_msg_id = user_msg_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTo_device_id() {
        return to_device_id;
    }

    public void setTo_device_id(int to_device_id) {
        this.to_device_id = to_device_id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserMsg(int user_id, int to_device_id, String msg) {
        this.user_id = user_id;
        this.to_device_id = to_device_id;
        this.msg = msg;
    }
}
