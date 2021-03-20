package xyz.blue.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.blue.service.impl.DeviceServiceImpl;

import javax.websocket.Session;
import java.io.Serializable;
import java.util.ArrayList;

public class Client implements Serializable {
    @Autowired
    DeviceServiceImpl deviceService;

    private static final long serialVersionUID = 8957107006902627635L;

    private String client_id;
    private String identity;
    private Session session;

    private ArrayList<Device> DeviceList = new ArrayList<>();


    public Client(String client_id, Session session) {

        if (String.valueOf(client_id.charAt(0)).equals("U")) {
            this.client_id = client_id.substring(1);
//            this.DeviceList.addAll(deviceService.queryDeviceListByUserID(Integer.parseInt(this.client_id)));
        } else {
            this.client_id = client_id;
//            this.DeviceList.addAll(deviceService.query_deviceById(Integer.parseInt(client_id)));
        }
        System.out.println(this.client_id);
        this.session = session;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public ArrayList<Device> getDeviceList() {
        return DeviceList;
    }

    public void setDeviceList(ArrayList<Device> deviceList) {
        DeviceList = deviceList;
    }

    public String getClient_id() {
        return client_id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public Client() {
    }


}
