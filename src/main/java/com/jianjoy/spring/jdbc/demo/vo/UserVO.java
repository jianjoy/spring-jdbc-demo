package com.jianjoy.spring.jdbc.demo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Author: zhoujian
 * Description:
 * Date: 2021/6/6 21:38
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private String name;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String memo;
}