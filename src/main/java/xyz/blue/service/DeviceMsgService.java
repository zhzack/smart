package xyz.blue.service;

import xyz.blue.pojo.DeviceMsg;

import java.util.List;

public interface DeviceMsgService {
    void insert_msg(DeviceMsg deviceMsg);

    List<DeviceMsg> queryDeviceMsgByID(int deviceId);
}
