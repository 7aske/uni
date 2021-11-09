import csv

my_courses = ["CS322", "CS324", "CS225", "CS220", "IT381"]
this_semester = {}


def main():
	with open("svi_predmeti.csv", "r") as courses:
		reader = csv.reader(courses)
		lines = list(reader)
		codes = lines[0]
		names = lines[1]
		for i, code in enumerate(codes):
			if code in my_courses:
				this_semester[code] = names[i]

	with open("ovaj_semestar.csv", "w") as file:
		writer = csv.writer(file)
		writer.writerow(this_semester.keys())
		writer.writerow(this_semester.values())


if __name__ == '__main__':
	main()
