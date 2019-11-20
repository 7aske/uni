show databases;

drop database if exists  fabrika;
create database if not exists fabrika;

use fabrika;

drop table if exists users;

-- create table or replace primer (
create or replace table users (
    id int(18) primary key auto_increment,
    username varchar(32) not null unique
);
create or replace table roles (
    id int(18) primary key auto_increment,
    rolename varchar(32) not null unique
);

alter table users 
    add role_id int(18) not null;

alter table users 
    add constraint `roles_FK`
    foreign key (`role_id`) references `roles`(`id`)
    on update cascade
    on delete restrict;


-- alter table users
-- add ime varchar(255);

-- alter table drop ime;

-- alter table users change ime ime varchar(512) not null unique;
-- alter table primer change ime ime varchar(512) not null unique;

insert into roles (rolename) values ("admin");
insert into users (username, role_id) values ("admin", 1);



show databases;

describe users;
