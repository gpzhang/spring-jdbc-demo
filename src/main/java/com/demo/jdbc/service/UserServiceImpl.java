package com.demo.jdbc.service;

import com.demo.jdbc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author haishen
 * @date 2019/3/30
 */
@Service
public class UserServiceImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User selectUserById(Integer id) {
        User user = new User();
        String sql = "SELECT id,name,age FROM user where id = ?";
        jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
            }
        });
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean insertUser(User user) {
        String insertQuery = "insert into user (name, age) values (?, ?)";
        jdbcTemplate.update(insertQuery, user.getName(), user.getAge());
        return true;
    }
}
