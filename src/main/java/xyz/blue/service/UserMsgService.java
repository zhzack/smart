package xyz.blue.service;

import xyz.blue.pojo.UserMsg;

import java.util.List;

public interface UserMsgService {
    void insert_msg(UserMsg userMsg);

    List<UserMsg> queryUserMsgByID(int user_id);
}
