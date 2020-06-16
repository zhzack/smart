package xyz.blue.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.blue.mapper.ChildDeviceMapper;
import xyz.blue.pojo.ChildDevice;
import xyz.blue.service.ChildDeviceService;

import java.util.List;

@Service("ChildDeviceService")//别名
public class ChildDeviceServiceImpl implements ChildDeviceService {

    @Autowired
    private ChildDeviceMapper childDeviceMapper;

    @Override
    public List<ChildDevice> query_cdBydId(int deviceId) {
        return childDeviceMapper.query_cdBydId(deviceId);
    }
}

