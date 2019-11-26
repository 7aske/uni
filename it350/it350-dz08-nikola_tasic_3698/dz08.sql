set foreign_key_checks=0;

create database if not exists dz08;

use dz08;

drop table if exists pacijent;
create table if not exists pacijent (
    pacijent_id int(11) not null auto_increment,
    ime varchar(64) not null,
    prezime varchar(64) not null,
    jmbg varchar(13) not null unique,
    adresa varchar(64) not null,
    telefon varchar (32) not null,
    constraint pacijent_pk primary key (pacijent_id),
    doktor_id int(11)
);
describe pacijent;


alter table pacijent add constraint foreign key (doktor_id) references doktor (doktor_id);


drop table if exists doktor;
create table if not exists doktor (
    doktor_id int(11) not null auto_increment,
    ime varchar(64) not null,
    prezime varchar(64) not null,
    jmbg varchar(13) not null unique,
    spec varchar(32) not null,
    constraint doktor_pk primary key (doktor_id)
);
describe doktor;


drop table if exists medikament;
create table if not exists medikament (
    medikament_id int(11) auto_increment,
    naziv varchar(64) not null,
    proizvodjac varchar(64) not null,
    sifra varchar(13) not null,
    constraint medikament_pk primary key (medikament_id),
    proizvodjac_id int(11)
);
describe medikament;


alter table medikament add constraint foreign key (proizvodjac_id) references proizvodjac (proizvodjac_id);


drop table if exists bolest;
create table if not exists bolest (
    bolest_id int(11) not null auto_increment,
    naziv varchar(64) not null,
    opis varchar(1024) not null,
    slika blob not null,
    constraint bolest_pk primary key (bolest_id)
);

drop table if exists proizvodjac;
create table if not exists proizvodjac (
    proizvodjac_id int(11) not null auto_increment,
    naziv varchar(64) not null,
    adresa varchar(64) not null,
    telefon varchar(32) not null,
    constraint proizvodjac_pk primary key (proizvodjac_id)
);
describe proizvodjac;


drop table if exists boluje_od;
create table if not exists boluje_od (
    boluje_od_id int(11) not null auto_increment,
    datum_dijagnoze varchar(32) not null,
    constraint boluje_od_pk primary key (boluje_od_id),
    pacijent_id int(11),
    bolest_id int(11)
);
describe boluje_od;

alter table boluje_od add constraint foreign key (pacijent_id) references pacijent (pacijent_id);
alter table boluje_od add constraint foreign key (bolest_id) references bolest (bolest_id);

alter table bolest drop slika;
describe bolest;

alter table doktor add legitimacija varchar(16) unique after jmbg;
describe doktor;

drop table if exists izabrani_doktor;
create table if not exists izabrani_doktor (
    izabrani_doktor_id integer(11) auto_increment,
    constraint izabrani_doktor_pk primary key (izabrani_doktor_id),
    doktor_id integer(11),
    pacijent_id integer(11),
    constraint foreign key (doktor_id) references doktor(doktor_id),
    constraint foreign key (pacijent_id) references pacijent(pacijent_id)
);

drop table if exists pregled;
create table if not exists pregled (
    pregled_id integer(11) auto_increment,
    constraint pregled_pk primary key (pregled_id),
    datum datetime,
    doktor_id integer(11),
    pacijent_id integer(11),
    constraint foreign key (doktor_id) references doktor(doktor_id),
    constraint foreign key (pacijent_id) references pacijent(pacijent_id)
);

drop table if exists terapija;
create table if not exists terapija (
    terapija_id integer(11) auto_increment,
    constraint terapija_pk primary key (terapija_id),
    opis varchar(4096),
    datum datetime,
    trajanje integer(3),
    doktor_id integer(11),
    pacijent_id integer(11),
    constraint foreign key (doktor_id) references doktor(doktor_id),
    constraint foreign key (pacijent_id) references pacijent(pacijent_id)
);

set foreign_key_checks=1; 

