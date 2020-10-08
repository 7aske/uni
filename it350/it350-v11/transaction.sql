set autocommit = 0;
start transaction;
begin work;
update angazovanje set profesor_id = 6 where predmet_id like 'IT350';
update angazovanje set profesor_id = 4 where predmet_id like 'IT370';
commit;
