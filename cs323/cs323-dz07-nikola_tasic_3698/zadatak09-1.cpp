//9. Napisati program koji iz datoteke “ulaz.txt” (korišćenjem C++ tokova za rad sa fajlovima)
//učitava ceo broj n (n <=50), a zatim prihvata po jedan element n-dimenzionalnog niza celih
//brojeva. Napisati funkciju koja formira drugi niz koji sadrži samo neparne elemente unetog
//niza. Rezultat funkcije je pokazivač na novodobijeni niz. Funkciju koja formira novi niz kreirati
//kao deo imenskog prostora Test, u posebnom fajlu Test.cpp. Dimenziju novog niza vratiti kao
//argument funkcije korišćenjem reference. Novo-dobijeni niz kreirati kao dinamički niz
//(dinamičko upravljanje memorijom u programskom jeziku C++). Novo-dobijeni niz iz glavnog
//programa odštampati na ekranu, kao i dimenziju novodobijenog niza.

#include <iostream>
#include <fstream>
#include <string>
#include "Test.cpp"

using namespace std;

int main() {
    string str;
    ifstream file;
    int num;

    file.open("input.txt");
    if (!file.good()){
        cout << "Cannot open input.txt" << endl;
        return 1;
    }
    getline(file, str);

    cout << "Number read from file: " << str << endl;

    num = stoi(str);

    if (num > 50) {
        cerr << "Number is larger than 50" << endl;
        return 1;
    }
    int arr[num];
    for(int i = 0; i < num; i++){
        int input;
        cout << "Enter a number: ";
        cin >> input;
        arr[i] = input;
    }
    int* newArr = Test::getOdd(arr, num);
    cout << "[ ";
    for (int j = 0; j < num; ++j) {
        cout << newArr[j] << " ";
    }
    cout << "]" << endl;
    delete[] newArr;
    return 0;
}