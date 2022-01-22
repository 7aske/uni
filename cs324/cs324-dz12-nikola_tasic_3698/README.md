# CS324 DZ12

## Zadatak #1

Napisati program koji će generisati skup podataka koji će se koristiti za trening i test skupove.
Podaci jesu sledeći:

* Kolona #1: Ime: cs_101_ocena (vrednost od 5 do 10)
* Kolona #2: Ime: it_101_ocena (vrednost od 5 do 10)
* Kolona #3: Ime: ma_101_ocena (vrednost od 5 do 10)
* Kolona #4: Ime: cs_115_izostanci (vrednosti od 0 do 15)
* Kolona #5: Ime cs_115_položen (vrednosti 0 ili 1)
* Kolona #6: Ime: cs_115_ocena (vrednosti od 5 do 10)

Kolone popuniti nasumično, i onda promeniti sledeće (ne ručno, već kroz kod): Ukoliko je vrednost kolone #1 između 8 i 10, povećati vrednost kolone #6 za jedan. Ukoliko je vrednost kolone #4 između 5 i 12, smanjiti vrednost kolone #6 za jedan, a između 13 i 15, smanjiti vrednost kolone #6 za 2. Ukoliko je vrednost kolone #3 između 9 i 10, povećati vrednost kolone #6 za 1.Svuda gde je vrednost kolone #5 nula, vrednost kolone #6 je 5. Obratiti pažnju da pri promeni vrednosti kolona da minimalne i maksimalne vrednosti ostanu nepromenjene.

Svaka kolona ima 500 redova. Sačuvati tabelu kao dataset.csv.

## Zadatak #2

Učitati skup dataset.csv zadatka #1.
Istrenirati model linearna regresije da estimira vrednosti kolone cs_115_ocena, ako su ulazni
podaci:

* Samo kolona cs_101 ocena,
* Samo kolona cs_115_izostanci,
* Sve kolone (osim cs_115_ocena i cs_115_polozen, naravno)

Istrenirati modele linearne regresije (koristiti 75%, a zatim 90% skupa za trening skup) za svaku od traženih kolona, naći broj grešaka i tačnost za svaki, i zaključiti koji je model najbolje koristiti. Kada se koristi samo jedna kolona, nacrtati i grafike estimiranih vrednosti naspram pravih vrednosti.

## Zadatak #3

Uraditi isto kao i kod zadatka #2, samo je model logistička regresija, a estimiraju se vrednosti kolone cs_115_polozen.