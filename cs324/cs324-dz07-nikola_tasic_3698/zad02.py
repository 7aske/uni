import sqlite3
import json


class Predmet:
	def __init__(self, sifra, puno_ime, profesor, godina_studiranja) -> None:
		super().__init__()
		self.godina_studiranja = godina_studiranja
		self.profesor = profesor
		self.puno_ime = puno_ime
		self.sifra = sifra

	def __repr__(self) -> str:
		return "{} {} {} {}".format(self.sifra, self.puno_ime, self.profesor, self.godina_studiranja)


def main():
	conn = sqlite3.connect(":memory:")
	c = conn.cursor()
	create_table_sql = """
	create table if not exists predmeti (
		sifra varchar(8) primary key,
		punoIme varchar(64) not null,
		profesor varchar(64) not null,
		godinaStudiranja int not null
	);
	"""
	c.execute(create_table_sql)

	cs322 = Predmet("CS322", "Programiranje u C#", "Milos Lomovic", "4")
	cs324 = Predmet("CS324", "Skripting jezici", "Nemanja Zdravkovic", "4")
	cs225 = Predmet("CS225", "Operativni sistemi", "Nemanja Zdravkovic", "4")
	it381 = Predmet("IT381", "Bezbednost i Zastita informacija", "Milena Bogdanovic", "4")
	insert_course(cs322, c)
	insert_course(cs324, c)
	insert_course(cs225, c)
	insert_course(it381, c)

	courses = find_by_prof("Nemanja Zdravkovic", c)
	for course in courses:
		print(course)


def find_by_prof(prof, c):
	c.execute("select * from predmeti where profesor = ?;", (prof,))
	return list(map(lambda r: Predmet(*r), c.fetchall()))


def insert_course(predmet, c):
	c.execute("insert into predmeti values (?, ?, ?, ?)",
	          (predmet.sifra, predmet.puno_ime, predmet.profesor, predmet.godina_studiranja))


if __name__ == '__main__':
	main()
