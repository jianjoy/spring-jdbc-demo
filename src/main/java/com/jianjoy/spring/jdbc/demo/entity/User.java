package com.jianjoy.spring.jdbc.demo.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Author: zhoujian
 * Description:
 * Date: 2021/6/6 21:27
 */
@Data
@ToString
public class User {
    private Long id;
    private String name;
    private String memo;
    private Date createTime;
}