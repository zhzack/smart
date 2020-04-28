package xyz.zackblue.grey.blue.service.impl;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;
import xyz.zackblue.grey.blue.pojo.StudentEntity;
import xyz.zackblue.grey.blue.service.StudentService;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service("studentServices")//别名
public class StudentServiceImpl implements StudentService {


    @Resource
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Map<String, Object>> user_list() {
        String sql = "select * from student";
        List<Map<String, Object>> user_list = jdbcTemplate.queryForList(sql);
        return user_list;
    }

    //JDBC 写入数据
    @Override
    public int saveStudent() {
        //初始化属性参数
        String name = "张三";
        Integer age = 12;
        //执行写入
        int row = jdbcTemplate.update("INSERT INTO student (age)VALUES (?);", 12);
        //返回结果
        return row;
    }


    //JDBC 查询数据
    @Override
    public List<Map<String,Object>> queryAllStudent() {
        //SQL
        String sql = "SELECT *  FROM tb_user ";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
        //结果
//        List<StudentEntity> list = jdbcTemplate.query(sql, new RowMapper<StudentEntity>() {
//            //映射每行数据
//            @Override
//            public StudentEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
//                StudentEntity stu = new StudentEntity();
//                stu.setId(rs.getInt("ID"));
//                stu.setAge(rs.getInt("AGE"));
//                stu.setName(rs.getString("NAME"));
//                stu.setAddress(rs.getString("ADDRESS"));
//                return stu;
//            }
//
//        });
        //返回结果
        return list;
    }

    //JDBC 更新数据
    @Override
    public int updateStudent(StudentEntity studentEntity) {
        //SQL
        String sql = "update tb_user set user_name=?,user_address=? where user_id=101878";
        //结果
        int row = jdbcTemplate.update(sql, new PreparedStatementSetter() {
            //映射数据
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, studentEntity.getName());
                preparedStatement.setString(2, studentEntity.getAddress());
                //preparedStatement.setInt(3, studentEntity.getId());
            }
        });
        //反悔结果
        return row;
    }

    //删除数据
    @Override
    public int deleteStudent(Integer id) {
        //SQL+结果
        int resRow = jdbcTemplate.update("UPDATE student SET is_delete=1 WHERE id=?",
                new PreparedStatementSetter() {
                    //映射数据
                    @Override
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setInt(1, id);
                    }
                });
        //返回结果
        return resRow;
    }
}

