package xyz.blue.service;

import xyz.blue.pojo.User;

public interface UserService {

    int deleteByPrimaryKey(Integer user_id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer user_id);

    User selectByName(String user_name);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}
