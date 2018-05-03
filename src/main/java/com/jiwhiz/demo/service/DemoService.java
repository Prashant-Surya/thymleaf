package com.jiwhiz.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import com.jiwhiz.demo.model.Demo;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DemoService {
                
	       	void addService(String name, String description, String version);
                void addVersion(String service_id, String version_id, String version_status, String version_started);
                void addCompat(String service1_name,String service1_version,String service2_name,String service2_version,String compatable);
		List<Demo> findAll();
                List<Demo> findAllVersion();
                List<Demo> findCompat();
	
}

