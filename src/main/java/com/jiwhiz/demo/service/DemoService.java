package com.jiwhiz.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import com.jiwhiz.demo.model.Demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class DemoService {

    @Autowired
    JdbcTemplate template;

    public List<Demo> findAll() {
        String sql = "select * from person";
        RowMapper<Demo> rm = new RowMapper<Demo>() {
            @Override
            public Demo mapRow(ResultSet resultSet, int i) throws SQLException {
                Demo user = new Demo(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("version"));

                return user;
            }
        };

        return template.query(sql, rm);
    }
}
