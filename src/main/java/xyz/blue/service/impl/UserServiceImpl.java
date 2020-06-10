package xyz.blue.service.impl;


import org.springframework.stereotype.Service;
import xyz.blue.mapper.UserMapper;
import xyz.blue.pojo.User;
import xyz.blue.service.UserService;

import java.util.List;

@Service("UserService")//别名
//@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public List<User> queryUserList() {
        return userMapper.queryUserList();
    }

    @Override
    public User queryUserByID(int id) {
        return userMapper.queryUserByID(id);
    }

    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }

    @Override
    public Object addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUser(int id) {
        return userMapper.deleteUser(id);
    }
}

