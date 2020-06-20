package xyz.blue.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.blue.pojo.DeviceMsg;

import java.util.List;

//mybatis的mapper类：Dao
@Mapper
@Repository
public interface DeviceMsgMapper {
    void insert_msg(DeviceMsg deviceMsg);

    List<DeviceMsg> queryDeviceMsgByID(int to_user_id);
}
