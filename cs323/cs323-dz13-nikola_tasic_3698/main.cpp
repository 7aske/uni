#include <iostream>
#include <exception>

class AddressNotValidException : std::exception {
protected:
	explicit AddressNotValidException() = default;;

public:
	explicit AddressNotValidException(const std::string &msg) {
		this->msg = msg;
	}

	const char* what() const noexcept override {
		return AddressNotValidException::msg.c_str();
	}

private:
	std::string msg;
};


class Student {
public:
	Student(const std::string &firstname, const std::string &lastname, const std::string &address,
			const std::string &city, const std::string &index) {
		this->setFirstname(firstname);
		this->setLastname(lastname);
		this->setAddress(address);
		this->setCity(city);
		this->setIndex(index);
	}

	~Student() = default;;

protected:
	explicit Student() = default;;

private:
	std::string firstname;
	std::string lastname;
	std::string address;
	std::string city;
	std::string index;
public:
	const std::string &getFirstname() const {
		return firstname;
	}

	void setFirstname(const std::string &firstname) {
		Student::firstname = firstname;
	}

	const std::string &getLastname() const {
		return lastname;
	}

	void setLastname(const std::string &lastname) {
		Student::lastname = lastname;
	}

	const std::string &getAddress() const {
		return address;
	}

	void setAddress(const std::string &address) {
		if (isdigit(address.back())) {
			Student::address = address;
		} else {
			throw AddressNotValidException("Address not valid");
		}
	}

	const std::string &getCity() const {
		return city;
	}

	void setCity(const std::string &city) {
		Student::city = city;
	}

	const std::string &getIndex() const {
		return index;
	}

	void setIndex(const std::string &index) {
		Student::index = index;
	}

	friend std::ostream &operator<<(std::ostream &os, const Student &student) {
		os << "firstname: " << student.firstname << " lastname: " << student.lastname << " address: " << student.address
		   << " city: " << student.city << " index: " << student.index;
		return os;
	}
};


class BatteryDurationInvalidException : std::exception {
protected:
	explicit BatteryDurationInvalidException() = default;;

public:
	explicit BatteryDurationInvalidException(const std::string &msg) {
		this->msg = msg;
	}

	const char* what() const noexcept override {
		return BatteryDurationInvalidException::msg.c_str();
	}

private:
	std::string msg;
};

class Clock {
public:
	Clock(const std::string &name, const std::string &model, int year, long batteryDuration) {
		this->setName(name);
		this->setModel(model);
		this->setYear(year);
		this->setBatteryDuration(batteryDuration);
	}

protected:

private:
	std::string name;
	std::string model;
	int year;
	long batteryDuration;
public:
	const std::string &getName() const {
		return name;
	}

	void setName(const std::string &name) {
		Clock::name = name;
	}

	const std::string &getModel() const {
		return model;
	}

	void setModel(const std::string &model) {
		Clock::model = model;
	}

	int getYear() const {
		return year;
	}

	void setYear(int year) {
		Clock::year = year;
	}

	long getBatteryDuration() const {
		return batteryDuration;
	}

	void setBatteryDuration(long batteryDuration) {
		if (batteryDuration > -1) {
			Clock::batteryDuration = batteryDuration;
		} else {
			throw BatteryDurationInvalidException("Battery duration must not be negative");
		}
	}

	friend std::ostream &operator<<(std::ostream &os, const Clock &clock) {
		os << "name: " << clock.name << " model: " << clock.model << " year: " << clock.year << " batteryDuration: "
		   << clock.batteryDuration;
		return os;
	}
};

int main() {
	try {
		Student s("Nikola", "Tasic", "Unknown1", "Unknown", "3698");
		std::cout << s << std::endl;
	} catch (AddressNotValidException ex) {
		std::cout << ex.what() << std::endl;
	}
	try {
		Student s("Nikola", "Tasic", "Unknown", "Unknown", "3698");
		std::cout << s << std::endl;
	} catch (AddressNotValidException ex) {
		std::cout << ex.what() << std::endl;
	}

	try {
		Clock c("Name", "Model", 2010, 40);
		std::cout << c << std::endl;
	} catch (BatteryDurationInvalidException ex) {
		std::cout << ex.what() << std::endl;
	}

	try {
		Clock c("Name", "Model", 2010, -1);
		std::cout << c << std::endl;
	} catch (BatteryDurationInvalidException ex) {
		std::cout << ex.what() << std::endl;
	}
	return 0;
}