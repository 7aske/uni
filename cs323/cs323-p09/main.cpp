#include <iostream>
#include "kontakt.cpp"

using namespace std;

int main() {
    string name = "aleksandra";
    Kontakt k0(name, name, name, name);
    cout << k0.getIme() << endl;
    cout << k0 << endl;
    Kontakt k1(k0);
    return 0;
}


