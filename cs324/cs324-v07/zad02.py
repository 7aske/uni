from getpass import getpass
import re


def main():
	email_regex = re.compile(r'([A-Za-z0-9]+[.-_])*[A-Za-z0-9]+@[A-Za-z0-9-]+(\.[A-Z|a-z]{2,})+')
	password_regex = re.compile(r'^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[-_#@]).{8,}$')

	with open("email_sifre.txt", "w") as file:

		while True:
			email = input("email: ")
			if email_regex.match(email) is not None:
				break

		file.write(email + '\n')

		while True:
			password = getpass()
			file.write(password + "\n")
			if password_regex.match(password) is not None:
				break


if __name__ == '__main__':
	main()
