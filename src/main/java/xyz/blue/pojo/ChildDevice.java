package xyz.blue.pojo;

public class ChildDevice {

    private int child_device_id;
    private int device_id;
    private String child_device_name;

    @Override
    public String toString() {
        return "ChildDevice{" +
                "child_device_id=" + child_device_id +
                ", device_id=" + device_id +
                ", child_device_name='" + child_device_name + '\'' +
                '}';
    }

    public int getChild_device_id() {
        return child_device_id;
    }

    public void setChild_device_id(int child_device_id) {
        this.child_device_id = child_device_id;
    }

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public String getChild_device_name() {
        return child_device_name;
    }

    public void setChild_device_name(String child_device_name) {
        this.child_device_name = child_device_name;
    }

    public ChildDevice(int child_device_id, int device_id, String child_device_name) {
        this.child_device_id = child_device_id;
        this.device_id = device_id;
        this.child_device_name = child_device_name;
    }
}
