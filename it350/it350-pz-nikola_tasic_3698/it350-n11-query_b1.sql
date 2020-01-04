select library.name, municipality.name as 'Municipality', count(book_specimen.id_book_specimen) as 'Books'
from book_specimen
         join library on library.id_library = book_specimen.id_library
         join address on address.id_address = library.id_address
         join municipality on address.id_municipality = municipality.id_municipality
group by library.id_library;


