-- 12. Napisati upit kojim se prikazuju podaci o predmetu koji je položilo najviše studenata.

use studentska;

select predmet.predmet_id, count(overa.ocena) from predmet join overa on predmet.predmet_id = overa.predmet_id and overa.ocena > 5 group by predmet.predmet_id; 
