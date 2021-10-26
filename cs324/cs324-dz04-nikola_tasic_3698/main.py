import random
from typing import Union
import time


class Zad01:
	def __init__(self):
		print(self.zad01func("Nikola", 3698))
		print(self.zad01func("Nikol", 3698))

	@staticmethod
	def zad01func(name: str, index: int) -> [Union[int, float]]:
		if len(name) % 2 == 0:
			lst = [random.randint(0, index) for _ in range(index)]
		else:
			lst = [random.uniform(-int(str(index)[:2]), int(str(index)[2:])) for _ in range(index)]
		lst.sort()

		return lst


# Ne vidim slicnosti izmedju ova dva pristupa izracunavanja.

# Razlike su u tome sto rekurzivni pristup zauzima mnogo vise memorije i procesorskog
# vremena zbog cestih poziva funkcija i samim tim manje performantan u odnosu
# na iterativni pristup
class Zad02():
	def __init__(self) -> None:
		start = time.time_ns()
		print(self.fib_rec(10))
		end = time.time_ns()
		print("rec took " + str(end - start) + "ns")
		start = time.time_ns()
		print(self.fib_iter(10))
		end = time.time_ns()
		print("iter took " + str(end - start) + "ns")

	@staticmethod
	def fib_rec(n: int):
		if n == 1 or n == 0:
			return 1
		return Zad02.fib_rec(n - 1) + Zad02.fib_rec(n - 2)

	@staticmethod
	def fib_iter(n: int):
		n1 = 0
		n2 = 1
		for x in range(n):
			n2_tmp = n2
			n2 = n1 + n2
			n1 = n2_tmp
		return n2


def main():
	Zad01()
	Zad02()


if __name__ == '__main__':
	main()
