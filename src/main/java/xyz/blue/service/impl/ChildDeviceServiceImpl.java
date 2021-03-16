package xyz.blue.service.impl;


import org.springframework.stereotype.Service;
import xyz.blue.mapper.ChildDeviceMapper;
import xyz.blue.pojo.ChildDevice;
import xyz.blue.service.ChildDeviceService;

import java.util.List;

@Service("ChildDeviceService")//别名
public class ChildDeviceServiceImpl implements ChildDeviceService {

    private final ChildDeviceMapper childDeviceMapper;

    public ChildDeviceServiceImpl(ChildDeviceMapper childDeviceMapper) {
        this.childDeviceMapper = childDeviceMapper;
    }

    @Override
    public List<ChildDevice> query_cdBydId(int deviceId) {
        return childDeviceMapper.query_cdBydId(deviceId);
    }
}

