def zad01():
	use_inp = False

	# @formatter:off
	first_name = input("Ime: ")             if use_inp else "Nikola"
	last_name  = input("Prezime: ")         if use_inp else "Tasic"
	birth_year = input("Godina rodjenja: ") if use_inp else "1995"
	index      = input("Indeks: ")          if use_inp else "3698"
	# @formatter:on

	course_grades = {}
	while True:
		course = input("Predmet: ")
		if course.strip() == "":
			break
		course_grades[course] = int(input("Ocena: "))

	grades = course_grades.values()

	avg_grade = 0
	if len(grades) > 0:
		avg_grade = sum(grades) / len(grades)

	output = "{} {}, {}. rodjen/a {} ima trenutno prosecnu ocenu {}" \
		.format(first_name, last_name, index, birth_year, avg_grade)

	print(output)
	print(first_name, last_name + ",", index, "rodjen/a", birth_year + ".", "ima trenutno prosecnu ocenu", avg_grade)


def main():
	zad01()


if __name__ == '__main__':
	main()
