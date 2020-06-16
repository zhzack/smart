package xyz.blue.pojo;

public class DeviceMsg {
    private int device_id;
    private int to_user_id;
    private String msg;

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
