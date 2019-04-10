//9. Napisati program koji umeće string S2 u string S1 počev od pozicije p. Za novodobijeni string
//ispisati samo deo stringa S od pozicije p na dužini k. Napomena - Koristiti funkcije C++ klase za
//rad sa stringovima (find, replace, insert, substr, ...).

#include <iostream>
#include <string>

using namespace std;

int main() {
    string string1;
    string string2;
    string out;
    int p;
    int k;
    cout << "Enter string 1: ";
    cin >> string1;
    // ili cin.getline(string1, sizeof(input));
    cout << "Enter string 2: ";
    cin >> string2;
    cout << "Enter index p: ";
    cin >> p;
    cout << "Enter index k: ";
    cin >> k;


    string1.insert(p, string2);
    cout << "New string: " << string1 << endl;
    cout << "Weird professor demand: ";
    while (p < string1.size() && k > 0){
        cout << string1[p];
        p++;
        k--;
    }
    return 0;
}