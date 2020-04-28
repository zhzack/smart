package xyz.zackblue.grey.blue.service;

import xyz.zackblue.grey.blue.pojo.StudentEntity;

import java.util.List;
import java.util.Map;

public interface StudentService {

    List<Map<String,Object>> user_list();
    //写入数据
    int saveStudent();

    //查询数据
    List<Map<String,Object>> queryAllStudent();

    //更新数据
    default int updateStudent(StudentEntity StudentEntity) {
        return 0;
    }

    //删除数据
    int deleteStudent(Integer id);

}
