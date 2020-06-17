package xyz.blue.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.blue.pojo.DeviceMsg;

//mybatis的mapper类：Dao
@Mapper
@Repository
public interface UserMsgMapper {
    void insert_msg(DeviceMsg deviceMsg);
}
