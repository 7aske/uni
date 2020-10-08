(select indeks, ime, prezime from student limit 3)
union all
(select indeks, ime, prezime from student limit 3);

select * from overa where indeks = 1478;


-- use 'in' instead of '=' where there are
-- more than one result row
select indeks, avg(ocena) from overa where indeks in (
    select indeks from student where ime = 'Milica'
)
group by indeks
order by avg(ocena);

select indeks, avg(ocena) from overa where indeks in (
    select indeks from student where ime = 'Milica'
);

select * from overa, student where overa.indeks = student.indeks;

select student.indeks, ime, ocena, prezime, overa.predmet_id, naziv from overa
join student on overa.indeks = student.indeks and overa.ocena > (
    select avg(ocena)
    from overa
    where overa.predmet_id like 
    group by overa.predmet_id
)
join predmet on overa.predmet_id = predmet.predmet_id

-- where (ime like 'Milica' or prezime like 'Stankovic')
-- where ocena > (
--     group by student.indeks
--     having avg(ocena) > 7.2
-- );


-- select avg(ocena), predmet_id
-- from overa
-- group by predmet_id;


