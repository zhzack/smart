package xyz.zackblue.grey.blue.pojo;

import java.util.Date;

public class Users {
    private Integer id;
    private String name;
    private Integer gender;
    private Date birth;
    private Department department;

    public Users(Integer id, String name, Integer gender, Department department) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birth = new Date();
        this.department = department;
    }

    public Users() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
