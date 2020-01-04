import mysql.connector
import datetime
from pprint import pprint
import matplotlib.pyplot as plt

sql1 = ' select library.id_library, book_specimen.id_book_specimen, library.name from book_specimen join library on library.id_library = book_specimen.id_library;'
sql2 = 'select due_date, library.id_library, library.name, bs.id_book_specimen from reader join rent on reader.id_reader = rent.id_reader join book_specimen bs on bs.id_book_specimen = rent.id_book_specimen join library on library.id_library = bs.id_library;'

base = datetime.datetime.today()
date_list = [base - datetime.timedelta(days=x) for x in range(366)]


def main():
	lib_data = {}
	mydb = mysql.connector.connect(
		host="127.0.0.1",
		user="nik",
		passwd="nik",
		database="library"
	)

	cur = mydb.cursor()
	cur.execute(sql1)
	for x in cur:
		if x[0] not in lib_data.keys():
			lib_data[x[0]] = {}
			lib_data[x[0]]["books"] = []
		lib_data[x[0]]["books"].append(x[1])

		if "name" not in lib_data[x[0]].keys():
			lib_data[x[0]]["name"] = x[2]

	for k, v in lib_data.items():
		v["count"] = len(v["books"])
		v["returns"] = [0] * 366
		v["curr"] = [0] * 366

	cur.reset()

	cur = mydb.cursor()
	cur.execute(sql2)

	for x in cur:
		lib_data[x[1]]["returns"][x[0].timetuple().tm_yday - 1] += 1
		for y in range(len(lib_data[x[1]]["curr"])):
			if x[0].timetuple().tm_yday - 1 > y:
				lib_data[x[1]]["curr"][y] += 1

	pprint(lib_data)

	for k, item in lib_data.items():
		# m = max(item["curr"])
		# for y in range(len(item["curr"])):
		# 	item["curr"][y] = m - item["curr"][y]
		save_png(item, k)


def save_png(data, idx):
	plt.bar(date_list, data["returns"])
	plt.bar(date_list, data["curr"])
	plt.yticks([0, 1, 2, 3])
	plt.legend([data["name"]])
	plt.ylabel("Books Returned")
	plt.xlabel("Date")
	plt.savefig("library{}.png".format(idx), facecolor='w', edgecolor='w',
	            orientation='portrait', format=None)
	plt.clf()


if __name__ == '__main__':
	main()
