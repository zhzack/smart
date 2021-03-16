package xyz.blue.service.impl;


import org.springframework.stereotype.Service;
import xyz.blue.mapper.MsgMapper;
import xyz.blue.pojo.Msg;
import xyz.blue.service.MsgService;

@Service("DevicesService")//别名
public class MsgServiceImpl implements MsgService {

    private final MsgMapper msgMapper;

    public MsgServiceImpl(MsgMapper msgMapper) {
        this.msgMapper = msgMapper;
    }


    @Override
    public int deleteByPrimaryKey(Integer msg_id) {
        return 0;
    }

    @Override
    public int insert(Msg record) {
        msgMapper.insert(record);
        return 0;
    }

    @Override
    public int insertSelective(Msg record) {
        return 0;
    }

    @Override
    public Msg selectByPrimaryKey(Integer msg_id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Msg record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Msg record) {
        return 0;
    }
}

