CREATE TABLE person (
	id BIGINT NOT NULL AUTO_INCREMENT,
	service_name varchar(20),
	service_description varchar(100),
        service_version varchar(10),
	PRIMARY KEY (id)
);

insert into person (service_name, service_description, service_version) values ('Gradle', 'Its the building tool for java application','3.4.1');



