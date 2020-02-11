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

Implementacija obuhvata razvoj programa primenom programskih jezika visokog ili niskog nivoa, ili pak, korišćenje opštih, gotovih sistema koji se onda prilagođavaju zahtevima korisnika.

Razvoj softvera koji se višestruko koristi se vrši na više nivoa: 

* Apstraktni nivo (projektni šabloni)

* Nivo objekta (objekti iz biblioteka)

* Nivo komponenti

* Nivo sistema

Korišćenjem ranije razvijenog softvera, ubrzava se razvoj novog softvera, smanjuje se rizik neuspeha i smanjuju se troškovi razvoja.

Konfiguracija softvera je skup određenih verzija softverskih komponenata. Upravljanje konfiguraciom je opšti proces upravljanja promenama softverskog sistema.

Upravljanje konfiguracijom je upravljanje verzijama, integracija sistema i praćenje problema radi prijava grešaka i problema u softveru.

Razvojne i izvršne platforme obuhvataju i hardversku i softversku opremu, koja se koristi pri razvoju i pri korišćenju razvijenog sistema.

Odluka o izvršnoj konfiguraciji softverskog sistema se najčešće dokumentuje primenom UML dijagrama instalacije.

Zbog ad-hoc izmena u softveru, koje nisu u skladu sa projektnom dokumentacijom, nastaju problemi pri integraciji većih softverskih sistema.

Transformacija ima za cilj da poboljša jedan aspekt modela zadržavajući sva druga svojstva modela.

TRANSFORMACIJA MODELA - Svrha ove transformacije je pojednostavljenje ili optimizacija originalnog modela, a u skladu sa specifikacijom zahteva

Refaktorisanje je transformacija izvornog koda radi poboljšanja njegove čitljivosti ili promenljivosti, a bez promena ponašanja sistema

Inženjerstvo unapred se primenjuje na skup elemenata modela i dovodi do odgovarajućih komandi izvršnog koda, kao što su deklaracije klasa, Java izrazi ili šema baze podataka

Inženjerstvo unazad (reverse engineering) se primenjuje nad skupom elemenata izvornog koda da bi se dobio skup elemenata modela

Primenom principa transformacija objektnog modela umanjujete mogućnost javljanja grešaka.

Implementacija je preslikavanje, tj. transformacija objektnog modela u izvorni kod, kao i optimizacija samog objektnog modela, kao i njegovo preslikavanje u šemu baze podataka

OPTIMIZACIJA PRISTUPA - Čest izvor neefikasnosti je ponavljanje prolaze preko višestrukih asocijacija i pogrešno postavljanje atributa.

Klasa koja ostane sa samo nekoliko atributa ili metoda (posle više optimizacija) i ako ima asocijaciju samo sa jednom klasom, može se zameniti sa jednim atributom.

Asocijacije su UML koncepti koje označavaju kolekcije dvosmernih veza između dva ili više objekata. Programski jezici podržavaju koncept reference.

Dvosmerne asocijacije su složenije, jer uvode uzajamne zavisnosti među klasama.

ASOCIJACIE “JEDAN-NA-VISE” - Stranu sa "VISE" možemo realizovati samo sa kolekcijom referenci

ASOCIJACIJE "VISE-NA-VISE” - Obe strane imaju klase koje imaju polja podataka tipa kolekcije, a koje sadrže reference na objekte, kao i operacije koje su neophodne za njihovo ažuriranje.

Razvoj softvera sa otvorenim kodom je pristup razvoju softvera u kome je izvorni kod softvera javno dostupan, a pozivaju se dobrovoljci da učestvuju u njegovom daljem razvoju.

Vlasnik softvera može da postavi ograničenja za njegovo korišćenje, uključujući i korišćenje posebne dozvole za korišćenje softvera sa otvorenim kodom.

## L9

Testiranje služi da se utvrdi da li program radi ono bi trebalo da radi i da se otkriju greške u programu pre nego što se pusti u upotrebu.

Testiranje je samo deo šireg procesa provere (verifikacije) i potvrđivanja (validacije) ispravnosti softvera.

Inspekcije softvera podržava proces verifikacije i validacije u različitim fazama procesa razvoja softvera.

Inspekcija bolje otkriva greške nego testiranje programa.

Slučajevi za testiranje (test cases) i specifikacije su ulaz u testiranje. Izvršenje testova se može automatizovati i dobijeni razultati automatski upoređivati sa očekivan rezultatima (automated testing).

**Tri faze testiranja** su: 

* Razvojno testiranje

* Testiranje softvera za isporuku

* Korisničko tetsiranje

Razvojna testiranja uključuju sve aktivnosti testiranja koje provode članovi razvojnog tima.

Jedinicno testiranje je proces testiranja osnovnih softverskih jedinica, kao što su metodi ili klase

Alati za testiranje (kao što je JUnit) poseduju opšte klase za testiranje koje vi možete da proširite da bi kreirali specifične slučajeve testiranja.

Koriste se dve vrste slučajeva testiranja. Prvi se odnosi na normalan način rada programa, a drugi – na česte probleme zasnovane na iskustvu.

Kategorije ulaznih podataka se često nazivaju particijama ekvivalencije ili domenima. Testiranje particija se koristi kod slučajeva testiranja sistema.

Primena particija ekvivalencije je efektivan pristup testiranja jer pomaže u utvrđivanju grešaka programera kada se za ulaze koriste podaci na ivicama particija.

Testiranje komponente se fokusira na interfejs komponente s ciljem da se proveri da li se ponaša u skladu sa specifikacijom.

Vrsta interfejsa usplovaljava i mogućnost javljanja greške određenog tipa.

VRSTE GREŠAKA KOD INTERFEJSA - Greške potiću od lošeg korišćenja interfejsa, nerazumevanja interfejsa i usled neusklađenosti brzina rada komponenti koje su u vezi posredstvom interfejsa.

Inspekcije i recenzije ponekad mogu biti troškovno efektivnije nego testiranje radi otkrivanja grešaka.

Na osnovu sekvencijalnog dijagrama slučajeva korišćenja sistema utvrđuju se operacije koje se treba da testiraju i vrši se projektovanje slučajeva testiranja za izvršenje testova.

Testovima vodjen razvoj (test driven development) je pristup razvoju programa u kome se istovremeno vrši i razvoj koda i njegovo testiranje.

AUTOMATSKO TESTIRANJE - Testovi su ugrađeni u jedan poseban program koji izvršava testove i koji aktivira program koji se testira.

Testovima vodjen razvoj se najčešće koristi kod razvoja novog softvera.

Primarni cilj procesa testiranja softvera za isporuku je da proizvođač softvera uveri sebe da je sistem dovoljno dobar za upotrebu.

Testiranje zasnovano na zahtevima je validaciono testiranje, jer treba da utvrdi da li sistem ostvaruje zadate zahteve.

Scenario testiranja je jedan pristup testiranja softvera pre isporuke u kome vi osmislite tipične scenarije korišćenja i onda ih iskoristite za razvoj slučajeva testiranja sistema

Kada primenjujete testiranja korišćenjem scenarija, vi najčešće testirate nekoliko zahteva sa jednim scenarijom

TESTIRANJE PERFORMANSI SISTEMA - Testiranje se vrši izvršavanjem više testova sa postepenim povećanjem opterećenja sistema sve dok te performanse postanu neprihvatljive.

Testiranje od strane kupca, je faza procesa testiranja u kojoj naručioci softvera definišu ulazne podatke i interakcije radi donošenja odluke o preuzimanju sistema i njegovog korišćenja.

Proces prihvatanja softverskog sistema sdrži šest faza: 

* Definisanje kriterijuma

* Planiranje testa

* Priprema tasta

* Izvršavanje testa

* Analiza rezultata

* Odbijanje ili prihvatanje sistema

U slučaju primene agilnog metoda u razvoju softvera, nema posebne aktivnosti kojom se vrši testiranje sistema radi njegovog prihvatanja od strane korisnika.

## L10

Evolucija softvera je stalna promena softvera posle njegovog inicijalnog razvoja i isporuke naručiocu, ili tržištu.

Evolucija je faza u kojoj se vrše značajne promene u arhitekturi softvera i njegovoj funkcionalnosti. Za vreme servisiranja, vrše se sitnije promene, ali neophodne promene.

Glavni pokretač evolucije softvera u svim organizacijama su predlozi za promenu sistema.

Troškovi i efekat promena se ocenjuju da bi se donela odluka o prihvatanju.

IMPLEMENTACIJA PROMENE - Mora se proceniti da li promena neće izazvati neke nove probleme.

Sistemi koji imaju puno hitnih ispravki u svojoj istoriji (menja se kod, ali ne i projektna dokumentacija) brže stare, jer nove promene postaju teže izvodljive i teže se održavaju.

Održavanje softvera praćeno je značajnim troškovima jer se u dugom periodu vrše povremene manje ili veće dorade softvera.

Održavanje softvera je proces menjanja sistema posle njegove isporuke.

Tri tipa odrzavanja: popravka greski, prilagodjavanje okruzenju i dodavanje funkcionalnosti.

Ukupni troškovi održavanja softvera u toku njegovog životnog veka mogu da se smanje u odnosu na troškove razvoja ako se o održavanju vodi računa još u fazi razvoja softvera.

Skuplje je dodati novu funkcionalnost već postojećem softveru nego je ugraditi pri njegovom razvoju.

Reinženjering softvera je promena strukture arhitekture sistema, ponovno pisanje koda primenom modernih programskih jezika, promena struktura i vrednosti podataka sistema.

Proces reeinženjeringa starog softvera sadrži niz aktivnosti koje poboljšavaju performanse starog softvera.

Restrukturiranje je poboljšanje strukture programa, smanjivanje njegove složenosti i olakšavanje njegovog razumevanja.

Sta raditi sa starim softverom - Da bi se izabrala pravilna strategija evolucije starog sistema, potrebno je najpre izvršiti njegovu procenu i sa poslovnog i sa tehničkog stanovišta.

Poslovna vrednost softvera u nekoj organizaciji se određuje analizom frekvencije njegove upotrebe, brojem poslovnih procesa koje podržava, stepenom pouzdanosti i važnosti rezultata.

Da bi ocenili okruženje, potrebno je da izvršite neka merenja sistema, njegovog okruženja i njegovog procesa održavanja

Najvažniji faktori za ocenjivanje kvaliteta aplikacije su vezani za pouzdanost sistema, poteškoće u održavanje sistema i dokumentaciju.

# L11


ZAŠTO JE DOŠLO DA RAZVOJA AGILNIH METODA RAZVOJA SOFTVERA? - Trka za vremenom zahvatila je i industriju razvoja softvera. S ciljem da se brzo dobije koristan softver, on se ne dobija kao jedna jedinstvena jedinica, već se dobija u seriji inkremenata.

Agilne metode realizuju koncept integralnog razvoja softvera, u kome su inkrementi mali, i isporučuju se korisnicima na svake dve ili tri nedelje.

Agilne metode se filozofski oslanjaju na tzv. „agilni manifest“ koji je dokument oko koga su se složili mnogi proizvođači softvera.

Zbog odsustva jasnog i kompletnog dokumenta koji definiše zahteve, što je obično deo ugovore, softverske kuće imaju poteškoće da angažuju druge firme kao podizvođače.

Ukjlučivanje korisnika sistema i česte promene u sastavu razvojnog tima – su dva najveća problema u primeni agilnih metoda u održavanju sistema.

Kod planski vođenog razvoja, iteracije se javljaju unutar aktivnosti sa formalnom dokumentacijom. Kod agilnog pristupa, iteracije se dešavaju između aktivnosti.

Najveći broj projekata razvoja softvera koriste praksu i planom vođenim i agilnog pristupa razvoju softvera.

Kod ekstremnog programiranja, zahtevi se izražavaju u vidu scenarija a programeri rade u parovima i razvijaju testove za svaki zadatak pre nego što napišu kod.

Zahtevi nisu specificirani u obliku lista zahtevanih funkcija sistema, već u vidu scenarija koji nastaju kao rezultat diskusije predstavnika korisnika i razvojnog tima.

Kada se karte sa pričama razviju, razvojni tim svaki kartu podeli na zadatke i onda procenjuje potreban rad i resurse za implementaciju.

Opšti problem kod inkrementalnog programiranja je da on dovodi do degradacije strukture softvera, tako da se buduće promene sve teže i teže implementiraju.

Prvo se definiše test, pa se onda razvija kod. To omogućava testiranja programa još kada je u fazi razvoja.

Da bi se testiranje ubrzalo, izvršenje testova se automatizuje. Testovi se pišu kao izvršne komponente pre nego što se implementira zadatak.

PROGRAMIRANJE U PAROVIMA - Podržava ideju o osećanju kolektivnog autorstva, realizuje neformalna recenzija procesa razvoja i omaže rad na usavršavanu koda, tj. restrukturiranje, tokom razvoja izmena sistema.

SCRUM PRISTUP RAZVOJU - Rad na projektima se realizuje u tzv. sprint ciklusima, gde svaki sprint ciklus razvije jedan inkrement sistema.

Scrum sprint ciklus je jedinica planiranja u kojoj se radi neki posao koje se ocenjuje, sa svojstvima koji su odabrani za razvoj, i koji se softverski primenjuje.

Scrum postupak je timski vođen, baziran na skupu upravljivih i razumljivih delova, koji kupcu isporučuje softverske verzije na vreme u bliskoj saradnji sa kupcem.

Veliki softverski sistemi imaju svoje specifičnosti koje ometaju primenu agilnh metoda u njihovom razvoju.

Postoji mišljenja da se agilne metode mogu koristiti i kod razvoja velikih sistema,ali uz izvesne modifikacije metoda.

Primena agilnih metoda u velikim organizacijama je proces promene kulture organizacije. Za to je potrebno dosta vremena i zahteva upravljanje organizacijskim promenama.
