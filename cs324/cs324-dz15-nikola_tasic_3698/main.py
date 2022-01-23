def is_prime(n):
	if n == 1:
		return False
	if n == 2 or n == 3:
		return True

	if n % 2 == 0:
		return False

	# n ** .5 menja math.sqrt
	for i in range(3, int(n ** .5) + 1, 2):
		if n % i == 0:
			return False
	return True


def primes_interval(a, b):
	return list(filter(is_prime, [x for x in range(a, b + 1)]))


def main():
	def square(x): return pow(x, 2)

	squared_primes = list(map(square, primes_interval(1, 1000)))
	print(squared_primes)


if __name__ == '__main__':
	main()
