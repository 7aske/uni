select book_authors.id_author, person.first_name, person.last_name, `Books Published`
from (select author.id_author, author.id_person, count(books.id_book) as `Books Published`
      from (select id_author, author_book.id_book, publish_date
            from author_book
                     join book b on author_book.id_book = b.id_book
            where (b.publish_date between cast('1900-01-01' as date) and curdate())) as `books`
               join author on books.id_author = author.id_author
      group by author.id_author) as book_authors
         join person on person.id_person = book_authors.id_person
where `Books Published` > 10;

