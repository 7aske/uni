/*24.[C++]: Napraviti C++ program u koji se unosi tri broja. Program treba da proveri da li se sabiranjem bilo
koja dva broja može dobiti treći i ispiše “Može” ili “Ne može” u zavisnosti od rezultata provere. Uraditi
zadatak korišćenjem C++ klasa i modifikatora za ulaz/izlaz (cin, cout, setprecision, setwidth...).*/

#include <iostream>

using namespace std;
int check(int,int,int);
int main(void)
{
    int num0, num1, num2;
    cout << "Enter first number: ";
    cin >> num0;
    cout << "Enter second number: ";
    cin >> num1;
    cout << "Enter third number: ";
    cin >> num2;
    if (check(num0, num1, num2)){
        cout << "Moze" << endl;
    } else {
        cout << "Ne moze" << endl;
    }

    return 0;
}
int check(int a, int b, int c){
    if (a == b + c){
        return 1;
    }
    if (b == a + c){
        return 1;
    }
    if (c == a + b){
        return 1;
    }
    return 0;
}

