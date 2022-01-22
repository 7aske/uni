from collections import Counter
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt


def main():
	data = pd.read_csv("data.csv")

	# 1
	print(round(np.mean(data["Trajanje"]), 3))

	# 2
	print(round(data.groupby(["NivoStudija"])["Trajanje"].mean(), 3))

	# 3

	print(data.groupby(["StudijskiProgram"])["Trajanje"].value_counts())

	# 4
	print(data.groupby(["Univerzitet"])["Trajanje"].value_counts())

	# 5
	study_duration = data["Trajanje"].to_list()
	mean = np.mean(study_duration)

	plt.axvline(mean, color="red")
	plt.hist(study_duration, edgecolor="black", linewidth=1.5)
	plt.title("Broj studenata po trajanju studija:")
	plt.xlabel("Trajanje studija")
	plt.ylabel("Broj studenata")

	plt.show()

	# 6
	plt.hist(data["Univerzitet"].to_list(), edgecolor="green", linewidth=1.0)
	plt.xticks(rotation=10)
	plt.ylabel("Broj studenata")
	plt.xlabel("Univerziteti")

	plt.show()

	# 7
	by_major = Counter(data["StudijskiProgram"])

	plt.pie(by_major.values(), labels=by_major.keys(
	), startangle=40, autopct="%.1f%%", wedgeprops={"edgecolor": "white", "linewidth": 1})
	plt.title("Raspodela studenata po studijskom programu")

	plt.show()

	by_university = Counter(data["Univerzitet"])

	plt.pie(by_university.values(), labels=by_university.keys(), autopct="%.1f%%",
	        wedgeprops={"edgecolor": "white", "linewidth": 1})
	plt.title("Raspodela studenata po univerzitetu")

	plt.show()


if __name__ == '__main__':
	main()
