#pragma once

#include <string>
#include <ostream>

enum Type {
    jeans, tshirt, hoodie
};
using namespace std;

class Item {
public:
    Item();

    Item(double price, int id, string barcode, string brand, Type type);

    ~Item();

    double getPrice() const;

    void setPrice(double price);

    int getId() const;

    void setId(int id);

    const string &getBarcode() const;

    void setBarcode(const string &barcode);

    const string &getBrand() const;

    void setBrand(const string &brand);

    Type getType() const;

    void setType(Type type);

    static int getObjectCount();

    bool operator==(const Item &rhs) const;

    bool operator!=(const Item &rhs) const;

    bool operator<(const Item &rhs) const;

    bool operator>(const Item &rhs) const;

    bool operator<=(const Item &rhs) const;

    bool operator>=(const Item &rhs) const;

    friend ostream &operator<<(ostream &os, const Item &item);

    friend istream &operator>>(istream &os, const Item &item);


private:
    double price;
    int id;
    string barcode;
    string brand;
    Type type;
    static int object_count;
};
