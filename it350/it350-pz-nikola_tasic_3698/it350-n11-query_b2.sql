select library.name, municipality.name as 'Municipality', count(employee.id_employee) as 'Employees'
from employee
         join library on library.id_library = employee.id_library
         join address on address.id_address = library.id_address
         join municipality on address.id_municipality = municipality.id_municipality
group by library.id_library;


