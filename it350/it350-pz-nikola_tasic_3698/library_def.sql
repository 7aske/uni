/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     12/26/2019 1:26:11 AM                        */
/*==============================================================*/

drop database if exists library;
create database library;
use library;


drop table if exists address;

drop table if exists author;

drop table if exists author_book;

drop table if exists book;

drop table if exists book_specimen;

drop table if exists employee;

drop table if exists library;

drop table if exists municipality;

drop table if exists person;

drop table if exists reader;

drop table if exists region;

drop table if exists rent;

/*==============================================================*/
/* Table: address                                               */
/*==============================================================*/
create table address
(
   id_address           int not null auto_increment,
   id_municipality      int not null,
   street               varchar(255) not null,
   number               varchar(8) not null,
   primary key (id_address)
);

/*==============================================================*/
/* Table: author                                                */
/*==============================================================*/
create table author
(
   id_author            int not null auto_increment,
   id_person            int not null,
   description          varchar(4096) not null,
   primary key (id_author)
);

/*==============================================================*/
/* Table: author_book                                           */
/*==============================================================*/
create table author_book
(
   id_author_book       int not null,
   id_book              int not null,
   id_author            int not null,
   primary key (id_author_book)
);

/*==============================================================*/
/* Table: book                                                  */
/*==============================================================*/
create table book
(
   id_book              int not null auto_increment,
   isbn                 varchar(32) not null,
   name                 varchar(255) not null,
   publish_date         date not null,
   primary key (id_book)
);

/*==============================================================*/
/* Table: book_specimen                                         */
/*==============================================================*/
create table book_specimen
(
   id_book_specimen     int not null auto_increment,
   id_library           int,
   id_book              int not null,
   book_serial          varchar(16) not null,
   primary key (id_book_specimen)
);

/*==============================================================*/
/* Table: employee                                              */
/*==============================================================*/
create table employee
(
   id_employee          int not null auto_increment,
   id_person            int not null,
   id_library           int not null,
   position             varchar(32) not null,
   primary key (id_employee)
);

/*==============================================================*/
/* Table: library                                               */
/*==============================================================*/
create table library
(
   id_library           int not null auto_increment,
   id_address           int not null,
   name                 varchar(255) not null,
   primary key (id_library)
);

/*==============================================================*/
/* Table: municipality                                          */
/*==============================================================*/
create table municipality
(
   id_municipality      int not null auto_increment,
   id_region            int not null,
   name                 varchar(255) not null,
   primary key (id_municipality)
);

/*==============================================================*/
/* Table: person                                                */
/*==============================================================*/
create table person
(
   id_person            int not null auto_increment,
   first_name           varchar(255) not null,
   last_name            varchar(255) not null,
   jmbg                 varchar(13),
   primary key (id_person)
);

/*==============================================================*/
/* Table: reader                                                */
/*==============================================================*/
create table reader
(
   id_user              int not null auto_increment,
   username             varchar(255) not null,
   password             varchar(255) not null,
   primary key (id_user)
);

/*==============================================================*/
/* Table: region                                                */
/*==============================================================*/
create table region
(
   id_region            int not null auto_increment,
   name                 varchar(255) not null,
   primary key (id_region)
);

/*==============================================================*/
/* Table: rent                                                  */
/*==============================================================*/
create table rent
(
   id_rent              int not null auto_increment,
   id_user              int not null,
   id_book_specimen     int not null,
   due_date             date not null,
   primary key (id_rent)
);

alter table address add constraint fk_relationship_2 foreign key (id_municipality)
      references municipality (id_municipality) on delete restrict on update restrict;

alter table author add constraint fk_inheritance_2 foreign key (id_person)
      references person (id_person) on delete restrict on update restrict;

alter table author_book add constraint fk_author_book foreign key (id_book)
      references book (id_book) on delete restrict on update restrict;

alter table author_book add constraint fk_author_book2 foreign key (id_author)
      references author (id_author) on delete restrict on update restrict;

alter table book_specimen add constraint fk_relationship_6 foreign key (id_book)
      references book (id_book) on delete restrict on update restrict;

alter table book_specimen add constraint fk_relationship_9 foreign key (id_library)
      references library (id_library) on delete restrict on update restrict;

alter table employee add constraint fk_inheritance_1 foreign key (id_person)
      references person (id_person) on delete restrict on update restrict;

alter table employee add constraint fk_relationship_4 foreign key (id_library)
      references library (id_library) on delete restrict on update restrict;

alter table library add constraint fk_relationship_3 foreign key (id_address)
      references address (id_address) on delete restrict on update restrict;

alter table municipality add constraint fk_relationship_1 foreign key (id_region)
      references region (id_region) on delete restrict on update restrict;

alter table rent add constraint fk_relationship_7 foreign key (id_book_specimen)
      references book_specimen (id_book_specimen) on delete restrict on update restrict;

alter table rent add constraint fk_relationship_8 foreign key (id_user)
      references reader (id_user) on delete restrict on update restrict;

