import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
from matplotlib import pyplot as plt


def log_regression(x, y, percentage):
	x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=percentage, random_state=0)

	logistic_regression = LogisticRegression()
	logistic_regression.fit(x_train, y_train)

	y_prediction = logistic_regression.predict(x_test)

	data_frame2 = pd.DataFrame(x_test)
	data_frame2["Data"] = x_test
	data_frame2["Predicted"] = y_prediction

	errors = np.sum(np.where(y_test != y_prediction, 1, 0))
	print(y_prediction)
	print(f"Number of errors -> {errors}")

	plt.scatter(data_frame2["Data"], y_test, color='red')
	plt.plot(data_frame2["Data"], y_prediction, color='blue')
	plt.show()


def main():
	data = pd.read_csv("dataset.csv")

	x = data[["cs_101_ocena"]]
	y = data["cs_115_polozen"]

	log_regression(x, y, 0.25)
	log_regression(x, y, 0.1)

	x = data[["cs115_izostanci"]]
	y = data["cs_115_polozen"]

	log_regression(x, y, 0.25)
	log_regression(x, y, 0.1)

	# 3. deo
	x = data[["cs_101_ocena", "it_101_ocena", "ma_101_ocena", "cs115_izostanci"]]
	y = data["cs_115_polozen"]

	log_regression(x, y, 0.25)
	log_regression(x, y, 0.1)


if __name__ == '__main__':
	main()
