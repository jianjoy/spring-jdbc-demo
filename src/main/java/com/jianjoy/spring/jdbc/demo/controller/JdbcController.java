package com.jianjoy.spring.jdbc.demo.controller;

import com.jianjoy.spring.jdbc.demo.entity.User;
import com.jianjoy.spring.jdbc.demo.service.JdbcService;
import com.jianjoy.spring.jdbc.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * Author: zhoujian
 * Description:
 * Date: 2021/6/6 21:34
 */
@RestController
@RequestMapping("/jdbc")
public class JdbcController {

    @Autowired
    private JdbcService jdbcService;

    @GetMapping("/user")
    public UserVO getUserById(Long id) {
        User user = jdbcService.findById(id);
        return Objects.isNull(user) ? UserVO.builder().build() :
                UserVO.builder()
                        .createTime(user.getCreateTime())
                        .memo(user.getMemo())
                        .name(user.getName())
                        .build();
    }
}