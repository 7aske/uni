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

## L4

* **Inzenjesrki zahtevi**

    Zahtevi za razvoj softverskog sistema su opisi onoga što treba sistem da radi. Inženjerstvo zahteva je proces nalaženja, analize, dokumentovanja i provere ovih servisa i ograničenja. Postoje zahtevi korisnika i zahtevi sistema. Različiti korisnici su zainteresovani za različite kategorije zahteva.

* **Funcionalni zahtevi**

    Funkcionalni zahtevi sistema opisuju šta bi sistem trebalo da radi. Korisnici ih definišu na apstraktan način, a inženjeri razvoja ih pretvaraju u detaljne zahteve.

* **Nefunkcionalni zahtevi**

    Nefunkcionalni zahtevi su zahtevi koji nisu direktno povezani sa servisima koje sistem treba da obezbedi svojim korisnicima, već definišu ograničenja implementacije sistema.

* **Dokument sa zahtevima**

    Dokument sa zahtevima za softver je zvanični dokument koji razvojni tim treba da primeni. On uključuje i zahteve korisnika i detaljnu specifikaciju zahteva za sistem. Specifikacija zahteva je proces pisanja korisničkih i sistemskih zahteva u dokument sa zahtevima.

## L5

* **Slucajevi koriscenja**

    Granične (Boundary) klase, klase kontrole (Controller) i klase entiteta (Entity) su klase koje su glavni elementi modela analize, jer definišu ponašanje celog softverskog sistema.

    Akter (Actor) u konumikaciji sa Boundry klasom, a ova sa Control i Entity klasama realizuje jedan slučaj korišćenja.

    Inženjer slučaja korišćenja vrši realizaciju slučaja korišenja i nalazi i utvrđuje klase analize.

    Iterativni postupak utvrđivanja klasa entiteta se realizuje primenom tri koraka: Modelovanje funkcionalnosti, Modelovanje klase enetiteta i Modelovanje dinamike ovih klasa.

# L6

* **Arhitektura sistema**

    Arhitektura sistema ukazuje nam kako bi sistem trebalo da bude organizovan i prikazuje ukupnu strukturu sistema.

    Projektovanje arhitekture softvera je određivanje njegovih glavnih komponenti koje čine sistem i njihove međusobne interakcije.

* **Arhitekntonski sabloni**

    Arhitektonski šabloni (architecural patterns) su opisi organizacije sistema, kao što su arhitektura klijent-server, ili višeslojna arhitektura. Arhitektonski šabloni sadrže bitna arhitektonska rešenja koja su upotrebljena kod različitih sistema.


    * *Servisno orijentisana arhitektura*

        Ova arhitektura organizuje aplikaciju kao kolekciju servisa kojii komuniciraju jedan sa drugim preko dobro-definisanih interfejsa. Ovi servisi se nazivaju Veb servisima. 

        Podsistem je zamenljivi deo sistema sa jasno definisanim interfejsima.  Podsistem sadrži klase. Servis je skup operacija koji dele neku zajedničku svrhu, a interfejsi  podistema sadrže spisak servisa.

    * *MVC* 

        MVC arhitektura svrstava sve podsisteme u tri kategorije: Model, View i Controller.

    * *Slojevita arhitetktura*

        Slojevita arhitektura obezbeđuje odvajanje i nezavisnost komponenata softvera primenom funkcionalnih slojeva. Svaki sloj zavisi od uređaja i servisa koje nudi sloj ispod njega Svaki sloj obezbeđuje novu funkcionalnost, a oslanja se na funkcionalnost slojeva ispod njega.

    * *Repozitorijum*

        Repozitorijum obezbeđuje pristup svih podsistema jedinstveoj strukturi podataka. Organizacija alata oko zajedničke baze podataka je način da se poveća efikasnost rada sa podacima, jer nema prenosa podataka sa jednog na druge module.

    * *Klijent/Server*

        Serverski podsistem obezbeđuje servise klijentima, tj. drugim podsistemima. Serveri ne moraju da znaju identitet klijenata, dok klijenti mogu da znaju nazive servera i njihovih servisa. Klijenti koristi servise upotrebom poziva udaljenih procedura kao što je HTTP.
    
    * *Pipe and filter*

        Prima veći broj ulaza i šalje rezultate podsistemima u vidu skupa izlaza. Model cevi i filtera je model izvršne organizacije sistema procesa funkcionalnih transformacija koji, koristeći svoje ulaze, proizvodi izlaze.

    * *Message oriented middleware*

        Message oriented middleware (MOM) arhitektura je zasnovana na ideji da sistem radi na osnovu međusobne komunikacije podsistema koji ga čine. 

## L7

    
Jedan objektno-orijentisano softverski sistem čine interaktivni objekti koji održavaju svoje unutrašnje stanje i obezbeđuju operacije na to stanje.   
    
PROCES PROJEKTOVANJA SOFTVERA Definisati kontekst i spoljne interakcije sa sistemom, projektovati arhitekturu softvera, utvrditi glavne objekte sistema, razviti projektne modele sistema i specificirati sve interfejse objekata.

Model interakcija daje dodatne informacije, jer ukazuje kako sistem koji projektujemo komunicira sa okruženjem, tj. sa drugim sistemima u svom okruženju.
    
Interfejs nekog objekta definiše poruke na koje objekat reaguje. Svaka poruka se definiše tzv. potpisom tj. nazivom operacije koju objekat sadrži i njenih parametara
    
Šabloni (paterni) i jezici za šablone su načini opisivanja najbolje prakse, dobrih projektnih rešenja, i prikupljanje iskustva na način koji omogućava drugima da ponovo upotrebe to iskustvo
    
Šabloni se mogu klasifikovati na sledeće osnovne vrste:

* *Šabloni kreiranja*

* *Šabloni strukture*

* *Šabloni ponašanja*

* *J2EE šabloni*

**Šabloni kreiranja**: 
    
* *Singleton* - Singlton se primenjuje u slučajevima klasa koje imaju samo jedan primerak (instancu), tj. Objekat
    
* *Builder* - Odvojiti izradu složenih objekata od njihovog reprezentacije, tako da isti postupak izrade može da se primeni za kreiranje različitih reprezentacija objekata.  Builder omogućava menjanje unutrašnje predstave objekta, izolovanje izrade i predstavljanja objekata i bolju kontrolu procesa njihove izrade.
    
* *Abstract Factory* - Interfejs apstraktne fabrike je odgovoran za kreiranje fabrike povezanih objekata, bez posebne specifikacije njihovih klasa. AbstractFactory klasa štititi klijenta od različitih platformi koje obezbeđuju različite implementacije istog skupa koncepata
    
**Šabloni strukture**:

* *Adapter* - Adapter konvertuje interfejs klase u drugi interfejs u skladu sa očekivanjem klijenta. Adapter omogućava da klase rade zajedno, što ne bi mogli da rade zbog nekompatibilnih interfejsa. Povezuje dva nekompatibilna interfejsa

* *Bridge* - svrha šablona: Odvajanje apstrakcije od svoje implementacije tako da se obe klase mogu nezavisno menjati

* *Decorator* - Decorator šablon dozvoljava dodavanje nove funkcionalnosti postojećeg objekta bez menjanja njegove strukture. Decortor obezbeđuje veću fleksibilnost nego statičko nasleđivanje, ne zavisi od identiteta objekta i radi sa malo malih objekata koji se razlikuju samo po malim inkremetalnim dodacima.

* *Facade* - Facade sakriva složenost sistema i obezbeđuje interfejs klijentu za njegovu komunikaciju sa sistemom

* *Composite* - Composite šablon opisuje kako da se koristi rekurzija u postavljanju objekata, tako da klijenti ne moraju da se bave tom razlikom.

    
**Šabloni ponašanja**:


* *Mediator* -  Mediator omogućava definisanje objekta koji učauruje (skriva) kako niz objekata međusobno komuniciraju. Mediator podržava labavo povezivanje objekata. Primenite Mediator šablon kada skup objekata međusobno komunicira na jasno definisan, ali složen način, a veze nisu strukturirane i teško su razumljive

* *Memento* - Šablon ograničava korišćenje podklasa, razdvaja Colleques objekte, pojednostavljuje protokole objekata, olakšava sagledavanje interakcije objekata, ali postaje složen i težak za održavanje. Koristite Memento šablon kada je potrebno da se memoriše stanje objekta da bi se kasnije povratilo to stanje, dobili implementacioni detalji i time prekinulo učaurenje objekta. Šablon pojednostavljuje Originator objekat, ali upotreba Memento objekta može da zahteva veliku memoriju, a postoje i skriveni troškovi oko kasnijeg uništenja Momento objekta

* *State* - Šablon State dozvoljava objektu da menja svoje ponašanje kada se promeni njegovo unutrašnje stanje. Šablon State se koristi kada ponašanje objekta zavisi od sopstvenog stanja i mora da promeni svoje ponašanje u vreme izvršenja, a operacije imaju velike uslovne iskaze zavisne od stanja.

* *Strategy* - Šablon omogućava definisanje familije algoritama, učaurenje svakog od njih, i njihovu razmenljivost, a algoritmi se mogu nezavisno menjati od strane klijenta. Upotrebite Strategy šablon kada se povezane klase razlikuju samo u ponašanju, kada imate potrebu za različitim varijantama algoritma i kada podatke algoritma klijent ne treba da zna.

* *Observer* - Šablon omogućava definisanje "jedan-na-vise" zavisnost između objekata, tako da kada se jednom objektu promeni stanje, svi objekti zavisni od njega se automatski obaveštavaju i menjaju. Primenjujte šablon kada apstrakcija ima dva aspekta, i kada jedan zavisi od drugoga i kada promena jednog objekta zahteva promenu drugih objekata, čiji broj ne znate.

* *Visitor* - Visitor vam omogućava da definišete novu operaciju bez promene klasa elemenata na koje se izvršava. Koristite Visitor šablon kada struktura objekata sadrži mnogo klasa objekata sa mnogo različitih interfejsa, kada postoji mnogo nepovezanih operacija, a klase strukture se retko menjaju.

# L8    

Implementacija obuhvata razvoj programa primenom programskih jezika visokog ili niskog nivoa, ili pak, korišćenje opštih, gotovih sistema koji se onda prilagođavaju zahtevima korisnika

Razvoj softvera koji se višestruko koristi se vrši na više nivoa: apstraktni nivo (projektni šabloni), nivo objekta (objekti iz biblioteka), nivo komponenti i nivo sistema

Korišćenjem ranije razvijenog softvera, ubrzava se razvoj novog softvera, smanjuje se rizik neuspeha i smanjuju se troškovi razvoja

Konfiguracija softvera je skup određenih verzija softverskih komponenata. Upravljanje konfiguraciom je opšti proces upravljanja promenama softverskog sistema

Upravljanje konfiguraciom je upravljanje verzijama, integracija sistema i praćenje problema radi prijava grešaka i problema u softveru.

Razvojne i izvršne platforme obuhvataju i hardversku i softversku opremu, koja se koristi pri razvoju i pri korišćenju razvijenog sistema

Odluka o izvršnoj konfiguraciji softverskog sistema se najčešće dokumentuje primenom UML dijagrama instalacije

Zbog ad-hoc izmena u softveru, koje nisu u skladu sa projektnom dokumentacijom, nastaju problemi pri integraciji većih softverskih sistema.

Transformacija ima za cilj da poboljša jedan aspekt modela zadržavajući sva druga svojstva modela.

TRANSFORMACIJA MODELA - Svrha ove transformacije je pojednostavljenje ili optimizacija originalnog modela, a u skladu sa specifikacijom zahteva

Refaktorisanje je transformacija izvornog koda radi poboljšanja njegove jasnoće (čitljivosti) ili promenljivosti, a bez promena ponašanja sistema

Inženjerstvo unapred se primenjuje na skup elemenata modela i dovodi do odgovarajućih komandi izvršnog koda, kao što su deklaracije klasa, Java izrazi ili šema baze podataka

Inženjerstvo unazad (Reverse) se primenjuje nad skupom elemenata izvornog koda da bi se dobio skup elemenata modela

Primenom principa transformacija objektnog modela umanjujete mogućnost javljanja grešaka

Implementacija je preslikavanje, tj. transformacija objektnog modela u izvorni kod, kao i optimizacija samog objektnog modela, kao i njegovo preslikavanje u šemu baze podataka

OPTIMIZACIJA PRISTUPA - Čest izvor neefikasnosti je ponavljanje prolaze preko višestrukih asocijacija i pogrešno postavljanje atributa.

Klasa koja ostane sa samo nekoliko atributa ili metoda (posle više optimizacija) i ako ima asocijaciju samo sa jednom klasom, može se zameniti sa jednim atributom

Operacije koje troše mnogo resursa, treba odložiti u izvršenju što kasnje.

Asocijacije su UML koncepti koje označavaju kolekcije dvosmernih veza između dva ili više objekata. Programski jezici podržavaju koncept reference.

Dvosmerne asocijacije su složenije, jer uvode uzajamne zavisnosti među klasama

ASOCIJACIE “JEDAN-KA-PUNO” - Stranu sa “puno” možemo realizovati samo sa kolekcijom referenci

ASOCIJACIJE “PUNO-KA-PUNO” - Obe strane imaju klase koje imaju polja podataka tipa kolekcije, a koje sadrže reference na objekte, kao i operacije koje su neophodne za njihovo ažuriranje

Ukoliko dođe do kršenje ugovora (operacije na interfejsu), izbacuje se izuzetak te se izvršenje programa zaoustavlja.

U mnogim slučajevima, kod za proveru predusplova i postuslova je obimniji i složeniji nego kod koji obavlja zahtevanu funkciju, te je izvor novih grešaka kao i pada performansi softvera

RELACIONA TABELA - Šema baze podataka opisuje ispravan skup slogova podataka koji se mogu skladištiti u bazi podataka. Relacione baze podatake memorišu i šemu i podatke, u obliku tabela

Strani ili sekundarni ključ je primarni ključ, ali u nekoj drugoj tabeli, jer on povezuje tu drugu tabelu sa tabelom u kojoj je definisan primarni ključ. Na taj način se povezuju dve tabele.

PRESLIKAVANJA KLASA I ATRIBUTA - Klasa se preslikava u tabelu, sa istim imenom. Svaki atribut klase je kolona tabele, sa istim imenom. Svaki slog podataka tabele odgovara jednom primerku (objektu) klase

PRESLIKAVANJE ASOCIJACIJE - Kardinalnost 1 može da bude strani ključ, a kardinalnost “mnogo” je strani ključ koji predstavlja klasu. Asocijacija sa “mnogo-ka-mnogo” se predstavlja posebnom tabelom

PRESLIKAVANJE NASLEĐIVANJA - Koristi se strani ključ ka vezi ka tabeli koja predstavlja podklasu ili se atributi super klase prebacuju u podklase, te se dupliciraju kolone tabela koje odgovaraju podklasama

Razvoj softvera sa otvorenim kodom je pristup razvoju softvera u kome je izvorni kod softvera javno dostupan, a pozivaju se dobrovoljci da učestvuju u njegovom daljem razvoju

Vlasnik softvera može da postavi ograničenja za njegovo korišćenje, uključujući i korišćenje posebne dozvole za korišćenje softvera sa otvorenim kodom