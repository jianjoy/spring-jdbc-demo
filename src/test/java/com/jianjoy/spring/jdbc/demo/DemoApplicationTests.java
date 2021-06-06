package com.jianjoy.spring.jdbc.demo;

import com.jianjoy.spring.jdbc.demo.entity.User;
import com.jianjoy.spring.jdbc.demo.service.JdbcService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private JdbcService service;

    @Test
    void contextLoads() {
    }


    @Test
    public void findAll() {
        List<User> users = service.findAll();
        Assertions.assertNotNull(users);
        System.out.println(users.size());
    }

    @Test
    public void batchAddUser() {
        List<User> userList = new ArrayList<>();
        User testUser1 = new User();
        testUser1.setName(UUID.randomUUID().toString());
        testUser1.setMemo("test-memo-1");
        userList.add(testUser1);
        User testUser2 = new User();
        testUser2.setName(UUID.randomUUID().toString());
        testUser2.setMemo("test-memo-2");
        userList.add(testUser2);
        int count = service.batchAddUser(userList);
        Assertions.assertTrue(count == 2);
    }

    @Test
    public void updateUser() {
        int rows = service.updateUser(1L, "memo-update");
        Assertions.assertTrue(1 == rows);
    }

    @Test
    public void addUser() {
        int row = service.addUser(UUID.randomUUID().toString(), "memo-" + UUID.randomUUID().toString());
        Assertions.assertTrue(row == 1);
    }

    @Test
    public void deleteUser() {
        int row = service.deleteUser(999l);
        Assertions.assertTrue(1 == row);
    }

    @Test
    public void findById(){
        User user = service.findById(1L);
        Assertions.assertNotNull(user);
        System.out.println(user);
    }

}
