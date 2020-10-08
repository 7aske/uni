# ne razumem kako da uradim zadatak, ovo je samo prikaz korisnika po biblioteci
select id_library, `Library Name`, `Readers`
from (select reader.id_reader, library.id_library, library.name as `Library Name`, count(reader.id_reader) as 'Readers'
      from reader
               join rent on reader.id_reader = rent.id_reader
               join book_specimen bs on bs.id_book_specimen = rent.id_book_specimen
               join library on library.id_library = bs.id_library
      group by library.id_library) as `Readers per Library`
group by id_library order by `Readers` desc;
