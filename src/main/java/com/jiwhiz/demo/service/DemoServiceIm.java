package com.jiwhiz.demo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;

import javax.sql.DataSource;
import java.util.Date;
import com.jiwhiz.demo.model.Demo;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceIm implements DemoService {

    @Autowired
    JdbcTemplate template;
    public DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.template = new JdbcTemplate(dataSource);
	}

           	public void addService(String name, String description, String  version){
		
		//JdbcTemplate template = new JdbcTemplate(dataSource);
		template.update("INSERT INTO person(name,description,version)VALUES(?,?,?)",name,description,version );
		
	}

           	public void addVersion(String service_id, String version_id, String version_status, String version_started){
		
		//JdbcTemplate template = new JdbcTemplate(dataSource);
		template.update("INSERT INTO version(service_id,version_id,version_status,version_started)VALUES(?,?,?,?)",service_id,version_id,version_status,version_started);
		
	}
               
                public void addCompat(String service1_name,String service1_version,String service2_name,String service2_version,String compatable){
		template.update("INSERT INTO compatability(service1_name,service1_version,service2_name,service2_version,compatable)VALUES(?,?,?,?,?)",service1_name,service1_version,service2_name,service2_version,compatable);

        }


	
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


    public List<Demo> findAllVersion() {
        String sql1 = "select * from version order by version_started ASC";
        RowMapper<Demo> rm = new RowMapper<Demo>() {
            @Override
            public Demo mapRow(ResultSet resultSet, int i) throws SQLException {
                Demo user1 = new Demo(resultSet.getInt("service_id"),
                        resultSet.getString("version_id"),
                        resultSet.getString("version_status"),
                        resultSet.getDate("version_started"));

                return user1;
            }
        };

        return template.query(sql1, rm);
 }                                                                            


    public List<Demo> findCompat() {
        String sqlcompat = "select * from compatability";
        RowMapper<Demo> rm = new RowMapper<Demo>() {
            @Override
            public Demo mapRow(ResultSet resultSet, int i) throws SQLException {
                Demo compat = new Demo(resultSet.getInt("compat_id"),
                        resultSet.getString("service1_name"),
                        resultSet.getString("service1_version"),
                        resultSet.getString("service2_name"),
                        resultSet.getString("service2_version"),
                        resultSet.getString("compatable"));

                return compat;
            }
        };

        return template.query(sqlcompat, rm);
 } 

}

