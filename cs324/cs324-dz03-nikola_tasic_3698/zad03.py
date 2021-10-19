import random
from itertools import islice

def main():
	index = 3698
	m = index // 3

	start = int(str(index)[0:2])
	end = int(str(index)[2:])

	data = dict((i, random.uniform(start, end)) for i, _ in enumerate(range(m)))
	print(list(islice(data.items(), index % 3)))


if __name__ == '__main__':
	main()
