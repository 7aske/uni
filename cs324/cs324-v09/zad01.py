import numpy as np
import matplotlib.pyplot as plt
import pandas as pd


def g(x):
	return np.exp((-1 / 2) * x) * np.cos((-2) * x)


def h(x):
	return np.exp((-3 / 2) * x) * np.sin(4 * x)


def main():
	x = np.linspace(0, 4, 501)
	y_func = g(x)
	h_func = h(x)
	plt.plot(x, y_func, 'r-', label="Cos talas")
	plt.plot(x, h_func, 'k--', label="Sin talas")
	plt.xlabel('$x$')
	plt.ylabel('$y(x)$')
	plt.xlim(0, 4)
	plt.ylim(-1, 1)
	plt.legend()
	plt.grid()
	plt.show()


if __name__ == '__main__':
	main()
