import numpy as np


def main():
	matrix0 = np.identity(3)
	np.fill_diagonal(matrix0, 1)
	print(matrix0)

	matrix1 = np.zeros((4, 4), dtype='int32')
	tens = np.full((2, 2), dtype='int32', fill_value=10)
	matrix1[1:-1, 1:-1] = tens
	print(matrix1)

	matrix2 = np.ones((5, 5), dtype='int32')
	zeros = np.zeros((3, 3), dtype='int32')
	zeros[1, 1] = 1
	matrix2[1:-1, 1:-1] = zeros
	print(matrix2)


if __name__ == '__main__':
	main()
