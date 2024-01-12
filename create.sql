create sequence MY_SCHEMA.MY_ID_SEQUENCE start with 1 increment by 1;
create sequence MY_SCHEMA.MY_ID_SEQUENCE start with 1 increment by 50;
create table MY_SCHEMA.books (OWNER_ID numeric(19,2), id bigint not null, author varchar(255), title varchar(255), primary key (id));
create table MY_SCHEMA.persons (ID numeric(19,2) not null, email varchar(255), name varchar(255), primary key (ID));
create table MY_SCHEMA.students (ID numeric(19,2) not null, major varchar(255), minor varchar(255), primary key (ID));
create table MY_SCHEMA.teachers (ID numeric(19,2) not null, labName varchar(255), primary key (ID));
alter table if exists MY_SCHEMA.books add constraint FKf7vn3m4vhxd4sksjasu6qy1c foreign key (OWNER_ID) references MY_SCHEMA.persons;
alter table if exists MY_SCHEMA.students add constraint FKhy31y17wryimnvqptyg3j2tfl foreign key (ID) references MY_SCHEMA.persons;
alter table if exists MY_SCHEMA.teachers add constraint FKqqsrcjrffcf9bhlpmvkfirers foreign key (ID) references MY_SCHEMA.persons;
