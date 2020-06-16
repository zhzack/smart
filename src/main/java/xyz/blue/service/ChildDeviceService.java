package xyz.blue.service;

import xyz.blue.pojo.ChildDevice;

import java.util.List;

public interface ChildDeviceService {

    List<ChildDevice> query_cdBydId(int deviceId);

}
