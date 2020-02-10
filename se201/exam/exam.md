# SE201

## L1

* **Sta cini softverski sistem?**

    Softverski sistem cine racunarski programi i njihova dokumentacija

* **Sta predstavlja 'softversko inzenjerstvo'?**

    Softversko inzenjerstvo je inzenjerska disciplina koja se bavi svim aspektima prozivodnje softvera. Ukljucuje tehnike specifikacije programa, njihovo projektovanje i evoluciju.

* **Kako dolazi do gresaka u softveru?** 

    Greske u softveru su posledica povecanih zahteva i ocekivanja korisnika s jedne strane ili s druge strane niske primene metoda i tehnika softverskog inzenjerstva.

* **Sta je profesionalni softver?**

    Profesionalni softver je softver koji ima odredjenu poslovnu svrhu. Na proper ugradjeni(embeded sistemi), informacioni sistemi ili CAD sistemi.

* **Vrste softverskih proizvoda**

    * *Genericki softver* - Softverski prozivodi koji su razvijeni radi prodajei namenjeni korisnicima koji nisu pozati u vreme razvoja.
    
    * *Softver po narudzbini* - Softver koji je razvijen po specifikaciji definisanoj od strane konkretnog kupca. Primer ovakvog softvera su u glavnom informacioni sistemi.

     
## L2
* **Sta je softverski proces?**

    Softverski proces je redosled aktivnosti koje vode proizvodju softvera. Softverski proces cine:

    * *Specifikacija* - Ova aktivnost treba da ustanovi koji servisi su zahtevani od strane sistema i ograničenja rada i razvoja sistema. Kupci i inzenjeri definisu softver koji treba da se prozivede kao i ogranicenja u radu istog.

    * *Razvoj* - Projektovanje i programiranje.

    * *Validacija* - Ova aktivnost je zapravo spoj dve aktivnosti - validacije i verifikacije. Ona treba da pokaze da li softver zadolvoljava svoju specifikaciju i zahteve.

    * *Evolucija* - Promena softvera u skladu sa promenjenim zahtevima kupca ili trzista.

* **Tipovi softverskih procesa**

    * *Pocesi vodjeni planom* - Procesi kod kojih su sve aktivnosti unapred odredjene planom i napredak u razvoju se odredjuje stepenom ostvarivanja tog plana.

    * *Agilni procesi* - Procesi kod kojih se planiranje vrsi inkrementalno kako bise proces lakse prilagodio promenljivim potrebama korisnika.

* **Modeli softverskih procesa**

    * *Model vodoapada* - Faze razvoja ovog modela su specifikacja zahteva, dizajn i implementacija, verifikacija i evolucija. Kod ovog modela svaka faza daje neki rezultat koji „teče“ po vodopadu i predstavlja polazište za iduću fazu.

    * *Inkrementalni razvoj* - Ovaj model povezuje faze specifikacije, razvoja, i provere, kao niz serija inkremenata razvoja softvera, pri čemu inkrement dodaje određenu funkcionalnost na prethodnu verziju po specificiranim zahtevima.

    * *Softversko inženjerstvo zasnovano na višestrukoj upotrebljivosti* - Ovaj pristup se oslanja na koriscenje komponenata softvera koje se mogu visestruko koristiti. Ovim procesom se integrišu postojeće softverske komponente u sistem, umesto da se razvijaju nove. 


* **Faze razvoja kod modela vodopada**

    1. *Analiza i definisanje zahteva* - Definišu se servisi sistema, ograničenja i ciljevi, definisani uz konsultaciju sa korisnicima sistema. Detaljnije se opisuju kao sistemska specifikacija.

    2. *Projektovanje sistema i softvera* - Proces projektovanja sistema raspoređuje zahteve svim komponentama sistema i uspostavljanje celokupne arhitekture sistema, kao i utvrđivanje i opisivanje osnovnih apstrakcija softverskog sistema i njihove međuzavisnosti.

    3. *Implementacija i testiranje* - U ovoj fazi je projektno rešenje softvera realizovano skupom programa ili programskih jedinica. Testiranje jedinica utvrđuje da li svaka jedinica ostvaruje svoju planiranu funkciju.

    4. *Integracija i testiranje sistema* - Sve programske jedinice u ovoj fazi se integrišu u sistem i testiraju se kao kompletan sistem radi provere da li softver zadovoljava postavljene zahteve, tj. ostvaruje svoje funkcije i performanse. Posle ovog testiranja, softverski sistem se isporučuje kupcu.
    
    5. *Operativni rad i odrzavanje* - Sistem je instalisan i pušten u operativni rad, tj. u upotrebu. Održavanje obuhvata ispravljanje grešaka koje nisu otkrivene u ranijim fazama životnog ciklusa, poboljšavanja primene programskih jedinica i poboljšanje usluga softverskog sistema u skladu sa novopostavljenim zahtevima.

* **Kada koristiti model vodopada?**

    Model vodopada se koristi samo u slučajevima kada su svi zahtevi dobro definisani, jasni i stabilni.

* **Inkrementalni razvoj**

    Inkrementalni razvoj se zasniva na postupku postepenog razvoja novih inkremenata, tj. verzija softvera, i zamenom prethodno razvijenih verzija.

* **Šta sadrži jedan inkrement?**

    Inkrement sadrži novi deo softvera koji zadovoljava novu grupu zahteva koji su dodeljeni inkrementu, i tako razvijen inkrement se integriše sa prethodno razvijenim softverom.

## L3

* **Sta je UML?**

    UML ili Unified Modeling Language je grafički jezik za vizualizaciju, specifikaciju, konstruisanje i dokumentovanje sistema programske podrške koji je postavljen kao standard.

* **Sta je klasa?**

    * Klasa je skup objekata koji imaju zajedničku strukturu i ponašanje.

* **Sta je generalizacija?**

    Generalizacija omogućava da se zajedničke informacije (atributi i metode) definišu i održavaju samo na jednom mestu (roditeljska klasa).

* **Screnario**

    Scenario opisuje interakciju slučaja korišćenja sistema, tj. sekvencijalno navedene akcije aktera i reakcije softverskog sistema. Postoje osnovni (primarni) i pomoćni (sekundarni) scenariji. Scenario opisuje interakciju slučaja korišćenja sistema, tj. sekvencijalno navedene akcije aktera i reakcije softverskog sistema. Postoje osnovni (primarni) i pomoćni (sekundarni) scenariji.

* **Slucaj koriscenja**

    Slučaj korišćenja utvrđuje učesnike (koji se u terminologiji UML nazivaju akterima) koji učestvuju u interakciji sa softverskim  sistemom i ima svoj naziv. Slučaj korišćenja utvrđuje učesnike (koji se u terminologiji UML nazivaju akterima) koji učestvuju u interakciji sa softverskim sistemom i ima svoj naziv.

* **Sekvencijalni dijagram**

    Sekvencijalni dijagram prikazuje redosled interakcija aktera sa osnovnim objektima sistema, ali prikazuje i interakciju između ovih objekata.

* **Dijagram interakcije**

    Dijagram interakcije prikazuje interakciju koja se sastoji od skupa objekata i njihovih relacija, uključujući poruke koje mogu da se razmenjuju između njih.
