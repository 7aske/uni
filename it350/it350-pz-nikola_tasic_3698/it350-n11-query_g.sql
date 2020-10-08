create view `Books Per Library` as
select id_library, books3.name as `Book Name`, isbn, `Library Name`, id_book, `Specimen Count`, id_address
from (select *, count(isbn) as `Specimen Count`
      from (select *
            from (select library.id_library,
                         library.id_address,
                         library.name as `Library Name`,
                         id_book_specimen,
                         bs.id_book   as idbook
                  from library
                           join book_specimen bs on library.id_library = bs.id_library
                           join book b on bs.id_book = b.id_book) as books) as books2
               join book b on b.id_book = books2.idbook
      group by isbn) as books3;

select `Book Name`, isbn, `Library Name`, `Specimen Count`, `Authors`, m.name as `Municipality Name`
from (select id_library, `Book Name`, isbn, `Library Name`, `Specimen Count`, count(id_author) as `Authors`, id_address
      from `Books Per Library`
               join author_book ab on `Books Per Library`.id_book = ab.id_book
      group by `Book Name`) as `Books and Authors`
         join address on address.id_address = `Books and Authors`.id_address
         join municipality m on address.id_municipality = m.id_municipality
where `Authors` > 1
  and m.name = 'Goteborg';
