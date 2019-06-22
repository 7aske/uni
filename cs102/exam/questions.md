# CS102

## Pitanja

### **1. Poznata okna rasporeda u JavaFX – navedite i objasnite?**  

Okna rasporeda:

* **BorderPane**  
  Ovaj pane omogucava fiksno pozicioniranje elemenata gore, dole, levo, desno i u sredini samog pane-a pomocu metoda:  
  `pane.setTop()`,  
  `pane.setBottom()`,  
  `pane.setRight()`,  
  `pane.setLeft()`,  
  `pane.setCenter()`
* **StackPane**  
  Stack pane pozicionira elemente u jedan preko drugog front-to-back.
* **VBox**  
  VBox pozicionira elemente redom jedan ispod drugog u koloni.
* **HBox**  
  HBox pozicionira elemente redom jedan pored drugog s leva na desno u jednom redu.
* **FlowPane**  
  FlowPane redja elemente redom jedan pored drugog i po potrebi prelazi u novi red u slucaju da elementi ne mogu da stanu u jedan.
* **GridPane**
  GridPane redja elemente u matricu tako da svakom elementu mora biti odredjena "koordinata". Elementi mogu da zauzimaju vise "polja" u toj matrici.

### **2. Registracija obrađivača i obrada događa u programima sa JavaFX grafičkim korisničkim interfejsom.**  

U zavisnosti od elementa postoje razlicite funkcije koje omogucavaju registrovanje `EventHandler`-a. Uglavnom elementi imaju metodu `e.setOnAction()` koja prima klasu obradjivaca objekata ili lambda funkciju koja se poziva/instancira u trenutku kada se taj dogadjaj desi. Na primer `e.setOnMouseClicked()` omogucava da prosledimo `EventHandler` klasu koja ce se pozvati u trenutku kada kliknemo na neki element. U `@Override`-ovanoj metodi `handle()` te klase vrsimo obradu samog dogadjaja.

### **3. Zašto se koriste i šta omogućavaju generički tipovi podataka?**  

Genericki tipovi podataka omogucavaju konstruisanje klasa i njihovih metoda koji obradjuju podatke za razlicite slozene Java tipove. Primer ugradjenog generickog tipa je `ArrayList` koji nam omogucava da pravimo liste slozenih tipova koji nasledjuju tip `Object` i nad njima primenjujemo sve funkcije primenljive na liste generalno.

### **4. Šta je zadatak, a šta nit? Na koje načine je moguće obaviti sinhronizovanje niti u Java programima?**  

Zadatak je logicka jedinica izvrsenja nekog posla dok nit je objekat interfejsa `Runnable` koji omogucava da se vise razlicitih zadataka izvrsava *asinhrono* ili *paralelno*. Svaki zadatak je pod kontrolom jedne niti. Zadaci se mogu sinhronizovati pomocu zakljucavanja (`ReentrantLock`), `Java Collections Framework`-a, `ForkJoinTask` klase...

### **5. Šta je PreparedStatement? Šta je CallableStatement?**  

`PreparedStatement` je parametarizovani `SQL` upit koji omogucava da umesto placeholder-a umetnemo odgovarajuce vrednosti/promenljive i na taj nacin izbegnemo na primer `SQL Injection`. `CallableStatement` nasledjuje `PreparedStatement` i omogucava pozivanje *memorisanih SQL upita* sa parametrima.

### **6. Objasnite unutrašnju klasu, unutrašnju anonimnu klasu i lambda izraz.**  

Obzirom na to da klasu u Java-i ne mozemo da oznacimo identifikatorom `private` unutrasnja klasa predstavlja klasu definisanu u okviru neke druge klase tako i takvoj klasi ne mozemo pristupiti van "roditeljske" klase. Unutrasnja anonimna klasa je klasa koja je definisana na mestu pozivanja parametra u nekoj metodi i na istom tom mestu instancirana. Na primer `e.setOnAction()` prima parametar tipa klase `EventHandler` i mi mozemo toj metodi prosledeiti klasu sa `@Override`-ovanim metodama direktno na mestu poziva. Lambda izraz pomaze da se uprosti sintaksa definisanja i instanciranja anonimne unutrasnje klase.

### **7. Objasnite detaljno strukturu Java FX programa.**  

`JavaFX` programi se sastoje, makar graficki deo, od pozornice `Stage`, scena `Scene`, panela `Pane`, i elemenata koje smestamo u te panele `Node`. Svaki `JavaFX` program pocinje u `@Override`-ovanoj metodi `start()` koja ima za argument instancu klase `Stage` koji predstavlja "pozornicu" na koju postavljamo instance klase `Scene`. Svaka scena ima svoj `root` okno koje predstavlja podrazumevani raspored dodatih elemenata `Node`. Mi mozemo da menjamo raspored postavljanja elemenata dodavajuce razlicita okna. Svi elementi koji se nalaze u prozoru `JavaFX` aplikacije su objekti klase `Node`

### **8. Koja su ograničenja kod upotrebe generika?**  

* Generici ne mogu da sadrze primitivne tipove podataka
* Ne mozemo instancirati objekte generickog tipa
* Ne mozemo kreirati primitivne nizove `array` generickog tipa
* Ne mozemo baciti izuzetke generickog tipa
* Ne mozemo vrsiti konverziju tipova
* Paramtar generickog tipa nije dozvoljen u statickom kontekstu

### **9. Šta je mapa? Objasnite kako se čuvaju podaci u mapi?**  

Mapa je struktura podataka koja podatke skladisti u obliku kljuceva sa vrednostima. Svaka vrednost ima svoj odgovarajuci kljuc i kljucevi razlicitih vrednosti ne smeju biti isti.

### **10. Objasnite primenu Connection i Statement objekata u Java programu.**  

Objekat klase `Connection` predstavlja referencu veze sa bazom podataka i njega koristimo da komuniciramo sa bazom a pre svega instanciramo objekte klase `Statement` koji nam sluze da izvrsmo `SQL` upite direktno nad bazom. Iz objekta klase `Connection` pozivamo metodu `createStatement()` koja nam vraca objekat klase `Statement` pomocu kojeg mozemo da konstruisemo `SQL` upit i izvrsimo ga nad bazom.

### **11. Koje tipove listi poznajete i po čemu se razlikuju?**

* `ArrayList` je interfejs koj omogucava kreiranje promenljivih listi koje u pozadini koriste tip `array` a koji imaju dobru podrsku za nasumicno pristupanje elemenatima te liste. Takodje `ArrayList` ima konstantno vreme izvrsenja operacija kao sto su `size`, `isEmpty` i druge.
* `LinkedList` je interfejs koji u pozadini primenjuje duplu lancanu listu kao strukturu podataka i omogucava fiksno vreme dodavanja/brisanja elemenata na kraj/pocetak liste zbog same prirode strukture.

### **12. Kako se obrađuje ResultSet dobijen izvršavanjem nekog SQL upita?**  

Iz `ResultSet` objekta nekom od petlji i provere metodom `rs.next()` prolazimo kroz rezulatate upita. `ResultSet` objekat sadrzi specijalne "gettere" koji vracaju odgovarajuci tip podatka koji nam je potrban. Treba imati u vidu da mi moramo da uskladimo poziv tih metoda sa nacinom na koji smo konstruisali sam upit. Ako je na prvom mestu predvidjeno da stoji tip podatka `String` poziv metode `rs.getInt()` ce baciti gresku.

### **13. Objasnite upotrebu generičkih džoker tipova.**  

Dzoker `<?>` predstavlja zamenu za bilo koji tip podatka i koristimo ga najcesce u slucaju kada zelimo da nasa genericka metoda moze da prihvati podklase klase za koju smo je definisali. Na primer klasa Integer je podklasa klase Number ali GenericClass\<Integer\> nije podklasa klase GenericClass\<Number\>

### **14. Objasnite razliku između apstraktne klase i interfejsa.**

Metode interfejsa su implicitno apstraktne i one ne mogu da imaju implementacije. Metode apstraktne klase mogu da imaju podrazumevano ponasanje. Java klasa moze da extenduje samo jednu apstraktnu klasu a moze da implementira vise interfejsa. Interfejs moze da implementira samo drugi interfejs. Interfejs je apsolutno apstraktan i ne mozemo ga instancirati. Objekte apstraktne klase ne mozemo instancirati kljucnom recju `new` ali mozemo pozvati ako postoji metoda `main()`. Za implemetiranje interfejsa koristimo kljucnu rec `implements`, a za nasledjivanje apstraktne klase koristimo kljucnu rec `extends`.

### **14. Šta je JDBC? Šta je JDBC drajver? Koje korake primenjuje Java program kada pristupa bazi podataka?**  

`JDBC` je kontroller koji sadrzi sve potrebne alate za komunikaciju sa bazom podataka. `JDBC Driver` je posrednik u komunikaciji izmedju `JDBC` i same baze nad kojom se vrse upiti. Prilikom pristupa bazi podataka pozivom metode `createConnection()` objekta interfejsa `Driver` dobijamo objekat interfejsa `Connection` ako je veza povezivanje sa bazom uspresno. Nad tim objektom mozemo kreirati objekte interfejsa `Statement` koji nam sluzi da izvrsavamo upite nad bazom i upravljamo podacima kojima smo iz baze dobili.

### **15.  Objasnite rekurzivno rešavanje problema u Javi. Navedite prednosti i nedostatke rekurzivnog pristupa rešavanju problema.**  

Primenom rekurzije mozemo kompleksne probleme resiti prostim deljenjem na manje potprobleme koji su definisani u samoj rekuriji. Nedostaci rekurzije jesu da u koliko bazni slucaj nije precizno definisan ili ne postoji dobijemo beskonacnu rekurziju a takodje svaki poziv rekurzivne metode podrazumeva dodavanje nove funkcije tj novog `stack frame`-a sto dovodi do povecane upotrebe memorije za resenje nekog problema.
