import random


def main():
	n = 3698
	numbers = [random.randint(1, 10000) for _ in range(n)]
	numbers.sort(reverse=True)
	print(numbers)


if __name__ == '__main__':
	main()
