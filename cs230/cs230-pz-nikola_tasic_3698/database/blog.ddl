/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     4/18/2020 11:50:03 AM                        */
/*==============================================================*/

drop table if exists blog_post;

drop table if exists post_comment;

drop table if exists post_tag;

drop table if exists tag;

/*==============================================================*/
/* Table: blog_post                                             */
/*==============================================================*/
create table blog_post
(
    id_blog_post int          not null auto_increment,
    body         text         not null,
    date_posted  date,
    author       varchar(32)  not null,
    title        varchar(128) not null,
    slug         varchar(32)  not null,
    preview      text         not null,
    primary key (id_blog_post)
);

/*==============================================================*/
/* Table: post_comment                                          */
/*==============================================================*/
create table post_comment
(
    id_post_comment int         not null auto_increment,
    id_blog_post    int         not null,
    author          varchar(32) not null,
    body            text        not null,
    date_posted     date,
    primary key (id_post_comment)
);

/*==============================================================*/
/* Table: post_tag                                              */
/*==============================================================*/
create table post_tag
(
    id_post_tag int         not null auto_increment,
    name        varchar(32) not null,
    primary key (id_post_tag)
);

/*==============================================================*/
/* Table: tag                                                   */
/*==============================================================*/
create table tag
(
    id_blog_post int not null,
    id_post_tag  int not null,
    primary key (id_blog_post, id_post_tag)
);

alter table post_comment
    add constraint fk_comment foreign key (id_blog_post)
        references blog_post (id_blog_post) on delete restrict on update restrict;

alter table tag
    add constraint fk_tag foreign key (id_blog_post)
        references blog_post (id_blog_post) on delete restrict on update restrict;

alter table tag
    add constraint fk_tag2 foreign key (id_post_tag)
        references post_tag (id_post_tag) on delete restrict on update restrict;

