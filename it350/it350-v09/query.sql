use studentska;
select indeks, ime, prezime from student where indeks <= 2000 and indeks >= 1500 or indeks = 4034;
select indeks, ime, prezime from student where ime like 'M%';
select indeks, ime, prezime from student where ime like 'Mil%an';
select indeks, ime, prezime from student where ime between 'M%' and 'Z%' and prezime between 'S%' and 'Z%';

select indeks, ime, prezime from student where ime in ('Milan', 'Marko', 'Milica');

select predmet_id, avg(ocena) as avgocena from overa where predmet_id like 'CS%' group by predmet_id order by avgocena desc;
