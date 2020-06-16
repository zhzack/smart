package xyz.blue.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.blue.pojo.ChildDevice;

import java.util.List;

//mybatis的mapper类：Dao
@Mapper
@Repository
public interface ChildDeviceMapper {

    //通过设备id查找设备
    List<ChildDevice> query_cdBydId(int deviceId);


}
