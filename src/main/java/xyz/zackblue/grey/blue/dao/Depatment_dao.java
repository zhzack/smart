package xyz.zackblue.grey.blue.dao;


import xyz.zackblue.grey.blue.pojo.Department;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Depatment_dao {

    private  static  Map<Integer, Department> departmentsHashMap =null;
    static {
        departmentsHashMap = new HashMap<>();
        departmentsHashMap.put(101,new Department(101,"运营"));
        departmentsHashMap.put(102,new Department(102,"管理"));

    }
    public Collection<Department> getDepartmentCollection(){
        return departmentsHashMap.values();
    }
    public Department getDepartment(Integer id){
        return departmentsHashMap.get(id);

    }
}
