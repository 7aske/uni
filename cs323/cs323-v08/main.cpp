#include <iostream>

class Box {
private:
    int a, b, c;
public:
    Box();

    Box(int a, int b, int c);

    ~Box();
};

Box::Box() {
    std::cout << "Box created" << std::endl;
}

Box::Box(int a, int b, int c) {
    this->a = a;
    this->b = b;
    this->c = c;
    std::cout << "Box created" << std::endl;
    std::cout << "Box a->" << a << std::endl;
    std::cout << "Box b->" << b << std::endl;
    std::cout << "Box c->" << c << std::endl;

}

Box::~Box() {
    std::cout << "Box destroyed" << std::endl;
}

int main() {
    Box b(1,2,3);
    std::cout << "Hello, World!" << std::endl;
    return 0;
}