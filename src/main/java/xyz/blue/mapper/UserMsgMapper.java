package xyz.blue.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.blue.pojo.UserMsg;

import java.util.List;

//mybatis的mapper类：Dao
@Mapper
@Repository
public interface UserMsgMapper {
    void insert_msg(UserMsg userMsg);

    List<UserMsg> queryUserMsgByID(int user_id);
}
