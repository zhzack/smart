package xyz.blue.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.blue.mapper.DeviceMsgMapper;
import xyz.blue.pojo.DeviceMsg;
import xyz.blue.service.DeviceMsgService;

import java.util.List;

@Service("DeviceMsgService")//别名
public class DeviceMsgServiceImpl implements DeviceMsgService {

    @Autowired
    private DeviceMsgMapper deviceMsgMapper;


    @Override
    public void insert_msg(DeviceMsg deviceMsg) {
        deviceMsgMapper.insert_msg(deviceMsg);
    }

    @Override
    public List<DeviceMsg> queryDeviceMsgByID(int to_user_id) {
        return deviceMsgMapper.queryDeviceMsgByID(to_user_id);
    }
}

