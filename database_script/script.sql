create database consultoriodb;
USE consultoriodb;

CREATE TABLE paciente(
  id BIGINT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(64) NOT NULL,
  apellido VARCHAR(128) NOT NULL,
  correo VARCHAR(128),
  fecha_nacimiento date,
  telefono varchar(10),
  direccion varchar(1024),
  PRIMARY KEY (id)
);

CREATE TABLE medico(
  id BIGINT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(64) NOT NULL,
  apellido VARCHAR(128) NOT NULL,
  correo VARCHAR(128),
  fecha_nacimiento date,
  telefono varchar(10),
  direccion varchar(1024),
  PRIMARY KEY (id)
);

CREATE TABLE procedimiento_medico(
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  nombre varchar(256) NOT NULL,
  descripcion VARCHAR(512)
);

CREATE TABLE especialidad_medica(
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   nombre varchar(256) NOT NULL,
  descripcion VARCHAR(512)
);

CREATE TABLE cita(
	 id BIGINT NOT NULL AUTO_INCREMENT,
     descripcion VARCHAR(512),
     fecha datetime NOT NULL,
     paciente_id BIGINT NOT NULL,
     medico_id BIGINT NOT NULL,
     procedimiento_id BIGINT NOT NULL,
     PRIMARY KEY(id),
     CONSTRAINT fk_cita_paciente_id FOREIGN KEY (paciente_id) REFERENCES paciente(id),
	 CONSTRAINT fk_cita_medico_id FOREIGN KEY (medico_id) REFERENCES medico(id),
     CONSTRAINT fk_cita_procedimiento_id foreign key(procedimiento_id) references procedimiento_medico(id)

);

CREATE TABLE medico_especialidad(
	medico_id BIGINT NOT NULL,
    especialidad_id BIGINT NOT NULL,
    CONSTRAINT pk_medico_especialidad PRIMARY KEY(medico_id,especialidad_id),
	CONSTRAINT fk_medico_id FOREIGN KEY (medico_id)
             REFERENCES medico (id),
    CONSTRAINT fk_especialidad_id FOREIGN KEY (especialidad_id)
             REFERENCES especialidad_medica (id)        
);