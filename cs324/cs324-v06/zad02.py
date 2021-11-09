def main():
	s = 0
	while True:
		val = input("Uneti broj: ")
		if val == "":
			break
		try:
			s += float(val)
			print(s)
		except ValueError:
			print("Unos", val, "nije validan")


if __name__ == '__main__':
	main()
