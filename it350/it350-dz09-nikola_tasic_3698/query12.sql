-- • 12. Napisati upit kojim se prikazuju podaci o Internet studentima. Prikazati indeks, ime, prezime I datum rodjenja za svakog internet studenta.
select indeks, ime, prezime, datum_rodjenja from student where tip_studiranja = "Internet";
