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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppConfig.class)
@TestPropertySource(locations="classpath:application_test.properties")
public class DemoServiceImTest {
    @Autowired
    DemoService demoService;

    @Autowired
	JdbcTemplate jdbcTemplate;
	RowMapper<Demo> rm;
	@Before
    public void setUp() {
        rm = new RowMapper<Demo>() {
            @Override
            public Demo mapRow(ResultSet resultSet, int i) throws SQLException {
                Demo user = new Demo(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("description"), resultSet.getString("version"));

                return user;
            }
        };
        System.out.println("SETUP done");
    }
    
    @Test
    public void testInitialization() throws Exception{
        assertNotNull(demoService);
        assertNotNull(jdbcTemplate);
    }

    @Test
    public void testAddService() throws Exception {
        demoService.addService("test_name", "test_description", "1.0.0");
        List<Demo> result = jdbcTemplate.query("select * from person where name='test_name'", rm);
        assertEquals(result.size(), 1);
        Demo demo = result.get(0);
        assertEquals(demo.name, "test_name");
        assertEquals(demo.version, "1.0.0");
        assertEquals(demo.description, "test_description");
    }

    @After
    public void tearDown() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "person");
    }

}