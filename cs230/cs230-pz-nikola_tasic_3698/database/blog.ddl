create database if not exists blog;
use blog;
create schema blog collate utf8mb4_unicode_ci;

create table blog_post
(
    id_blog_post int auto_increment
        primary key,
    body         text               not null,
    date_posted  date               null,
    author       varchar(32)        not null,
    preview      text               not null,
    title        varchar(128)       not null,
    slug         varchar(32)        not null,
    published    smallint default 1 null,
    constraint blog_post_slug_uindex
        unique (slug)
);

create table post_comment
(
    id_post_comment       int auto_increment
        primary key,
    id_blog_post          int         not null,
    author                varchar(32) not null,
    body                  text        not null,
    date_posted           date        null,
    blogPost_id_blog_post bigint      null,
    constraint fk_comment
        foreign key (id_blog_post) references blog_post (id_blog_post)
);

create table tag
(
    id_tag int auto_increment
        primary key,
    name   varchar(32) not null,
    constraint tag_name_uindex
        unique (name)
);

create table post_tag
(
    id_post_tag  int auto_increment
        primary key,
    id_blog_post int not null,
    id_tag       int not null
);

