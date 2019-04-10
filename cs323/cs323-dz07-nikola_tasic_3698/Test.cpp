#include <iostream>
namespace Test {
    int *getOdd(const int *n, int &size) {
        int newSize = 0;
        for (int i = 0; i < size; ++i) {
            if (n[i] % 2 == 1) {
                newSize++;
            }
        }

        int *out = new int[newSize];
        int count = 0;

        for (int j = 0; j < size; ++j) {
            if (n[j] % 2 == 1) {
                out[count] = n[j];
                count++;
            }
        }

        size = newSize;
        return out;
    }
}