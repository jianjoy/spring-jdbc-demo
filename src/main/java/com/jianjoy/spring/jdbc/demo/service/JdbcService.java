package com.jianjoy.spring.jdbc.demo.service;

import com.jianjoy.spring.jdbc.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: zhoujian
 * Description:
 * Date: 2021/6/6 21:26
 */
@Service
public class JdbcService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询用户信息
     *
     * @param id
     * @return
     */
    public User findById(Long id) {
        String sql = "select * from user where id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setCreateTime(resultSet.getTimestamp("create_time"));
                user.setId(id);
                user.setMemo(resultSet.getString("memo"));
                user.setName(resultSet.getString("name"));
                return user;
            }
        });
    }


    /**
     * 添加用户
     *
     * @param name
     * @param memo
     * @return
     */
    public int addUser(String name, String memo) {
        String sql = "insert into user(name,memo,create_time) values(?,?,?)";
        return jdbcTemplate.update(sql, new Object[]{name, memo, new Date()});
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    public int deleteUser(Long id) {
        String sql = "delete from user where id=?";
        return jdbcTemplate.update(sql, new Object[]{id});
    }

    /**
     * 修改用户
     *
     * @param id
     * @param memo
     * @return
     */
    public int updateUser(Long id, String memo) {
        String sql = "update user set memo=? where id=?";
        return jdbcTemplate.update(sql, new Object[]{ memo,id});
    }


    @Transactional
    public int batchAddUser(List<User> userList) {
        String sql = "insert into user(name,memo,create_time) values(?,?,?)";
        return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                User user = userList.get(i);
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getMemo());
                preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            }
            @Override
            public int getBatchSize() {
                return userList.size();
            }
        }).length;
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    public List<User> findAll() {
        String sql = "select * from user";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<User>>() {
            @Override
            public List<User> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                List<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setMemo(resultSet.getString("memo"));
                    user.setCreateTime(resultSet.getTimestamp("create_time"));
                    users.add(user);
                }
                return users;
            }
        });
    }

}