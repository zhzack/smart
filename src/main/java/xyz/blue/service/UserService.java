package xyz.blue.service;

import xyz.blue.pojo.User;

import java.util.List;

public interface UserService {

    //    查询用户列表
    List<User> queryUserList();
    //    通过id查询用户
    User queryUserByID(int id);
    //    通过name查询用户
    User queryUserByName(String name);
    //    添加用户
    Object addUser(User users);
    //  修改用户
    int updateUser(User users);
    //   删除用户
    int deleteUser(int id);

}
