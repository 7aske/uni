select `Count`, library.name, street, number
from (select book_specimen.id_book_specimen,
             book_specimen.id_library,
             one_author.id_book,
             one_author.id_author,
             count(book_specimen.id_book_specimen) as `Count`
      from (select book.id_book, ab.id_author
            from book
                     join author_book ab on book.id_book = ab.id_book
                     join author a on ab.id_author = a.id_author) as one_author
               join book_specimen on one_author.id_book = book_specimen.id_book
      group by one_author.id_book) as libraries
         join library on libraries.id_library = library.id_library
         join address a2 on library.id_address = a2.id_address
         join author on author.id_author = libraries.id_author
         join person on person.id_person = author.id_person
where person.first_name = 'Robinet'
  and person.last_name = 'Negal'
  and `Count` >= 5;
