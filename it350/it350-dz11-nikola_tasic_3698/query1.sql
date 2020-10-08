-- 1. Napisati naredbu kojom se u tabelu STUDENT dodaje nova kolona Pol koja ne može
-- biti NULL. Definisati podrazumevanu vrednost ove kolone

use studentska;

alter table student add pol char not null default 'N';

describe student;

alter table student drop pol;
