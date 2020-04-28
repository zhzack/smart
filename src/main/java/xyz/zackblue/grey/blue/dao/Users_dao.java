package xyz.zackblue.grey.blue.dao;

import xyz.zackblue.grey.blue.pojo.Department;
import xyz.zackblue.grey.blue.pojo.Users;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Users_dao {
    private static final Map<Integer, Users> usersHashMap;

    static {
        usersHashMap = new HashMap<>();
        usersHashMap.put(1001, new Users(1001, "运营", 0, new Department(101, "运营")));
        usersHashMap.put(1002, new Users(1002, "管理", 1, new Department(102, "管理")));

    }

    public static Collection<Users> getUsersCollection() {

        return usersHashMap.values();
    }
}
