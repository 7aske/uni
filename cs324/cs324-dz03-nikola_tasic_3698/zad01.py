exams = {}
def main():
	num_exams = int(input("Koliko imate polozenih ispita?: ").strip())
	for i in range(num_exams):
		exam = input("Polozeni ispit broj {} (sifra): ".format(i+1)).strip()
		grade = input("Ocena: ").strip()
		exams[exam] = grade
	query = input("Za koji ispit zelite informacije?: ").strip()

	grade = exams.get(query)

	if grade is not None:
		print("Polozili ste ispit {} sa ocenom {}".format(query, grade))
	else:
		print("Niste polozili ispit {}".format(query))

if __name__ == '__main__':
	main()
