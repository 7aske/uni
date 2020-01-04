select top_libraries.id_library,
       name,
       street,
       number,
       count(top_libraries.id_library) as `No. Employees`,
       `Title Count`
from (select id_library, name, street, number, max(`Title Count`) as `Title Count`
      from (select *, count(id_library) as `Title Count`
            from (select library.id_library, library.name
                  from library
                           join book_specimen bs on library.id_library = bs.id_library
                           join book b on bs.id_book = b.id_book
                  group by isbn, library.id_library
                  order by library.id_library) as unique_books
            group by name
            order by `Title Count` desc) as libraries
               join address on address.id_address = libraries.id_library) as top_libraries
         join employee on top_libraries.id_library = employee.id_library
group by employee.id_library;
