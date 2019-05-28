
// Zadatak 5. Napraviti hijararhiju izuzetaka vezanih za unos studenata. Svaki student ima:
// ime, prezime, adresu, grad, indeks. Ukoliko se desi da indeks ima više ili manje od 4 karaktera
// treba da se desi IndeksNijeValidanException. Ukoliko ime studenta nema makar 2 karaktera
// treba da se desi ImeNijeValidnoException. Svi izuzeci koji su vezani za pošiljku treba da
// naslede StudentException
#include <iostream>
#include <string>
#include <exception>

class StudentException : public std::exception {
public:
	explicit StudentException(std::string msg) : message(msg) {};

	virtual const char* what() const noexcept {
		return this->message.c_str();
	}

private:
	std::string message;
};

class StudentIndexNotValidException : StudentException {
public:
	explicit StudentIndexNotValidException() : StudentException("Student index not valid.") {

	}

	virtual const char* what() const noexcept {
		return StudentException::what();
	}
};

class StudentNameNotValidException : StudentException {
public:
	explicit StudentNameNotValidException() : StudentException("Student name not valid.") {

	}

	virtual const char* what() const noexcept {
		return StudentException::what();
	}
};

class Student {
public:
	Student(const std::string &firstName, const std::string &lastName, const std::string &city,
			const std::string &index) {
		if (firstName.length() <= 2 || lastName.length() <= 2) {
			throw StudentNameNotValidException();
		} else {
			this->firstName = firstName;
			this->lastName = lastName;
		}
		if (index.length() <= 2) {
			throw StudentIndexNotValidException();
		} else {
			this->index = index;
		}
		this->city = city;
	}

private:
	std::string firstName;
	std::string address;
	std::string lastName;
	std::string city;
	std::string index;
};

int main() {
	try {
		Student s0("Nikola", "Tasic", "Nis", "3698");
		Student s1("N", "T", "Nis", "3");
	} catch (StudentNameNotValidException ex) {
		std::cout << ex.what() << std::endl;
	} catch (StudentIndexNotValidException ex) {
		std::cout << ex.what() << std::endl;
	}
	return 0;
}