import re


def main():
	index = 3698
	last_num = str(index)[-1]
	with open("data.txt", "r") as file, open("adrese.txt", "w") as to_write:
		content = file.read()
		# pocinje kranjim brojem indeksa, ima brojeve posle prvog broja, ima razmak posle brojeva
		# i sve do kraja reda
		pattern = re.compile(r"^{}\d+\s.*".format(last_num), re.MULTILINE)
		for addr in re.findall(pattern, content):
			to_write.write(addr + "\n")


if __name__ == '__main__':
	main()
