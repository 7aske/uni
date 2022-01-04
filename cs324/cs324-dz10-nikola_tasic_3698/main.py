from collections import Counter

import pandas as pd
from matplotlib import pyplot as plt


def main():
	# za dodavanje podataka
	# while True:
	# 	subj = input("Sifra predmeta: ")
	# 	grade = input("Ocena: ")
	# 	if not subj or not grade:
	# 		break
	# 	subjects["Sifra"].append(subj)
	# 	subjects["Ocena"].append(int(grade))

	# df = pd.DataFrame(data)
	# df.to_csv("Student_izvestaj.csv")

	data = pd.read_csv("Student_izvestaj.csv")

	counted = Counter(data["Ocena"])

	grades = []
	num_grades = []
	avg_grades = []
	fig = plt.figure()
	counter = 0
	suma = 0

	for number in data["Ocena"]:
		suma += number
		counter += 1
		avg_grades.append(suma / counter)

	subjects = data["Sifra"].copy()
	for subj in range(len(subjects)):
		subjects[subj] = subjects[subj][:2]

	grade_counter = Counter(subjects)

	for grade, number in counted.items():
		num_grades.append(number)
		grades.append(grade)

	fig_1 = fig.add_subplot(2, 2, 1)
	fig_1.pie(num_grades, labels=grades, autopct="%.1f%%", startangle=0,
	          wedgeprops={"edgecolor": "white", "linewidth": 2})

	fig_2 = fig.add_subplot(2, 2, 2)
	plt.barh(list(grade_counter.keys()), list(grade_counter.values()))
	plt.ylabel("Grupa Predmeta")
	plt.xlabel("Broj ocena")

	# linijski plot
	figura_3 = fig.add_subplot(2, 2, (3, 4))
	plt.plot(range(len(data["Ocena"])), avg_grades, "g-", label="Prosek")
	plt.plot(range(len(data["Ocena"])), data["Ocena"], "ro", label="Ocena")

	plt.savefig("Student_izvestaj.png")
	plt.show()


if __name__ == '__main__':
	main()
