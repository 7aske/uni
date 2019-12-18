create or replace user temp identified by 'temp';
grant select on studentska.* to 'temp'@'localhost' identified by 'temp';
grant insert on studentska.student to 'temp'@'localhost' identified by 'temp';
grant all on *.* to 'temp'@'localhost' identified by 'temp' with grant option;
