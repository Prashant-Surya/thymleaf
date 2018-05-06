package com.jiwhiz.demo;

import java.sql.SQLException;

import com.jiwhiz.demo.service.DemoService;
import com.jiwhiz.demo.service.DemoServiceIm;
import com.mysql.jdbc.Driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@ConfigurationProperties()
@Configuration
public class AppConfig {
    @Autowired
    private Environment environment;
	@Bean
	public DemoService getSampleService() {
		return new DemoServiceIm();
    }
    @Bean
    public JdbcTemplate geJdbcTemplate() throws SQLException{
        SimpleDriverDataSource ds = new SimpleDriverDataSource();
        ds.setDriver(new Driver());
        ds.setUrl(environment.getProperty("spring.datasource.url"));
        ds.setUsername(environment.getProperty("spring.datasource.username"));
        ds.setPassword(environment.getProperty("spring.datasource.password"));
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        return jdbcTemplate;
    }
}
