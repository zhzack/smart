package xyz.blue.pojo;


public class DeviceMsg {
    private int device_msg_id;
    private int device_id;
    private int to_user_id;
    private String msg;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DeviceMsg(int device_msg_id, int device_id, int to_user_id, String msg, String date) {
        this.device_msg_id = device_msg_id;
        this.device_id = device_id;
        this.to_user_id = to_user_id;
        this.msg = msg;
        this.date = date;
    }

    public int getDevice_msg_id() {
        return device_msg_id;
    }

    public void setDevice_msg_id(int device_msg_id) {
        this.device_msg_id = device_msg_id;
    }

    @Override
    public String toString() {
        return "DeviceMsg{" +
                "device_id=" + device_id +
                ", to_user_id=" + to_user_id +
                ", msg='" + msg + '\'' +
                '}';
    }

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public int getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(int to_user_id) {
        this.to_user_id = to_user_id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DeviceMsg(int device_id, int to_user_id, String msg) {
        this.device_id = device_id;
        this.to_user_id = to_user_id;
        this.msg = msg;
    }
}
