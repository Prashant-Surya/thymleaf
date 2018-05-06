package com.jiwhiz.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.jiwhiz.demo.AppConfig;
import com.jiwhiz.demo.model.Demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations="classpath:application_test.properties")
@SpringBootTest(classes=AppConfig.class)
public class DemoServiceImTest {
    @Autowired
    DemoService demoService;

    @Autowired
	JdbcTemplate jdbcTemplate;
    RowMapper<Demo> person;
    RowMapper<Demo> version;

	@Before
    public void setUp() {
        person = new RowMapper<Demo>() {
            @Override
            public Demo mapRow(ResultSet resultSet, int i) throws SQLException {
                Demo user = new Demo(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("description"), resultSet.getString("version"));

                return user;
            }
        };
        version = new RowMapper<Demo>() {
            @Override
            public Demo mapRow(ResultSet resultSet, int i) throws SQLException {
                Demo user1 = new Demo(resultSet.getInt("service_id"), resultSet.getString("version_id"),
                        resultSet.getString("version_status"), resultSet.getDate("version_started"));

                return user1;
            }
        };
    }
    
    @Test
    public void testInitialization() throws Exception{
        assertNotNull(demoService);
        assertNotNull(jdbcTemplate);
    }

    @Test
    public void testAddService(){
        demoService.addService("test_name", "test_description", "1.0.0");
        List<Demo> result = jdbcTemplate.query("select * from person where name='test_name'", person);
        assertEquals(result.size(), 1);
        Demo demo = result.get(0);
        assertEquals(demo.name, "test_name");
        assertEquals(demo.version, "1.0.0");
        assertEquals(demo.description, "test_description");
    }

    @Test
    public void testAddVersion(){
        demoService.addService("test_name", "test_description", "1.0.0");
        List<Demo> services = jdbcTemplate.query("select * from person where name='test_name'", person);
        Demo service = services.get(0);
        demoService.addVersion(Integer.toString(service.id), "version_id", "status", "2018-05-16");
        String query = "select * from version where service_id='" + Integer.toString(service.id) + "'";
        List<Demo> result = jdbcTemplate.query(query, version);
        assertEquals(result.size(), 1);
        Demo version = result.get(0);
        assertEquals(version.version_id, "version_id");
    }

    @After
    public void tearDown() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "version");
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "person");
    }

}