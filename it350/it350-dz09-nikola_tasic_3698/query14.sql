-- 14. Napisati upit kojim se prikazuju podaci o svim studentima rodjenim u Mladenovcu ili Kraljevu.
select * from student where grad_rodjenja = 'Mladenovac' or grad_rodjenja = "Kraljevo";
