-- 14. Napisati upit kojim se prikazuje za svakog studenta koliko je ispita položio. Prikazati
-- ime, prezime I broj položenih predmeta.


use studentska;

select count(overa.ocena) as broj_ispita, student.ime, student.prezime from overa join student on overa.indeks = student.indeks and ocena > 5 group by overa.indeks;

