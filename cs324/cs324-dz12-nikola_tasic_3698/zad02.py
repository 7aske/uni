import numpy as np
import pandas as pd
from matplotlib import pyplot as plt
import sklearn
from sklearn import linear_model


def show_estimated_values(x, y, percent, col_name):
	x_train, x_test, y_train, y_test = sklearn.model_selection.train_test_split(x, y, train_size=percent)
	linear = linear_model.LinearRegression()
	linear.fit(x_train, y_train)

	predictions = linear.predict(x_test)

	new_data = pd.DataFrame(x_test, columns=[col_name])
	new_data['Y'] = y_test
	new_data['Prediction'] = predictions

	accuracy = linear.score(x_test, y_test)
	print(accuracy)

	plt.scatter(new_data[col_name], y_test, color='red')
	plt.plot(new_data[col_name], predictions, color='blue')
	plt.show()


def main():
	data = pd.read_csv("dataset.csv")
	tracked_data = ["cs_101_ocena", "cs_115_ocena"]
	tracked_extracted = data[tracked_data]
	predict = "cs_115_ocena"

	x = np.array(tracked_extracted.drop([predict], 1))
	y = np.array(data[predict])

	show_estimated_values(x, y, 0.75, "cs_101_ocena")
	show_estimated_values(x, y, 0.90, "cs_101_ocena")

	tracked_data = ["cs115_izostanci", "cs_115_ocena"]
	tracked_extracted = data[tracked_data]
	predict = "cs_115_ocena"

	x = np.array(tracked_extracted.drop([predict], 1))
	y = np.array(data[predict])

	show_estimated_values(x, y, 0.75, "cs115_izostanci")
	show_estimated_values(x, y, 0.90, "cs115_izostanci")

	tracked_data = ["cs_101_ocena", "it_101_ocena",
	                "ma_101_ocena", "cs115_izostanci", "cs_115_ocena"]
	tracked_extracted = data[tracked_data]
	predict = "cs_115_ocena"

	x = np.array(tracked_extracted.drop([predict], 1))
	y = np.array(data[predict])

	x_train, x_test, y_train, y_test = sklearn.model_selection.train_test_split(x, y, train_size=0.75)
	linear = linear_model.LinearRegression()
	linear.fit(x_train, y_train)

	predictions = linear.predict(x_test)
	accuracy = linear.score(x_test, y_test)
	errors = np.sum(np.where(y_test != predictions))
	print(accuracy, " ", errors)

	x_train, x_test, y_train, y_test = sklearn.model_selection.train_test_split(x, y, train_size=0.90)
	linear = linear_model.LinearRegression()
	linear.fit(x_train, y_train)

	predictions = linear.predict(x_test)
	accuracy = linear.score(x_test, y_test)
	errors = np.sum(np.where(y_test != predictions))
	print(accuracy, " ", errors)


if __name__ == '__main__':
	main()
