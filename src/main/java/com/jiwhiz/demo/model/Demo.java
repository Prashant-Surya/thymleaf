package com.jiwhiz.demo.model;

import java.util.Date;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 
@Entity
public class Demo {
 
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        public int id;
        public String name;
        public String description;
        public String version;

        public int service_id;
        public String version_id;
        public String version_status;
        public Date version_started;
      
        public String compat_id;
        public String service1_name;
        public String service1_version;
        public String service2_name;
        public String service2_version;
        public String compatable;



        public Demo(int id, String name, String description, String version){
            this.id=id;
            this.name=name;
            this.description=description;
            this.version=version;
        }

        public Demo(int service_id, String version_id, String version_status, Date version_started){
            this.service_id=service_id;
            this.version_id=version_id;
            this.version_status=version_status;
            this.version_started=version_started;
        }

        public Demo(int compat_id, String service1_name, String service1_version, String service2_name, String service2_version, String compatable){
            this.service1_name=service1_name;
            this.service1_version=service1_version;
            this.service2_name=service2_name;
            this.service2_version=service2_version;
            this.compatable=compatable;
        }
 
}
