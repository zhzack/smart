package xyz.blue.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.blue.pojo.User;

@Mapper
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer user_id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer user_id);

    User selectByName(String user_name);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}