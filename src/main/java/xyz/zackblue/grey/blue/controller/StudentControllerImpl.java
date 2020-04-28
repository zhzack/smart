package xyz.zackblue.grey.blue.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.zackblue.grey.blue.pojo.StudentEntity;
import xyz.zackblue.grey.blue.service.StudentService;

import java.util.List;
import java.util.Map;


@RestController

public class StudentControllerImpl {

    final
    StudentService studentServices;

    public StudentControllerImpl(StudentService studentServices) {
        this.studentServices = studentServices;
    }


    /**
     * 新增数据
     */
    @RequestMapping("/save")
    @ResponseBody
    
    public String save() {

        int row = studentServices.saveStudent();
        //判断结果
        if (row == -1) {
            return "新增失败";
        } else {
            return "新增成功";
        }
    }

    /**
     * 查询数据
     */
    @RequestMapping("/query")
    @ResponseBody
    
    public String query() {
        //查寻数据
        List<Map<String,Object>> list = studentServices.queryAllStudent();
        //组装数据
//        ArrayList<java.io.Serializable> new_list;
//        new_list = new ArrayList<>();
//        //循环取出结果
//        for (StudentEntity studentEntity : list) {
//            //新建学生对象
//            //填充数据
//            new_list.add(studentEntity.getId());
//            new_list.add(studentEntity.getName());
//            new_list.add(studentEntity.getAge());
//
//        }
//        //返回数据
//        return new_list.toString();
        return list.toString();
    }


    /**
     * 更新数据
     */
    @GetMapping("/update")
    @ResponseBody
    
    public String update() {
        //新建对象传递数据
        StudentEntity stu = new StudentEntity();
        stu.setId(2);
        stu.setName("尼古拉斯");
        stu.setAddress("东北");
        //执行更新操作
        int row =0;
        row= studentServices.updateStudent(stu);
        //判断结果
        if (row == -1) {
            return "更新失败";
        } else {
            return "更新成功";
        }
    }

    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    @ResponseBody
    
    public String delete() {
        //初始化数据
        Integer id = 3;
        //执行删除
        int row = studentServices.deleteStudent(id);
        //判断结果
        if (row == -1) {
            return "删除失败";
        } else {
            return "删除成功";
        }
    }
}
