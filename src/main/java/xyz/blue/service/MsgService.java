package xyz.blue.service;

import xyz.blue.pojo.Msg;

public interface MsgService {

    int deleteByPrimaryKey(Integer msg_id);

    int insert(Msg record);

    int insertSelective(Msg record);

    Msg selectByPrimaryKey(Integer msg_id);

    int updateByPrimaryKeySelective(Msg record);

    int updateByPrimaryKey(Msg record);


}
