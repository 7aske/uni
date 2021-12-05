import matplotlib.pyplot as plt


def avg(grades):
	return [sum(grades[0:x + 1]) / (x + 1) for x in range(len(grades))]


def main():
	grades = []

	while True:
		try:
			grade = int(input("Ocena: "))
			if 5 < grade <= 10:
				grades.append(grade)

		except ValueError:
			pass
		except EOFError:
			break
		except KeyboardInterrupt:
			break
	print(avg(grades))
	plt.plot(range(len(grades)), avg(grades), label="Ocene")
	plt.legend()
	plt.grid()
	plt.show()

	plt.pie(grades)
	plt.legend()
	plt.show()


if __name__ == '__main__':
	main()
