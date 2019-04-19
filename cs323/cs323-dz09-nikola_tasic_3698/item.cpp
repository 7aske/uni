#include "item.h"

using namespace std;

double Item::getPrice() const {
    return price;
}

void Item::setPrice(double price) {
    Item::price = price;
}

int Item::getId() const {
    return id;
}

void Item::setId(int id) {
    Item::id = id;
}

const string &Item::getBarcode() const {
    return barcode;
}

void Item::setBarcode(const string &barcode) {
    Item::barcode = barcode;
}

const string &Item::getBrand() const {
    return brand;
}

void Item::setBrand(const string &brand) {
    Item::brand = brand;
}

Type Item::getType() const {
    return type;
}

void Item::setType(Type type) {
    Item::type = type;
}

int Item::getObjectCount() {
    return object_count;
}

Item::Item() {
    Item::object_count++;
}

Item::Item(double price, int id, string barcode, string brand, Type type) {
    this->price = price;
    this->id = id;
    this->barcode = barcode;
    this->brand = brand;
    this->type = type;
    Item::object_count++;
}

Item::~Item() {
    Item::object_count--;
}

bool Item::operator==(const Item &rhs) const {
    return price == rhs.price &&
           id == rhs.id &&
           barcode == rhs.barcode &&
           brand == rhs.brand &&
           type == rhs.type;
}

bool Item::operator!=(const Item &rhs) const {
    return !(rhs == *this);
}

bool Item::operator<(const Item &rhs) const {
    if (price < rhs.price)
        return true;
    if (rhs.price < price)
        return false;
    if (id < rhs.id)
        return true;
    if (rhs.id < id)
        return false;
    if (barcode < rhs.barcode)
        return true;
    if (rhs.barcode < barcode)
        return false;
    if (brand < rhs.brand)
        return true;
    if (rhs.brand < brand)
        return false;
    return type < rhs.type;
}

bool Item::operator>(const Item &rhs) const {
    return rhs < *this;
}

bool Item::operator<=(const Item &rhs) const {
    return !(rhs < *this);
}

bool Item::operator>=(const Item &rhs) const {
    return !(*this < rhs);
}

ostream &operator<<(ostream &os, const Item &item) {
    os << "price: " << item.price << " id: " << item.id << " barcode: " << item.barcode << " brand: " << item.brand
       << " type: " << item.type;
    return os;
}

istream &operator>>(istream &is, const Item &item) {
    return is; // cita mi ">>" kao binarni operator iz nekog razloga
}
