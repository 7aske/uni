#include <iostream>

class Employee;

using namespace std;

int main() {
    std::cout << "Hello, World!" << std::endl;
    Employee e0;
    return 0;
}

class Employee {
    char* firstName{};
    char* lastName{};
public:
    Employee();

    Employee(const char* firstName, const char* lastName);

    ~Employee();

    char* getFirstName() { return firstName; };

    char* getLastName() { return lastName; };

    void setFirstName(char* const firstName);

    void setLastName(char* const lastName);

};

Employee::Employee(const char* firstName, const char* lastName) {
    this->firstName = (char*) firstName;
    this->lastName = (char*) lastName;
}

Employee::~Employee() {
    delete[] firstName;
    delete[] lastName;
}

void Employee::setFirstName(char* const firstName) {
    this->firstName = (char*) firstName;
}

void Employee::setLastName(char* const lastName) {
    this->lastName = lastName;
}
