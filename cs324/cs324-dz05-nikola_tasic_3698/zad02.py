class Person:
	def __init__(self, first_name, last_name):
		self.first_name = first_name
		self.last_name = last_name


class Student(Person):
	def __init__(self, first_name: str, last_name: str, index: int, major: str, passed_exams: dict):
		super(Student, self).__init__(first_name, last_name)
		self.__index = index
		self.__major = major
		self.__passed_exams = passed_exams

	def is_same_curriculum(self, other: 'Student'):
		if self.__major is None or other.__major is None:
			return False
		return other.__major == self.__major

	def passed_exams(self):
		return list(self.__passed_exams.keys())

	def common_passed_exams(self, other: 'Student'):
		return list(set(self.passed_exams()).intersection(set(other.passed_exams())))


def main():
	me = Student("Nikola", "Tasic", 3698, "SE", {"IT255": 10, "CS101": 10})
	toma = Student("Tomislav", "Zivadinovic", 3948, "SE", {"CS101": 8})

	print("{} and {} do{} have the same major".format(me.first_name, toma.first_name,
	                                                  "" if me.is_same_curriculum(toma) else " not"))

	print("{} has passed {} exam(s)".format(me.first_name, len(me.passed_exams())))

	print("{} has passed {} exam(s)".format(toma.first_name, len(toma.passed_exams())))

	common_passed = me.common_passed_exams(toma)
	print("{} and {}{} have common passed exams {}".format(me.first_name, toma.first_name,
	                                                       "" if len(common_passed) > 0 else " don't", common_passed))


if __name__ == '__main__':
	main()
