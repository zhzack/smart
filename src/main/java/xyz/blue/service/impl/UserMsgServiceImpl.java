package xyz.blue.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.blue.mapper.UserMsgMapper;
import xyz.blue.pojo.UserMsg;
import xyz.blue.service.UserMsgService;

import java.util.List;

@Service("UserMsgService")//别名
public class UserMsgServiceImpl implements UserMsgService {


    @Autowired
    private UserMsgMapper userMsgMapper;

    @Override
    public void insert_msg(UserMsg userMsg) {
        userMsgMapper.insert_msg(userMsg);
    }

    @Override
    public List<UserMsg> queryUserMsgByID(int user_id) {
        return userMsgMapper.queryUserMsgByID(user_id);
    }
}

