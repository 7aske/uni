class RandomNiz(list):
	def __init__(self, number_type, elements):
		super().__init__()
		self.number_type = number_type
		self.extend(elements)

	def sort_asc(self):
		self.sort()

	def sort_desc(self):
		self.sort(reverse=True)

	def sort_zig_zag(self):
		self.sort()
		for i in range(len(self)):
			pass

	def __repr__(self):
		return "RandomNiz('{}', {})".format(self.number_type, super(RandomNiz, self).__repr__())


def main():
	niz1 = RandomNiz("i", [1, 2, 3, 4, 5, 6])
	print(niz1)
	niz1.sort_zig_zag()
	print(niz1)
	pass


if __name__ == '__main__':
	main()
