# 24.
Oceniti vremensku složenost  sledećeg algoritma:
```
int a[n]; // fali deklaracija niza
int sum;
float avarage;
for (int i = 0; i < n ; i ++) {
    a [ i ] = i ;
}
for (int i = 0; i < n ; i ++) {
    sum+=a[i] 
} 
avarage=sum/n; 
```
---
```
int a[n]; // c
int sum; // c
float avarage; // c
for (int i = 0; i < n ; i ++) {
    a [ i ] = i ; // n * c
}
for (int i = 0; i < n ; i ++) { //
    sum+=a[i] // n * c
} 
avarage=sum/n; // c
```
Slozenost je `3c + nc + nc + c` ili kada se sredi `n`.
