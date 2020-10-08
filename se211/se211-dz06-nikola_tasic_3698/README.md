 <div align="center">
 
 ![logo](https://www.metropolitan.ac.rs/files/2018/11/logo-01.png) 

 </div>

 <div align="center">
 
## Prolećni semetar, 2019/20

## *SE211: KONSTRUISANJE SOFTVERA*


## Domaći zadatak: 06

</div>

Ime i prezime: **Nikola Tasić**

Broj indeksa: **3698**

Datum izrade: **05.04.2020**

---

### Tekst domaćeg zadatka

Zadatak:

Odabrati jednu debugging tehniku iz predavanja i primeniti na proizvoljnom programu.

1. Koristiti debbuger u razvojnom okruženju (NetBeans ili Visual Studio zavisno od programskog jezika u kome je pisana aplikacija)

2. Objasniti nedostatke odabranog debbuger-a

3. Detektovati i dokumentovati sintaksne greške u programskom kodu

4. Prikazati runtime greške ukoliko postoje u odabranom programu

### Rešenje zadatka:

1.

![img1](https://github.com/7aske/uni/blob/second-year/se211/se211-dz06-nikola_tasic_3698/img1.png)

![img2](https://github.com/7aske/uni/blob/second-year/se211/se211-dz06-nikola_tasic_3698/img2.png)

![img3](https://github.com/7aske/uni/blob/second-year/se211/se211-dz06-nikola_tasic_3698/img3.png)

2. `GDB` je veoma dobro poznat i testiran alat za debugging. Gotovo nezamenjljiv alat za reverse-engineering i debugging C programa generalno. Njegovi jedini nedostaci mogu da budu to što je command-line-based, ali kao šsto je na slikama prikazano izvršili smo integraciju `GDB`-a preko njegovog servera u sam IDE (u ovom slučaju Clion).

3. Da ima sintaksnih grešaka program se ne bi kompajlirao. 

4. Što se tiče runtime grešaka ovaj program je imao klasičan use-after-free bug gde smo memoriju na koju pokazuje pointer oslobodili a kasnije u programu nastavljamo da čitamo odatle. Rezultati su, u ovom slučaju, bili da vidimo nasumičan tekst umesto onog koji zapravo treba biti prikazan. Memorija se posle oslobađanja ne anulira ali ostali programi u sistemu imaju za slobodu da po njoj pišu. Zanimljiva situacija kod rešavanja ovog problema je bila da u programu za analiziranje curenja memorije `valgrind` nismo imali ovakav problem iz prostog razloga što valgrind alocira memoriju koju će dodeliti programu koji analizira (kao što na primer to radi operativni sistem) i posle oslobađanja te memorije ne dolazi do ponovnog upisa na tim mestima jer taj deo memorije i dalje kontroliše valgrind (operativni sistem ne može da je dodeli drugim procesima). 


