package xyz.blue.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.blue.pojo.User;

import java.util.List;

//mybatis的mapper类：Dao
@Mapper
@Repository
public interface UserMapper {
    //    查询用户列表
    List<User> queryUserList();

    //    通过id查询用户
    User queryUserByID(int id);

    //    通过name查询用户
    User queryUserByName(String name);

    //    添加用户
    Object addUser(User user);

    //  修改用户
    int updateUser(User user);

    //   删除用户
    int deleteUser(int id);
}
