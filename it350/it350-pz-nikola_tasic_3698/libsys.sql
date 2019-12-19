/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     12/19/2019 8:09:15 PM                        */
/*==============================================================*/


drop table if exists ADDRESS;

drop table if exists AUTHOR;

drop table if exists BOOK;

drop table if exists BOOK_SPECIMEN;

drop table if exists EMPLOYEE;

drop table if exists LIBRARY;

drop table if exists MUNICIPALITY;

drop table if exists PERSON;

drop table if exists READER;

drop table if exists REGION;

drop table if exists RELATIONSHIP_5;

drop table if exists RENT;

/*==============================================================*/
/* Table: ADDRESS                                               */
/*==============================================================*/
create table ADDRESS
(
   ID_ADDRESS           int not null auto_increment,
   ID_MUNICIPALITY      int not null,
   STREET               VBIN255 not null,
   NUMBER               varchar(8) not null,
   primary key (ID_ADDRESS)
);

/*==============================================================*/
/* Table: AUTHOR                                                */
/*==============================================================*/
create table AUTHOR
(
   ID_PERSON            int not null,
   FIRST_NAME           varchar(255) not null,
   LAST_NAME            varchar(255) not null,
   DESCRIPTION          varchar(4096) not null,
   primary key (ID_PERSON)
);

/*==============================================================*/
/* Table: BOOK                                                  */
/*==============================================================*/
create table BOOK
(
   ID_BOOK              int not null auto_increment,
   ISBN                 varchar(32) not null,
   NAME                 varchar(255) not null,
   PUBLISH_DATE         date not null,
   primary key (ID_BOOK)
);

/*==============================================================*/
/* Table: BOOK_SPECIMEN                                         */
/*==============================================================*/
create table BOOK_SPECIMEN
(
   ID_BOOK_SPECIMEN     int not null auto_increment,
   ID_LIBRARY           int,
   ID_BOOK              int not null,
   BOOK_SERIAL          varchar(16) not null,
   primary key (ID_BOOK_SPECIMEN)
);

/*==============================================================*/
/* Table: EMPLOYEE                                              */
/*==============================================================*/
create table EMPLOYEE
(
   ID_PERSON            int not null,
   ID_LIBRARY           int not null,
   FIRST_NAME           varchar(255) not null,
   LAST_NAME            varchar(255) not null,
   POSITION             varchar(32) not null,
   primary key (ID_PERSON)
);

/*==============================================================*/
/* Table: LIBRARY                                               */
/*==============================================================*/
create table LIBRARY
(
   ID_LIBRARY           int not null auto_increment,
   ID_ADDRESS           int not null,
   NAME                 varchar(255) not null,
   primary key (ID_LIBRARY)
);

/*==============================================================*/
/* Table: MUNICIPALITY                                          */
/*==============================================================*/
create table MUNICIPALITY
(
   ID_MUNICIPALITY      int not null auto_increment,
   ID_REGION            int not null,
   NAME                 varchar(255) not null,
   primary key (ID_MUNICIPALITY)
);

/*==============================================================*/
/* Table: PERSON                                                */
/*==============================================================*/
create table PERSON
(
   ID_PERSON            int not null auto_increment,
   FIRST_NAME           varchar(255) not null,
   LAST_NAME            varchar(255) not null,
   primary key (ID_PERSON)
);

/*==============================================================*/
/* Table: READER                                                */
/*==============================================================*/
create table READER
(
   ID_USER              int not null auto_increment,
   USERNAME             varchar(255) not null,
   PASSWORD             varchar(255) not null,
   primary key (ID_USER)
);

/*==============================================================*/
/* Table: REGION                                                */
/*==============================================================*/
create table REGION
(
   ID_REGION            int not null auto_increment,
   NAME                 varchar(255) not null,
   primary key (ID_REGION)
);

/*==============================================================*/
/* Table: RELATIONSHIP_5                                        */
/*==============================================================*/
create table RELATIONSHIP_5
(
   ID_BOOK              int not null,
   ID_PERSON            int not null,
   primary key (ID_BOOK, ID_PERSON)
);

/*==============================================================*/
/* Table: RENT                                                  */
/*==============================================================*/
create table RENT
(
   ID_RENT              int not null auto_increment,
   ID_USER              int not null,
   ID_BOOK_SPECIMEN     int not null,
   DUE_DATE             date not null,
   primary key (ID_RENT)
);

alter table ADDRESS add constraint FK_RELATIONSHIP_2 foreign key (ID_MUNICIPALITY)
      references MUNICIPALITY (ID_MUNICIPALITY) on delete restrict on update restrict;

alter table AUTHOR add constraint FK_INHERITANCE_2 foreign key (ID_PERSON)
      references PERSON (ID_PERSON) on delete restrict on update restrict;

alter table BOOK_SPECIMEN add constraint FK_RELATIONSHIP_10 foreign key (ID_LIBRARY)
      references LIBRARY (ID_LIBRARY) on delete restrict on update restrict;

alter table BOOK_SPECIMEN add constraint FK_RELATIONSHIP_7 foreign key (ID_BOOK)
      references BOOK (ID_BOOK) on delete restrict on update restrict;

alter table EMPLOYEE add constraint FK_INHERITANCE_1 foreign key (ID_PERSON)
      references PERSON (ID_PERSON) on delete restrict on update restrict;

alter table EMPLOYEE add constraint FK_RELATIONSHIP_4 foreign key (ID_LIBRARY)
      references LIBRARY (ID_LIBRARY) on delete restrict on update restrict;

alter table LIBRARY add constraint FK_RELATIONSHIP_3 foreign key (ID_ADDRESS)
      references ADDRESS (ID_ADDRESS) on delete restrict on update restrict;

alter table MUNICIPALITY add constraint FK_RELATIONSHIP_1 foreign key (ID_REGION)
      references REGION (ID_REGION) on delete restrict on update restrict;

alter table RELATIONSHIP_5 add constraint FK_RELATIONSHIP_5 foreign key (ID_BOOK)
      references BOOK (ID_BOOK) on delete restrict on update restrict;

alter table RELATIONSHIP_5 add constraint FK_RELATIONSHIP_6 foreign key (ID_PERSON)
      references AUTHOR (ID_PERSON) on delete restrict on update restrict;

alter table RENT add constraint FK_RELATIONSHIP_8 foreign key (ID_BOOK_SPECIMEN)
      references BOOK_SPECIMEN (ID_BOOK_SPECIMEN) on delete restrict on update restrict;

alter table RENT add constraint FK_RELATIONSHIP_9 foreign key (ID_USER)
      references READER (ID_USER) on delete restrict on update restrict;

