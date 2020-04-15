package xyz.zackblue.grey.blue.pojo;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor


public class Department {
    private Integer Id;
    private String name;

    public Department(Integer id, String name) {
        Id = id;
        this.name = name;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
