import numpy as np
import pandas as pd


def main():
	arr0 = np.random.randint(5, 11, 500)
	arr1 = np.random.randint(5, 11, 500)
	arr2 = np.random.randint(5, 11, 500)
	arr3 = np.random.randint(16, size=(500,))
	arr4 = np.random.randint(2, size=(500,))
	arr5 = np.random.randint(5, 11, 500)

	for i in range(500):
		if (arr0[i] in range(8, 11) or arr2[i] in range(9, 11)) and arr5[i] < 10:
			arr5[i] += 1
		if arr3[i] in range(5, 13) and arr5[i] > 5:
			arr5[i] -= 1
		elif arr3[i] in range(13, 16) and arr5[i] > 6:
			arr5[i] -= 2
		if arr4[i] == 0:
			arr5[i] = 5

	data = pd.DataFrame({"cs_101_ocena"   : arr0, "it_101_ocena": arr1, "ma_101_ocena": arr2,
	                     "cs115_izostanci": arr3, "cs_115_polozen": arr4, "cs_115_ocena": arr5})

	data.to_csv("dataset.csv", index=False)


if __name__ == '__main__':
	main()
