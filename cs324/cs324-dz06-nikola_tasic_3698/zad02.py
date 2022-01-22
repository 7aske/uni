from typing import List, Union


def average(items: List[Union[int, float]]):
	tmp = 0
	for item in items:
		if not isinstance(item, int) and not isinstance(item, float):
			# was lazy to type 4 different messages
			raise TypeError("List element cannot be of type {}".format(item.__class__.__name__))
		tmp += item
	return tmp / len(items)


def main():
	print(average([1, 2.2, 3, 4.1]))
	try:
		print(average([1, 'a', 3, 4.4]))
	except TypeError as e:
		print(e)
	try:
		print(average([1, 2, [], 4]))
	except TypeError as e:
		print(e)
	try:
		print(average([1, (), 3.1, 4]))
	except TypeError as e:
		print(e)
	try:
		print(average([1.424, {}, 3, 4]))
	except TypeError as e:
		print(e)


if __name__ == '__main__':
	main()
