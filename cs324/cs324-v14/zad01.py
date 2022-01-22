import time


def perf(func):
	def log(*args, **kwargs):
		begin = time.time()

		func(*args, **kwargs)

		end = time.time()
		print("exec time of", func.__name__, "is", end - begin)

	return log


def fib(n):
	if n < 2:
		return 1
	return fib(n - 2) + fib(n - 1)


@perf
def test():
	fib(30)


def main():
	test()


if __name__ == '__main__':
	main()
