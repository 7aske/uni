import sqlite3
import pandas as pd
from datetime import datetime


def add_book(conn):
	katBroj = input("Kat broj: ")
	naslov = input("Naslov: ")
	izdavac = input("Izdavac: ")
	godinaIzdavanja = input("Godina izdavanja: ")
	if int(godinaIzdavanja.rstrip(".")) > datetime.now().year:
		raise ValueError("Godina izdavanja ne moze da bude u budućnosti")
	book_values = (katBroj, naslov, izdavac, godinaIzdavanja)

	conn.execute("insert into knjige (katBroj, naslov, izdavac, godinaIzdavanja, izdata) values (?, ?, ?, ?, 0)",
	             book_values)
	conn.commit()


def rent_book(conn):
	katBroj = input("Kat broj: ")

	conn.execute("update knjige set izdata = 1 where katBroj = ?", (katBroj,))
	conn.commit()


def read_books(conn):
	df = pd.read_sql("select * from knjige", con=conn)
	df.to_csv("izdate_knjige.csv", columns=("naslov", "izdavac", "godinaIzdavanja"))
	print(df)


def main():
	conn = sqlite3.connect("biblioteka.db")
	conn.execute(
		"create table if not exists knjige (katBroj int, naslov text, izdavac text, godinaIzdavanja int, izdata bool)")

	while True:
		try:
			command = input("Unesite komandu (dodaj, izdaj, procitaj, izadji): ")
			if command == "dodaj":
				add_book(conn)
			elif command == "izdaj":
				rent_book(conn)
			elif command == "procitaj":
				read_books(conn)
			elif command == "izadji":
				break
		except KeyboardInterrupt:
			break


if __name__ == '__main__':
	main()
