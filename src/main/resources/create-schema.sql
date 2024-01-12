drop schema if exists MY_SCHEMA cascade;
create schema MY_SCHEMA;
grant all privileges to MY_SCHEMA;
--create sequence MY_SCHEMA.MY_ID_SEQUENCE start with 1 increment by 50;
--create table MY_SCHEMA.users (
--    ID number(38) NOT NULL primary key,
--    EMAIL VARCHAR(255),
--    NAME VARCHAR(255),
--    DATE_VAL DATE,
--    TIMESTAMP_VAL TIMESTAMP,
--    VERSION TIMESTAMP
--);
create sequence MY_SCHEMA.MY_ID_SEQUENCE start with 1 increment by 50;
create table MY_SCHEMA.books (OWNER_ID bigint, id bigint not null, author varchar(255), title varchar(255), primary key (id));
create table MY_SCHEMA.persons (id bigint not null, email varchar(255), name varchar(255), primary key (id));
create table MY_SCHEMA.students (id bigint not null, major varchar(255), minor varchar(255), primary key (id));
create table MY_SCHEMA.teachers (id bigint not null, labName varchar(255), primary key (id));
alter table if exists MY_SCHEMA.books add constraint FKf7vn3m4vhxd4sksjasu6qy1c foreign key (OWNER_ID) references MY_SCHEMA.persons;
alter table if exists MY_SCHEMA.students add constraint FK9nqs0pkter5l6no6n9v93uyau foreign key (id) references MY_SCHEMA.persons;
alter table if exists MY_SCHEMA.teacher add constraint FKdtjc3so567ncriqw09hahp1ay foreign key (id) references MY_SCHEMA.persons;

