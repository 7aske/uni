def zad01():
	a = [True, False]
	b = [True, False]
	c = [True, False]

	fmt_x = "({a} or {b}) and not {c}"

	print(fmt_x)
	print("| a   | b   | c   | x   |")
	for a_val in a:
		for b_val in b:
			for c_val in c:
				x = eval(fmt_x.format(a=a_val, b=b_val, c=c_val))
				print("|{:<5}|{:<5}|{:<5}|{:<5}|".format(str(a_val), str(b_val), str(c_val), str(x)))


def zad02():
	index = 3698
	for n in str(index):
		print("{:0>4}".format(str(bin(int(n)))[2:]), end=" ")
	print()


import math
fact = math.factorial
def zad03():
	eq = "13/8 + ((-1**({n} + 1)) * fact((2 * {n} + 1)))/(4**(2 * {n} + 3) * fact({n}) * fact(({n} + 2)))"
	x = 100
	val = 0
	for n in range(0, x + 1):
		val += eval(eq.format(n=n))
	print(val)


def main():
	zad01()
	zad02()
	zad03()


if __name__ == '__main__':
	main()
