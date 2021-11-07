CREATE SEQUENCE persona_id_seq
START 1
INCREMENT 1
MINVALUE 1;

CREATE SEQUENCE contacto_id_seq
START 1
INCREMENT 1
MINVALUE 1;

CREATE SEQUENCE relacion_id_seq
START 1
INCREMENT 1
MINVALUE 1;


create table Persona (
persona_id  integer not null default nextval('persona_id_seq'),
nombre      varchar(50),
apellido    varchar(50),
documento_tipo varchar(20),
documento_numero varchar(20),
pais varchar(50),
edad integer,
CONSTRAINT CON_PERSONA_PK PRIMARY KEY(persona_id),
CONSTRAINT CON_CONTACTO_NN_01 CHECK (apellido IS NOT NULL),
CONSTRAINT CON_CONTACTO_NN_02 CHECK (documento_tipo IS NOT NULL),
CONSTRAINT CON_CONTACTO_NN_03 CHECK (documento_numero IS NOT NULL),
CONSTRAINT CON_PERSONA_UN UNIQUE (documento_tipo, documento_numero, pais)
)

create table Relacion (
relacion_id integer not null default nextval('contacto_id_seq'),
hijo_id integer,
padre_id integer,
CONSTRAINT CON_RELACION_PK PRIMARY KEY(relacion_id),
CONSTRAINT CON_RELACION_FK_01 FOREIGN KEY(hijo_id) REFERENCES Persona(persona_id),
CONSTRAINT CON_RELACION_FK_02 FOREIGN KEY(padre_id) REFERENCES Persona(persona_id)
)

create table Contacto (
contacto_id integer not null default nextval('relacion_id_seq'),
persona_id integer,
tipo varchar(20),
descripcion varchar(200),
CONSTRAINT CON_CONTACTO_NN_01 CHECK (tipo IS NOT NULL),
CONSTRAINT CON_CONTACTO_NN_02 CHECK (descripcion IS NOT NULL),
CONSTRAINT CON_CONTACTO_PK PRIMARY KEY(contacto_id),
CONSTRAINT CON_CONTACTO_FK_01 FOREIGN KEY(persona_id) REFERENCES Persona(persona_id)
)