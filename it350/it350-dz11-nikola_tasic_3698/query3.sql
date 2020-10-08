-- 3. Napraviti transakciju u kojoj ćete update-ovati ocene svim studentima u tabeli
-- OVERA tako da umesto ocene 5 bude stoji null vrednost.

use studentska;
set autocommit = 0;



start transaction;
select * from overa where ocena = 5;
update overa set ocena = null where ocena = 5;
commit;

-- reset values back to original
start transaction;
update overa set ocena = 5 where ocena IS NULL;
select * from overa where ocena IS NULL;
commit;

set autocommit = 1;
