
CREATE TABLE IF NOT EXISTS person (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50),
  description VARCHAR(100) ,
  version VARCHAR(10),
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS version (
  service_id INT NOT NULL,
  version_id VARCHAR(10) NOT NULL,
  version_status VARCHAR(10) NOT NULL,
  version_started DATE NOT NULL,
  FOREIGN KEY (service_id) REFERENCES person(id),
  PRIMARY KEY (version_id , service_id));


CREATE TABLE IF NOT EXISTS compatability (
  compat_id INT NOT NULL AUTO_INCREMENT,
  service1_name VARCHAR(50) NOT NULL,
  service1_version VARCHAR(10) NOT NULL ,
  service2_name VARCHAR(50) NOT NULL,
  service2_version VARCHAR(10) NOT NULL ,
  compatable VARCHAR(10) NOT NULL,
  PRIMARY KEY (compat_id));
