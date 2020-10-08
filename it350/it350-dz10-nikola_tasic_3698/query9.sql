-- 9. Napisati upit kojim se prikazuje prosečna ocena na svakom predmetu na fakultetu.
-- Prikazati id predmet, naziv I prosečnu ocenu koju su dobili svi student koji su predmet
-- položili. Ne računati one koji su predmet pali.

use studentska;

select predmet.predmet_id, avg(overa.ocena), predmet.naziv from predmet join overa on predmet.predmet_id = overa.predmet_id and ocena > 5 group by predmet.predmet_id;

