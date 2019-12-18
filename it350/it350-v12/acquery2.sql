 select * from route where distance > (select avg(distance) from route);

