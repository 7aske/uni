# Uvod

U ovom radu bavićemo se kreiranjem web radnog okvira za Java programski jezik pod nazivom **Grain**. Cilj rada je pored kreiranja samog radnog okvira i istraživanje osnovnih i nekih od naprednijih koncepata koji čine svaki radni okvir i samim tim web aplikacije. Istražićemo razne odluke povodom dizajna različitih naprednih funkcionalnosti kao što su *sistem za umetanje zavisnosti* ili *templejting jezik* za kreiranje dinamičkih server-side stranica. Sam radni okvir biće dosta, po svojoj strukturi i funkciji, nalik na Spring radni okvir koji mu je i bio inspiracija.

Objasnićemo i teorijski obraditi pojmove kao što su:

* Umetanje zavisnosti

* HTTP zahtevi i sesija

* Autentikacija i autorizacija bazirana na rolama

* Dizajniranje programskog jezika i kreiranje interpretera

* MVC arhitektura softverskih projekata

* Objektno-relaciono mapiranje i komunikacija sa bazama podataka

Takođe, na kraju rada ćemo obraditi primer gotove aplikacije napisane u Grain framework-u. Aplikacija će pratiti MVC arhitekturu ali pored toga će koristiti asinhrone HTTP pozive za dobavljanje određenih informacija. Naravno aplikacija će imati autentikaciju i autorizaciju za odgovarajuće putanje.

# Pojam radnog okvira

## Šta je radni okvir

Pojam radni okvir predstavlja strukturu ili skup pravila prateći koja lakše dolazimo do cilja. Kada kažemo radni okvir (eng. *framework*) najčešće mislimo na softverski framework koji služi za izradu neke od različitih vrsta aplikacija (desktop, web, mobile itd.). Naravno, radni okviri postoje i u drugim sferama industrije. Jedan primer, blizak softveru, takođe iz softverske industrije, bi bio primer upravljanja projektima uz korišćenje jednog od agilnih radnih okvira kao što su npr. *scrum*, *ekstremno programiranje*, *lean* itd. Pravila i struktura nametnuta od strane tih okvira dovodi do toga da se manje vremena i energije (resursa) troši na kreiranje njih samih prilikom početka svakog projekta, postavlja smislena osnovna pravila koja su se u praksi pokazala kao efikasna prilikom uspešnog vođenja projekata i naravno lakše uključenje novih ljudi na projekat koji poznaju dati framework. 

Slična situacija je nastaje i u softverskom svetu. Ne želimo da svaki put kada krećemo sa novim projektom kreiramo osnovne alate koje ćemo koristiti od nule (parsiranje HTTP zahteva, web security biblioteka, logivanje itd.). Ovo umnogome smanjuje vreme razvoja nekog sistema. Takođe, zbog toga što mnogo timova radi na različitim projektima koristeći iste alate dolazi do toga da se sam alat bolje testira i samim tim prilagođava promenama. Pored svega toga, prilikom promene članova tima onboarding proces (proces upoznavanja člana tima sa projektom) je znatno kraći i sam taj proces se može fokusirati isključivo na upoznavanje novog člana sa domenskim problemima jer će sva tehnička infrastruktura biti manje-više ista (podrazumevajući, naravno, da je član upoznat sa radnim okvirom). Postoji dobra rečenica iz knjige *Clean Code* Roberta C. Martina gde Ward Cunningham (kreator Wikija) navodi da je jedna od odlika čistog koda da programer pri čitanju istog nailazi na linije i konstrukcije koje su upravo ono što je očekivao. Radni okviri nam tu pomažu jer će uvek postojati zajednička polazna tačka za sve projekte i programeri će, baš tako, nailaziti na konstrukcije koje su baš ono što su i očekivali.

Radni okvir u kontekstu programiranja je struktura odnosno skup biblioteka i pravila povrh kojih konstruišemo softver. Takođe, radni okvir i pomenute biblioteke sadrže implementaciju čestih funkcionalnosti koje se tiču domena za koji je on specijalizovan. U ovom radu ćemo se fokusirati na domen web framework-e tako da će se te podrazumevane implementacije čestih funkcionalnosti ticati parsiranja HTTP zahteva, implementacije security-a, komunikacije sa bazom podataka, kreiranja dinamičkih stranica itd.

U zavisnosti od toga kako je implementiran radni okvir može biti modularan i monolitan. Modularan radni okvir je na primer Spring radni okvir. Spring sam po sebi ne donosi mnogo više od implementacije umetanja zavisnosti (eng. *dependency injection* - *DI*) ali kombinovanje sa ostalim njegovim modulima (bibliotekama) nam daje kompletno rešenje. Mana ovog pristupa su potencijalni problemi sa različitim zavisnostima i malo veća kompleksnost konfiguracije. Velika prednost je to što developer bira koje će module izabrati i tako dolazi do minimalnog rešenja koje će zadovoljiti potrebe projekta. U drugom slučaju, kod monolitnih, kakav je ujedno i **Grain** radni okvir, celokupno rešenje je u obliku jedne zavisnosti i developer samo bira da li će koristiti neke funkcionalnosti ili ne. Ovde možemo doći do problema da je brdo nekorišćenih funkcionalnosti, a samim tim i koda, ubačeno u projekat. Ovo uglavnom ne predstavlja problem u mnogim situacijama ali to naravno zavisi od projekta i veličine framework-a.

Dakle, s obzirom na to da ustanovili smo šta predstavlja radni okvir - neizostavan alat modernog developera - možemo na da pređemo na opis nekih najpopularnijih rešenja.

## Web radni okviri u Java programskom jeziku

U ovom delu pričaćemo o nekim od popularnijih Java biblioteka i radnih okvira i videćemo procentualno koliko se koriste na projektima gde je baš Java glavna tehnologija. Referenciraćemo Perforce-ov *2021 Java Developer Productivity Report* gde možemo naći brdo različitih informacija o Java ekosistemu.

![Procenat zastupljenosti radnih okvira](./assets/2021_java_framework_percentage.png)
<div align="center">
Sl. 1 - <i>Procenat zastupljenosti radnih okvira</i>
</div>

Kao što možemo da vidimo Spring Boot ubedljivo zauzima prvo mesto po popularnosti među radnim okvirima na java projektima. Spring Boot doduše beleži pad sa 83% koje beleži prošle godine. Na trećem mestu sa tek 9% se nalazi koji je kao i četvrtoplasirani Quarkus (6%) doživeo pomak sa svojih 1% koje je imao prethodne godine (2020).

### Vert.x

Vert.x je open-source, reaktivni i poliglotski programski (može biti pisan u bilo kom JVM jeziku) toolkit koji nam dolazi od Eclipse-a. Vert.x je modularan, brz, i lagan a dizajniran je za korišćenje u mikroservisnim aplikacijama. Takođe, pored dizajna koji ima mikroservise na umu Vert.x je pogodan za reaktivno programiranje jer se bazira na *event loop-u* poput tehnologije kao što je Node.js. 

### Micronaut

Micronaut je kao što mu i samo ime govori micro-framework koji je dizajniran tako da ne koristi Java Reflection API za konfiguraciju i sve njegove funkcionalnosti koje bi inače bile konfigurisane u toku izvršenja su zapravo konfigurisane u toku kompajliranja. Ovo dovodi do znatnog umanjenja zahteva za radnom memorijom a takođe i smanjuje start-up vreme. Ovo je naravno idealno za korišćenje u mikroservisnim i cloud-native aplikacijama. Takođe, Micronaut je open-source.

### Quarkus

Quarkus je Java framework prilagođen za Kubernetes deployment. Glavne tehnologije koje omogućuju Quarkus su OpenJDK HotSpot i GraalVM. Ideja Quarkus-a je da učini Javu vodećom platformom u Kubernetes i serverless okruženjima dok pruža developerima jedinstven imperativni i reaktivni model za optimalno korišćenje u širokom spektru arhitektura.

### DropWizard

DropWizard je open-source Java radni okvir za razvoj visoko-performantnih, ops-friendly, RESTful web servisa. DropWizard sa sobom povlači stabilne i zrele java biblioteke iz Java ekosistema u jednostavan, lak paket koji omogućava developerima da se fokusiraju na obavljanje zbog posla.

### Spring Boot

Spring Framework je open-source radni okvir i IoC(inversion of control) kontejner za Java platformu. Jedna od glavnih odlika Spring-a je da se on može koristiti u bilo kojoj Java aplikaciji ali se najčešće koristi za izradu web aplikacija na Java EE (Java Enterprise Edition) platformi. Spring zajedno sa Spring Boot-om je *de facto* standard za izradu web aplikacija.

# Teorijska postavka

Pre nego što predstavimo studiju slučaja koja će biti pokrivena o ovom radu, bilo bi korisno proći kroz sve veće relevantne koncepte koje Grain framework pokriva. Koncepti će se ticati različitih podležućih tehnologija koje primarno koristimo na web-u ali i nekih specifičnih kao što je na primer pisanje programskog jezika.

## HTTP

HTTP ili *hyper-text transfer protocol* je najstandardniji protokol za razmenu informacija na web-u. HTTP započeo svoju evoluciju, ako možemo tako da se izrazimo, u kolevci Interneta - u CERN-u 1989. godine. Prva verzija (1.0) se pojavila 1991. godine a trenutna standardna trenutna najpodržanija verzija je 1.1 koja je standardizovana 1999. godine ([RFC 2616](https://datatracker.ietf.org/doc/html/rfc2616) sa dopunama 2014. godine RFC 7230-7235). Postoje i verzije HTTP/2 i HTTP/3 koje menjaju način prenosa podataka preko HTTP-a ali i ne i njegovu semantiku u pogledu aplikacije koje ga koriste. HTTP/2 uvodi binarnu kompresiju podataka u zaglavlju (više o tome kasnije), korišćenje jedne [TCP](https://en.wikipedia.org/wiki/Transmission_Control_Protocol) konekcije za razmenu podataka i push-ovanje podataka. HTTP/3 je revizija HTTP/2 protokola koji koristi [QUIC](https://en.wikipedia.org/wiki/QUIC) i UDP transportni protokol umesto TCP-a. U daljem tekstu kada govorimo o HTTP-u mislićemo na HTTP/1.1 verziju jer je to verzija koju podržava Grain radni okvir.

HTTP je protokol u aplikativnom lejeru i osnova komunikacije na *World Wide Web*-u. Ovaj protokol funkcioniše kao zahtev-odgovor protokol između klijenta i servera. Klijentska aplikacija kreira zahtev ka serveru. Server nakon obrade podataka vraća odgovor klijentu koji može da sadrži različite informacije u svom zaglavlju (headers) i zahtevani resurs u telu (eng. body). Komunikacija između servera i klijenta se ostvaruje pomoću TCP transportnog protokola. U osnovi TCP protokola se nalazi three-way-handshake koji osigurava da su podaci isporučeni (za razliku od UDP-a). 

![Dijagram TCP-a](./assets/tcp_states_connect.jpg)
<div align="center">
Sl. 2 - <i>Dijagram TCP-a</i>
</div>

Kao što vidimo, komunikacija se ostvaruje u tri koraka:

1. U prvom koraku klijent šalje **SYN** zahtev ka serveru.

2. Server odgovara sa **SYN-ACK** i potvrđuje da je dobio zahtev.

3. Na kraju, klijent šalje **ACK** nazad serveru.

Glavne odlike (razlike) u odnosu na **UDP** su sledeće:

* Uređen redosled paketa

* Ponovna transmisija izgubljenih paketa: svi podaci koji nisu dobili **ACK** odgovor se ponovo transmituju

* Transfer bez grešaka - svi paketi sa greškom se tretiraju izgubljenim i ponovo se transmituju

* Kontrola toka - kontrola brzine transfera da bi osiguralo dostavljanje podatka 

* Kontrola zagušenja - izgubljeni paketi smanjuju brzinu toka

### HTTP zahtev i odgovor

Kao što smo već naglasili HTTP je protokol koji se zasniva na zahtevu i odgovoru tako da ćemo ta dva koncepta bolje objasniti u ovom poglavlju.

#### Tipovi poruka

HTTP zahtevi i HTTP odgovori koriste generički format za poruke definisan u [RFC 822](https://www.rfc-editor.org/rfc/rfc822). Oba tipa poruka imaju početnu liniju (eng. *start-line*), nijedan ili više polja zaglavlja (u daljem tekstu eng. *header*), prazne linije (linija koja nema ni jedan karakter pre CRLF) koja označava kraj poljima zaglavlja i opciono telo poruke (eng. *message-body*).

Šema poruka bi izgledala ovako:

```
generic-message = start-line
                  *(message-header CRLF)
                  CRLF
                  [ message-body ]
start-line      = Request-Line | Status-Line
```

Moramo napomenuti da je **CRLF** oznaka za kraj reda standardna na Windows sistemima (*NIX sistemi koriste samo LF, izuzev starijih verzija OS X-a koji koriste CR). CR predstavlja *carriage return* - znak za povratak na početak reda, dok LF predstavlja *line feed* odnosno novi red. Ovi karakteri postoje u svakom tekstu, naravno u zavisnosti od operativnog sistema, s tim što su nevidljivi. Karakter CR je `0D` u heksadecimalnom zapisu i tekstualna reprezantacija mu je `\r`. Karakter LF je `0A` u hexadecimalnom a tekstualno se prikazuje kao `\n`. Ova nomenklatura je konvencija je zaostavština pisaćih mašina.

#### HTTP zaglavlje

Polja zaglavlja se dele na tri tipa:

* Generička - mogu ih koristiti i zahtev i odgovor (Cache-Control, Connection itd.)

* Zahtev - koriste se isključivo u zahtevu (Host, User-Agent itd.)

* Odgovor - koriste se isključivo u odgovoru (Age, Location itd.)

* Entitetska - koriste se u zahtevu i odgovoru za opis tela zahteva (Content-Type, Content-Length itd.)

Svako polje se sastoji od imena polja koje je praćeno dvotačkom (":") i vrednosti polja. Ime polja je case-insensitive. Između dvotačke i vrednosti polja može postojati bilo koji broj belih polja (eng. *whitespace*) Vrednost polja može biti bilo koji tekst, ali ne sme da sadrži CRLF. Polja zaglavlja se razdvajaju CRLF karakterom. Redosled gorenavedenih polja nije bitan, ali je preporučljivo da se prvo navode generička polja, pa onda polja zahteva i polja odgovora, a na kraju entitetska polja.

```
message-header = field-name ":" [ field-value ]
field-name     = token
field-value    = *( field-content | LWS )
field-content  = <the OCTETs making up the field-value
                and consisting of either *TEXT or combinations
                of token, separators, and quoted-string>
```

#### Telo poruke

Telo poruke je zaduženo za prenos entiteta (ovde ne mislimo na entitete u kontekstu baza podataka). Postojanje tela poruke je indikovano postojanjem **Content-Length** ili **Transfer-Encoding** headera. Ukoliko ne postoji ni jedan od ta dva headera telo poruke bi trebalo biti ignorisano. Postoje tipovi poruka koji po svojoj semantici i konvenciji ne bi trebalo da imaju telo a to su: svi informacioni odgovori (status kod 1xx - više o ovim kodovima kasnije), 204 status kod (nema sadržaja - eng. *no content*) i 304 (nije izmenjeno - eng. *not modified*). Svi ostali odgovori imaju telo makar ono bilo dužine nula.

S obzirom na to da je telo poruke dolazi na kraju same HTTP poruke postojane nekog od **Transfer-Encoding** i **Content-Lenght** headera je neophodno - u suprotnom korisnik ne bi znao kada da prestane sa čitanjem poruke.

#### Zahtev

Zahtev klijenta ka serveru se sastoji od **linije zahteva** (eng. *Request-Line*), zaglavlja i tela poruke.

```
Request = Request-Line
          *(( general-header
           | request-header
           | entity-header ) CRLF)
          CRLF
          [ message-body ]
```

Linija zahteva se sastoji iz tri dela: HTTP metode, zahtevanog resursa (URI - uniform resource identifier) i verzije HTTP protokola (HTTP/1.1, HTTP/2, HTTP/3) praćene CRLF-om. Sva tri dela linije zahteva su razdvojeni jednim razmakom (space).

```
Request-Line   = Method SP Request-URI SP HTTP-Version CRLF
```
Metoda zahteva opisuje koju koja metoda će biti primenjena na resursu identifikovanim od strane *Request-URI*-a. Metode su case-sensitive.
```
Method = "OPTIONS"
           | "GET"
           | "HEAD"
           | "POST"
           | "PUT"
           | "DELETE"
           | "TRACE"
           | "CONNECT"
           | extension-method
extension-method = token
```
Sve ove metode sa sobom nose određenu semantiku, ali naravno server može odlučiti da ih po svojoj volji implementira. Dobro je, naravno, pratiti konvenciju i poštovati njihovu ulogu. Nećemo zalaziti detaljno u ulogu svake od ovih metoda ali poenta jeste da npr. treba koristiti GET za dobavljanje podataka, POST za kreiranje, PUT i PATCH za celovito ili parcijalno ažuriranje, DELETE za brisanje i tako dalje. Naravno u specifikaciji stoji da ova metoda može biti proširena bilo kojom metodom - na primer možemo implementirati metodu HELLO koja šalje pozdrav ali to naravno nije u standardu i ni jedan pretraživač neće znati šta da uradi sa njom. Po specifikaciji naravno stoji da server mora da implementira GET i HEAD metode dok su sve druge opcione.

Resurs identifikator predstavlja apsolutnu putanju resursa na serveru. Ovo može biti direktno mapirano u fajl na fajl sistemu ili proizvoljno mapirano na bilo koji drugi resurs u sistemu koji implementira HTTP. Kao što smo rekli, Request-URI služi da identifikuje resurs nad kojim će biti primenjena HTTP metoda.

```
Request-URI    = "*" | absoluteURI | abs_path | authority
```

Primer Request-Line-a koji dobavlja početnu stranu nekog sajta bi izgledao ovako:

```
GET http://7aske.com HTTP/1.1
```

Ovo je primer Request-Line-a sa apsolutnim URI dok sledeći navodi identičan zahtev ali koristeći apsolutnu putanju:

```
GET / HTTP/1.1
Host: http://7aske.com
```

#### Odgovor 

Posle obrade zahteva server vraća nazad HTTP odgovor. HTTP odgovor izgleda identično kao i zahtev s razlikom da on umesto Request-Line-a ima **Status-Line** (linija statusa).

```
Response = Status-Line
          *(( general-header
           | response-header
           | entity-header ) CRLF)
          CRLF
          [ message-body ]
```

Status linija se sastoji iz tri dela: HTTP verzije (HTTP/1.1, HTTP/2, HTTP/3), status koda i tekstom koji opisuje razlog status koda praćenim CRLF-om. Sva tri dela linije zahteva su razdvojeni jednim razmakom (space).

Status kod je trocifreni broj koji u po specifikaciji ima svoje značenje. Neki od primera kodova su: 101 Switching Protocols, 200 OK, 304 Not Modified, 404 Not Found. Kodovi se po svom tipu dele na grupacije:

* 1xx - informacioni

* 2xx - uspešni

* 3xx - preusmeravački

* 4xx - greške klijenta

* 5xx - greške servera

Svaki status ima svoj kod i razlog. Razlog je predviđen isključivo za čitanje i razumevanje od strane čoveka dok sam kod čita mašina. Implementacija klijenta nije u obavezi da parsira tekst razlog na bilo koji način.

```
Status-Code    =
    "100"  ; Continue
  | "101"  ; Switching Protocols
  | "200"  ; OK
  | "201"  ; Created
  | "202"  ; Accepted
  | "203"  ; Non-Authoritative Information
  | "204"  ; No Content
  | "205"  ; Reset Content
  | "206"  ; Partial Content
  | "300"  ; Multiple Choices
  | "301"  ; Moved Permanently
  | "302"  ; Found
  | "303"  ; See Other
  | "304"  ; Not Modified
  | "305"  ; Use Proxy
  | "307"  ; Temporary Redirect
  | "400"  ; Bad Request
  | "401"  ; Unauthorized
  | "402"  ; Payment Required
  | "403"  ; Forbidden
  | "404"  ; Not Found
  | "405"  ; Method Not Allowed
  | "406"  ; Not Acceptable
  | "407"  ; Proxy Authentication Required
  | "408"  ; Request Time-out
  | "409"  ; Conflict
  | "410"  ; Gone
  | "411"  ; Length Required
  | "412"  ; Precondition Failed
  | "413"  ; Request Entity Too Large
  | "414"  ; Request-URI Too Large
  | "415"  ; Unsupported Media Type
  | "416"  ; Requested range not satisfiable
  | "417"  ; Expectation Failed
  | "500"  ; Internal Server Error
  | "501"  ; Not Implemented
  | "502"  ; Bad Gateway
  | "503"  ; Service Unavailable
  | "504"  ; Gateway Time-out
  | "505"  ; HTTP Version not supported
  | extension-code
```

Kao što je i slučaj sa metodama, serverska implementacija može deklarisati i parsirati dodatne kodove.

Sada kada imamo informacije možemo da damo primer kako bi izgledao jedan kompletan HTTP zahtev-odgvor ka serveru kreiranom u Grain frameworku:

```
> GET / HTTP/1.1
> Host: localhost:8080
> User-Agent: insomnia/2022.6.0
> Accept: */*

< HTTP/1.1 200 OK
< Content-Length: 4679
< Content-Type: text/html
```

#### Kolačići (Cookies)

Kolačići su mali tekstualni fajlovi koji se čuvaju na klijentu (browser-u) i koji služe da se čuvaju informacije o klijentu. Kolačići se šalju svaki put kada se šalje zahtev ka serveru. Oni se koriste za različite stvari, od praćenja aktivnosti klijenta na sajtu, do čuvanja informacija o korisniku. Jedna od osnovnih uloga kolačića je da identifikuju zahtev na serveru tako da bi server mogao da zna o kom korisniku se radi. Svaki korisnik može da dobije unikatni identifikator preko kolačića i preko njega će server znati o kome se radi - u prevodu preko kolačića server prati sesiju klijenta/korisnika.

Kolačići se šalju kroz **Cookie** header:

```
...
Cookie: <ime-kolačića>=<vrednost-kolačića>
...
```

Na primer cookie koje identifikuje korisnika može da izgleda ovako:

```
Cookie: JSSID=1234567890
```

Kolačići se mogu postaviti na klijentu kroz **Set-Cookie** header. Na primer cookie koje identifikuje korisnika može da izgleda ovako:

```
Set-Cookie: JSSID=1234567890
```

Svi kolačići se mogu invalidirati kroz **Set-Cookie** header sa vrednošću **max-age=0**. Na primer:

```
Set-Cookie: JSSID=1234567890; max-age=0
```

Klijent automatski ignoriše kolačiće koji su istekli. Svi kolačići se automatski šalju kroz svaki GET/POST zahtev koji je iniciran iz pretraživača kroz link ili formu. Zahtevi koji se iniciraju koristeći JS *fetch* API, *XHR* ili koristeći neku HTTP biblioteku neće sadržati kolačiće i oni moraju biti dodani ručno kroz **Cookie** header. U tim slučajevima se verovatno radi o nekoj **stateless** komunikaciji (bez stanja - suprotno od onoga za šta su kolačići namenjeni) i u tom slučaju je bolje koristiti neki vid **stateless** autentikacije. Više o tome u narednim poglavljima.

Kolačići i njihova zloupotreba je jedan od velikih rizika web-a i stoga se mora obazrivo rukovati istim. 

### Parsiranje JSON podataka

Sada kada smo obradili kako funkcioniše HTTP protokol, možemo da se osvrnemo na jedan od važnijih tipova podataka koji se koristi u web-u. U Grain frameworku za parsiranje JSON podataka se ne koristi biblioteka već postoji minimalnistična implementacija koja se trudi da pokrijve većinu JSON specifikacije.

JSON je tip podataka koji se koristi za razmenu podataka između klijenta i servera. JSON je skraćenica za JavaScript Object Notation. JSON je tekstualni format podataka koji je čitak za čoveka i lako parsiran od strane mašine. JSON je sličan JavaScript objektima ali je nešto jednostavniji i ne podržava sve funkcionalnosti koje JavaScript objekti imaju. JSON je sastavljen od dva tipa podataka: objekata i nizova. Objekat je kolekcija imenovanih vrednosti dok niz predstavlja kolekciju neimenovanih vrednosti. Vrednosti mogu biti tipa string, broj, boolean, null, objekat ili niz. JSON je sastavljen od dva tipa podataka: objekata i nizova. Objekat je kolekcija imenovanih vrednosti dok niz predstavlja kolekciju neimenovanih vrednosti. Vrednosti mogu biti tipa string, broj, boolean, null, objekat ili niz.

Jedna od odlika, pored čitljivosti, JSON oblika podataka je da je language-independent to jest da njegov oblik ne zavisi od jezika u kome se koristi i zbog toga je idealan za razmenu podataka između aplikacija koje su kreirane u različitim aplikacijama. Pored toga mnogi jezici u svojoj standardnoj biblioteci imaju implementaciju JSON parsiranja, a ako nemaju onda sigurno postoji popularna biblioteka za to.

Primer JSON tipa podatka:

```json
{
    "port": 8080,
    "routes": [
        {
            "path": "/",
            "method": "GET",
            "handler": "index"
        }
    ],
    "handlers": {
        "index": {
            "type": "file",
            "path": "index.html"
        }
    }
}
```

Iznad vidimo primer JSON objekta koji možemo da očekujemo u nekom od HTTP odgovora. Da bi klijent pročitao HTTP odgovor JSON tipa i na adekvatan načina parsira podatke moramo podesiti **Content-Type** header na tip `application/json`. Bez tog headera klijent će verovatno parsirati odgovor kao da je u pitanju podrazumevana vrednost za taj header a to je `text/plain`. Primer jednog JSON odgovora bi bio:

```
HTTP/2 200 OK
Server: GitHub.com
Date: Tue, 11 Oct 2022 18:53:40 GMT
Content-Type: application/json; charset=utf-8
Content-Length: 69

{"login":"7aske","id":17355360,"html_url":"https://github.com/7aske"}
```

Ovde vidimo par osnovnih header-a i telo odgovora. Ključno je naglasiti da je takođe bitno, pored Content-Type headera, podesiti i **Content-Length** header da bi klijentska aplikacija znala koliko karaktera iz samog tela odgovora pročitati.

JSON je odlična alternativa XML-u za pisanje konfiguracionih fajlove jer je lakše pisati i razumeti JSON fajl nego XML fajl. Takođe postoji i YAML kao superset JSON-a koji ima drugačiju sintaksu i više funkcionalnosti jedna od kojih je postojanje komentara.


## Autentikacija i autorizacija

Autentikacija i autorizacija su osnovni koncepti kada su u pitanju aplikacije sa više nivoa pristupa.

### Autentikacija

Autentikacija predstavlja potvrđivanje identiteta korisnika na sistemu. U procesu autentikacije anonimni korisnik postaje autentikovani korisnik kome su pridružene dodatne informacije i kome može biti praćena sesija. 

Jedan od najosnovnijih metoda za autentikovanje je HTTP Basic autentikacija. U ovom procesu klijent šalje HTTP zahtev sa headerom **Authorization** koji sadrži podatke o korisničkom imenu i lozinki u formatu `username:password` kodirane u Base64. Primer HTTP zahteva sa Basic autentikacijom:

```
GET / HTTP/1.1
Host: 7aske.com
Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQ=
```

Ovaj tip autentikacije je jednostavan za implementaciju ali je i jednostavan za zloupotrebu. Zbog toga se ne preporučuje za korišćenje u produkciji.

Jedan od alternativnih pristupa je username/password autentikacija. U ovom pristupu klijent šalje HTTP zahtev sa korisničkim imenom i šifrom korisnika i korisnik dobija nazad neku vrstu token-a koji će na dalje služiti sa identifikaciju tog korisnika na sistemu. Token može biti kolačić, JWT itd.

### Autorizacija

Autorizacija predstavlja proces određivanja nivoa pristupa korisnika na sistemu. Autorizacija se obično vrši na osnovu uloga korisnika. Uloga je skup prava koja je povezana sa korisnikom. Uloga može biti povezana sa korisničkim imenom ili sa tokenom koji je dobijen u procesu autentikacije.

Na primer kada korisnik pristupa nekoj stranici na sajtu on može da bude ulogovan ili ne. Ako je ulogovan onda može da vidi neke informacije koje nisu vidljive anonimnom korisniku. Ako je korisnik ulogovan i ima određenu ulogu onda može da vidi još neke informacije koje nisu vidljive korisnicima sa drugim ulogama.

Konkretan primer autorizacije bi bila neka web strana - na primer Web Shop. Anonimni korisnik može da gleda proizvode i isključivo to. Ulogovani korisnik pored svakog proizvoda može imati opciju da naruči taj proizvod ili isti doda u korpu. Pored svega toga ulogovani korisnik koji je ujedno i menadžer ili administrator te prodavnice će pored svakog proizvoda imati dugme izmeni i/ili link ka administracionoj strani.

![Uloge korisnika i pristup](./assets/roles.png)
<div align="center">
Sl. 3 - <i>Uloge korisnika i pristup</i>
</div>

Suma sumarum autentikacija je proces potvrđivanja identiteta korisnika na sistemu, dok je autorizacija proces određivanja nivoa pristupa korisnika na sistemu.

Autorizacija se može vršiti, npr. kod HTTP zahteva, slanjem *JWT*-a (JSON web token) u headeru **Authorization**. Primer HTTP zahteva sa tokenom u headeru:

```
POST /products/1/order HTTP/1.1
Host: 7aske.com/shop
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwiaXNzIjoiZ3JhaW4iLCJuYW1lIjoiTmlrb2xhIFRhc2ljIiwiZGF0YSI6IkVhc3RlciBFZ2cifQ.7KWPpDkjNecJ_n6LQ280WdR6e_rQ1a93Mhi1MGcDYpE
```

U ovom slučaju HTTP zahtev se prosleđuje na server koji proverava da li je token validan i da li korisnik ima pravo da pristupi resursu koji je zatražio. Ukoliko je token validan i korisnik ima pravo pristupa resursu, server odgovara sa HTTP statusom 200 i podacima o resursu. JWT token sadrži informacije o tome koji je korisnik u pitanju, ko je kreirao token, koliko je vremenski validan token itd. JWT se validira na serveru i šanse za njegovu manipulaciju su svedene na minimum za razliku od kolačića.

JWT (JSON web token) je JSON objekat koji se šalje u headeru HTTP zahteva. JWT se sastoji od tri dela odvojenih tačkom:

1. Header - koji sadrži informacije o algoritmu koji je korišćen za enkripciju i tipu tokena

2. Payload - koji sadrži informacije o korisniku

3. Signature - koji je rezultat enkripcije headera i payloada

JWT objekat je kodiran u Base64.

## Umetanje zavisnosti

U srži većine modernih web radnih okvira leži sistem za umetanje zavisnosti (eng. *dependency injection* - DI). DI je dizajn patern koji za ulogu ima da objektima koje kreira umetne druge objekte (zavisnosti) od kojih zavisi. DI je forma inverzije kontrole (eng. *inversion of control* - IoC). DI za cilj ima SoC (eng. *separation of concerns*) odnosno da razdvoji logiku kreiranja objekata od biznis logike koju oni predstavljaju. Ovo za rezultat ima da su objekti međusobno veoma labavo povezani (eng. *loosy coupled*).

U praksi DI se korisiti tako što definišemo klasu i njen konstruktor - tipično se koristi konsturktor za DI, ali postoje i drugi načini o kojima ćemo kasnije. Parametri u konstruktoru biće podrazumevani kao zavisnosti koje sistem za DI treba da razreši. Tipovi parametara mogu da budu klase ili interfejsi. 

Primer u pseudo kodu bi izgledao ovako:

```
class UserService:
    constructor(user_repository: UserRepository, role_service: RoleService):
        this.user_repository = user_repository
        this.role_service = role_service
```

U ovom slučaju klasa **UserService** zavisi od **UserRepository** i **RoleService**. Ove zavisnosti se rešavaju tako što se u konstruktoru klase **UserService** prosleđuju objekti koji implementiraju interfejse **UserRepository** i **RoleService**. Kasnije možemo koristiti klasu UserService bez prethodnog znanja o tome da li i kako možemo kreirati objekte klasa/interfejsa UserRepository i RoleService. Na ovaj način se postiže inverzija kontrole - framework je umesto nas, developera, zadužen za kreiranje objekata.

![Dijagram umetanja zavisnosti](./assets/di.png)
<div align="center">
Sl. 4 - <i>Dijagram umetanja zavisnosti</i>
</div>

Na slici vidimo dijagram umetanja zavisnosti. Klasa **Client** ima jednu zavisnosti definisanu preko interfejsa **Interface**. U sistemu prikazanom na dijagramu postioje dve klase koje implementiraju dati interfejs. **Injector** može u toku izvršenja (eng. *runtime*) da odabere koja će implementacija zapravo biti korišćena za kreiranje objekta **Client**.

DI rešava umnogome problem kreiranja objekata, ali pri rešavanju tog problema može doći i do problema koji se zove cirkularna zavisnost. Cirkularna zavisnost je problem kada klasa u svom nizu zavisnosti ima samu sebe. Sledećim dijagramom predstavićemo ovaj problem.

### Cirkularna zavisnost

![Cirkularna zavisnost](./assets/circular-di.png)
<div align="center">
Sl. 5 - <i>Cirkularna zavisnost</i>
</div>

U situaciji opisanoj dijagramom nastaje problem gde ukoliko injector pokuša da kreira objekat **Client 1** on će prvo kreirati objekat **Client 2** i zatim objekat **Client 3**. Kada se kreira objekat **Client 3** on će pokušati da kreira objekat **Client 1**. Ovo će se ponavljati u beskonačnost. Rešavanje ovog problema zahteva ili reorganizaciju zavisnosti ili umetanje kroz neki drugi mehanizam koji dozvoljava instanciranje objekata.

### Vrste umetanja zavisnosti

Umetanje zavisnosti se može realizovati na više načina. Opisaćemo tipove i njihove prednosti i mane.

1. Umetanje kroz konstruktor - najčešći način umetanja zavisnosti. Prednost ovog načina je što je najjednostavniji za implementaciju i s obzirom na to da je konstruktor jedini način da se kreira instanca objekta, makar u Javi, pruža atomičnu akciju pri kojoj će sve zavisnosti obavezno biti prisutne u tom trenutku. Mana ovog pristupa je to što se dešava da zbog dizajna aplikacije nećemo moći da pružimo sve zavisnosti. Ostale metode DI rešavaju ovaj problem.

2. Umetanje kroz setere - ovaj način umetanja zavisnosti je sličan prethodnom, ali se zavisnosti umetaju kroz setere. Prednost ovog načina je što se zavisnosti mogu umetati u bilo kom trenutku, a ne samo u konstruktoru. Mana ovog pristupa je to što u trenutku instanciranja objekta mogu ali ne moraju biti razrešene sve njegove zavisnosti. Ovo često može dovesti do grešaka.

3. Umetanje kroz polja - ovaj način umetanja zavisnosti je takođe sličan svom prethodniku, ali se zavisnosti umetaju kroz polja - ne pozivom metoda. Prednosti i mane su gotovo identične ali što se tiče mana ima jednu dodatku - umetanje kroz polje uglavnom zahteva da postoji neki oblik introspekcije runtime-a u samom jeziku (u Javi je ovo **Reflection API**) i samim tim je sporije i često može doći do grešaka.

Zaključak je da je najbolje koristiti umetanje kroz konstruktor, ali da se u nekim situacijama može ili mora koristiti i umetanje kroz setere ili polja.

## MVC arhitektura

Predstavili smo osnovnu specifikaciju komunikacije koju će naš framework i aplikacija koja je napisana u njemu koristiti. Naredni korak je predstaviti arhitekturu aplikacije odnosno arhitekturalni šablon (eng. *pattern*) koji ćemo koristiti. Arhitekturalni pattern predstavlja skup pravila za struktuiranje i organizaciju projekta. Pored toga arhitekturalni pattern sadrži i skup pravila koje definišu tok podataka u projektu. Arhitekturalni šabloni su proistekli iz višedecenijskog iskustva u rešavanju sličnih problema i predstavljaju osnovu za razvoj projekta. Ovo kao i framework sam po sebi olakšava developerima rad na projektu jer definiše pravila za organizaciju istog. 

Za aplikaciju smo odabrali MVC arhitekturu/šablon zbog svoje popularnosti i jednostavnosti. MVC je skraćenica za **Model-View-Controller**. Ova arhitektura je jedna od najčešćih arhitektura u web aplikacijama. Osnovni principi ove arhitekture su:

* **Model** - predstavlja podatke i logiku aplikacije. Model je uvek nezavisan od View-a i Controller-a. Model može da se koristi i u drugim aplikacijama.

* **View** - predstavlja prikaz podataka. View je uvek nezavisan od Model-a i Controller-a. View može da se koristi i u drugim aplikacijama.

* **Controller** - predstavlja logiku aplikacije. Controller je uvek nezavisan od Model-a i View-a. Controller može da se koristi i u drugim aplikacijama.

![MVC arhitektura](./assets/mvc.jpg)
<div align="center">
Sl. 6 - <i>MVC arhitektura</i>
</div>

MVC arhitektura se trudi da odvoji logiku aplikacije od prikaza podataka. Ovo omogućava da se aplikacija lakše razvija i održava. Moderni MVC radni okviri prate ovaj šablon i time omogućavaju developerima da pišu čist i struktuiran kod. Ovo im pomaže da beneficiraju od svih nivoa modularnosti.

Tok komunikacije u MVC arhitekturi je sledeći:

1. Klijent (pretraživač) šalje zahtev serveru.

2. Server prima zahtev i prosleđuje ga Controller-u.

3. Controller obradjuje zahtev i poziva odgovarajući Model.

4. Model vrši potrebne izmene ili komunikaciju sa bazom i vraća Controller-u podatke.

5. Controller vrši potrebne izmene i vraća View-u.

6. View vrši potrebne izmene i vraća klijentu.

7. Klijent (pretraživač) prikazuje podatke.

### Struktura projekta

Projekti koji prate MVC arhitekturu su često i monolitni po dizajnu tako ćemo predstaviti jedan monolitni projekat po MVC arhitekturi kao primer:

```
src
├───controller
|   └───UserController.java
├───model
|   └───User.java
└───view
    └───user
        └───index.html

```

Ovo je najčešća struktura MVC projekata. Jedna iteracije ove strukture je uvođenje i servisnog layer-a koji je zadužen za domensku logiku dok je model layer zadužen isključivo za komunikaciju sa bazom.

```
Browser
↑    ↓
|    UserController.java
|    ↓              ↑ ↓
index.html        User.java
                    ↑ ↓
                  Database
```

Ovakav pristup omogućava lakši razvoj aplikacija jer se prati modularno struktuiranje klasa u projektu. Svi klase zadužene za controller layer se nalaze u controller paketu, view klase u view paketu itd.

## Kreiranje templating jezika

Obradili smo većinu koncepata za koje će radni okvir biti zadužen da apstrahuje. Preostala su nam dva koncepta: *view templating* i komunikacija sa bazom. U ovom poglavlju obradićemo *view templating* odnosno kreiranje templating jezika koji će nam omogućiti da kreiramo dinamičke strane generisane na serveru (eng. *server-side redndered*).

### Templating jezici

Templating jezici se koriste na serveru za prikaz dinamičkih stranica. Bez templejtinga ne bismo mogli da na jednostavan način u bilo koju stranu ubacimo dinamičke podatke. Na primer: zamislimo da imamo prodavnicu koja ima proizvode. Mi ne možemo bez prethodnog poznavanja broja proizvoda kreirati statičku stranicu. U najgorem slučaju i možemo ručno napisati HTML strane za svaki od proizvoda, ali šta ćemo kada se doda novi proizvod ili pak promeni cena nekog proizvoda. U takvim situacijama na scenu stupa *view templating*. Templating jezik/sistem se sastoji iz dva dela:

1. **Templating engine** - engine koji generiše dinamičke stranice na osnovu template-a i podataka.

2. **Template** - template je fajl koji sadrži statički deo stranice i specijalne tagove koji se koriste za dinamičko popunjavanje strane podacima.

Templating enigne popunjava template sa podacima i vraća rezultujući fajl klijentu. Templating engine može da koristi različite template jezike. Da bi lakše objasnili šta je templating jezik navešćemo popularne primere istih. Navešćemo primere 4 različita pristupa templejtingu u 3 različita programska jezika:

1. **Jinja2** - Python templating jezik

```jinja2
<!DOCTYPE html>
<html>
    <head>
        <title>Flask Jinja2 Example</title>
    </head>
    <body>
        <h1>Flask Jinja2 Example</h1>
        <p>Hello, {{ name }}.</p>
        {{ for item in items }}
            <p>{{ item }}</p>
        {{ endfor }}
    </body>
</html>
```
Jinja2 zasniva na sintaksi koja je slična Pythonu. Ovaj templating jezik je popularan u Python okruženju. Koristi `{{` i `}}` za indikovanje template blokova.


2. **Handlebars** - JavaScript templating jezik

```handlebars
<!DOCTYPE html>
<html>
    <head>
        <title>Handlebars Example</title>
    </head>
    <body>
        <h1>Handlebars Example</h1>
        <p>Hello, {{name}}.</p>
        {{#each items}}
            <p>{{this}}</p>
        {{/each}}
    </body>
</html>
```

Handlebars je templating jezik koji je popularan u JavaScript okruženju. Takođe kao i **Jinja2** koristi `{{` i `}}` za indikovanje template blokova. Handlebars jezik je sličan Mustache jeziku.

3. **JSP** - Java templating jezik

```jsp
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Example</title>
    </head>
    <body>
        <h1>JSP Example</h1>
        <p>Hello, <%= name %>.</p>
        <% for (String item : items) { %>
            <p><%= item %></p>
        <% } %>
    </body>
</html>
```

JSP je templating jezik koji je popularan u Java okruženju. Ovaj templating jezik koristi `<%` i `%>` za indikovanje template blokova. U JSP blokovima koji se inače zovu *skriptleti* (eng. *scriptlets*) možemo da pišemo gotovo sve funkcionalnosti Java programskog jezika. JSP je, iako relativno zastareo, veoma moćan jezik.

4. **Thymeleaf** - Java templating jezik

```thymeleaf
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
    <head>
        <title>Thymeleaf Example</title>
    </head>
    <body>
        <h1>Thymeleaf Example</h1>
        <p th:text="'Hello, ' + ${name} + '.'"></p>
        <p th:each="item : ${items}">
            <span th:text="${item}"></span>
        </p>
    </body>
</html>
```

Thymeleaf je templating jezik koji je popularan u Java okruženju i prirodni naslednik JSP-a. Ovaj templating jezik koristi `th:` za indikovanje template blokova. Thymeleaf se umnogome razlikuje od prethodnih template jezika u tome što se bez problema može interpretirati kao čista HTML strana jer su sve njegove funkcionalnosti u strane "markirane" kao HTML5 atributi. Ovo može da bude od velike pomoći prilikom razvoja dizajna stranice jer ne moramo da imamo funkcionalni backend aplikaciju da bismo prikazali sam HTML sadržaj.

### Templating engine

Templating engine je program koji se koristi za generisanje dinamičkih stranica na osnovu template-a i podataka. Templating engine može da koristi različite template jezike. Templating engine se sastoji iz dva dela:

1. **Prevodilac** - (eng. *compiler*) prevodilac koji parisra (prevodi) template i generiše apstraktno sintaksno stablo - AST (eng. *abstract syntax tree*) template-a. Template parser se sastoji iz dva dela:

    1. **Lexer** - lexer koji parsira template i generiše tokene. Tokeni su strukture koje sadrže informacije o tipu tokena i njegovoj vrednosti. Primer tokena je `{{` koji označava početak template bloka ili `if` koji označava if petlju.

        Lexer za zadatak ima da tekst template-a pretvori u tokene koji imaju veću semantičku vrednosti. Na primer sledeće parče koda:
        ```
        if (user == null) {
            return "Hello, guest.";
        } else {
            return "Hello, " + user;
        }
        ```
        bi se leksiralo u tokene:
        ```
        IF, LPAREN, IDENTIFIER, EQUAL, NULL, RPAREN, LBRACE,
            RETURN, STRING, SEMICOLON,
        RBRACE, ELSE, LBRACE,
            RETURN, STRING, PLUS, IDENTIFIER, SEMICOLON,
        RBRACE
        ```

        Ovakav format podataka je mnogo lakši za parsiranje u AST u sledećem koraku.

    2. **Parser** - sam parser koji parsira tokene i generiše AST. AST je struktura koja sadrži informacije o strukturi jezika. AST čvor uglavnom ima vrednost i levu i desnu stranu.

        Parser ima za zadatak da tokene na osnovu pravila sintakste programskog jezika kreira AST koje odgovara prosleđenim tokenima. Ako dođe do nepoklapanja pravila jezika sa očekivanim tokenima u toku parsiranja stabla nastaju tzv. sintaksne greške koje programer mora da ispravi.

        Prethodni primer bi se parsirao na sledeći način parsirao u AST:
        ```
        # skratili smo neka od imena čvorova radi preglednosti

                     IF
                    /|\
                   / | \ 
                  /  |  \ 
                 /   EQ  \
                /   /  \  \
               / IDEN  NULL\
              /             \
             BLOCK          BLOCK
              |              |
             RET            RET
              |              |
             STR            ADD
                           /   \
                         STR   IDEN
        ```

        Ovakva struktura je manje-više spremna da bude interpretirana i evaluirana u sledećem koraku.

2. **Interpretator** - interpretator (eng. *interpreter*) evaluira AST generisano u koraku kompajliranja i na osnovu njega generiše dinamičku stranu.
    
    Interpretator uzima početni čvor AST-a i redom po pravilima svakog čvora evaluira njegovu vrednost. Na primer, ako se u AST-u nađe čvor `ADD` interpretator će evaluirati vrednost njegovih levog i desnog čvora i zatim će izračunati njihovu vrednost - to će biti vrednost `ADD` čvora. Ako se u AST-u nađe čvor `IF` interpretator će evaluirati vrednost njegovog uslova i zatim će evaluirati vrednost njegovog bloka ako je uslov ispunjen. Finalni razultat ove interpretacije, u slučaju template engine-a biće validan HTML string koje će biti vraćen klijentu.

    Interpretator je najbitniji deo templating engine-a jer je on onaj koji evaluira AST i generiše dinamičku stranicu. Interpretator je najčešće implementiran kao rekurzivna funkcija koja prolazi kroz AST i evaluira njegove čvorove. Interpretator pored evaluacije vodi računa o konceptima programskog jezika kao što su globalne i lokalne promenljive, uvoz klasa i fajlova itd. Više o svim ovim konceptima govorićemo kada budemo obrđivali njihovu implementaciju u radnom okviru.

## ORM i komunikacija sa bazom

Na kraju, dolazimo do poslednje stavke koju ćemo teorijski obraditi. To je pojam ORM-a i komunikacije sa bazama podataka. Komunikacija sa bazom podataka je jedan od integralnih delova svake web aplikacije. Osim prezentacionih sajtova gotovo svaki sistem ima neki vid čuvanja (eng. *persistence*) podataka. Operacije na bazom podataka mogu biti obavljene na dva načina: direktno koristeći driver/biblioteku ili putem ORM-a koji apstrahuje sve operacije nad bazom u pozive metoda nad objektima/strukturama u jeziku u kome je implementiran.

### Direktan pristup

Kod direktnog pristupa bazi mi uglavnom ručno pišemo i popunjavamo upite ka samoj bazi. Primer ovog pristupa bio bi JDBC u Javi:

```java
// konekcija na bazu
Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydb", "user", "password");

// kreiranje upita
PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
stmt.setInt(1, 1);

// izvršavanje upita
ResultSet rs = stmt.executeQuery();

// čitanje rezultata
while (rs.next()) {
    System.out.println(rs.getString("name"));
}
```

Ovaj pristup ima nekoliko nedostataka. Ručno popunjavanje upita je veoma neefikasno i često dovodi do SQL injection napada. Kod se često duplira i kod kompleksnijih upita kod često bude teško održiv. Takođe, prilikom promene bilo koje od klasa za koje imamo napisane upite postoji velika šansa da ćemo morati većinu njih da ponovo napišemo. Takođe, bez dodatne konfiguracije upiti ne podležu sintaksnim proverama tako da bez integracionih testova greške u upitima ćemo otkriti tek u runtime-u. Kada su u pitanju performanse direktan pristup bazi i maksimalna fleksibilnost u kreiranju upita može da bude pogodna. Često se ORM pristup izbegava kod kompleksnih upita ili se pribegava pristupu kreiranja pogleda (eng. *view*).

### ORM pristup

**Object-relational mapping** ili ORM je pristup gde biblioteka pruža zajednički interfejs ka bilo kojoj podržanoj bazi podataka kroz klase/strukture u tom jeziku za koji je pisana. Primer ORM-a u Java programskom jeziku je **Hibernate**. Hibernate, ili bilo koji drugi ORM, koristi izmene nad objektima modelskih klasa da kreira upite koji će ekvivalentni red u bazi izmeniti u skladu sa izmenama na objektu. Iz toga proizilazi da je objekat mapiran u bazi podataka, odnosno tako dolazimo do *objektno-relacionog* mapiranja. S obzirom na to da se ORM često konfiguriše na osnovu runtime inspekcije podataka (pričali smo o Reflection) dolazi do problema sa performansama koje nekad mogu biti razlog zašto nekad ne želimo da koristimo ORM.

Primer korišćenja ORM-a u Javi:

```java
// kreiranje sesije

Session session = HibernateUtil.getSessionFactory().openSession();
session.beginTransaction();

// kreiranje objekta
User user = new User();
user.setName("John Doe");
user.setAge(25);

// čuvanje objekta u bazi
session.save(user);

// čitanje objekta iz baze
User user = (User) session.get(User.class, 1);

// izmena objekta
user.setName("Jane Doe");

// čuvanje izmena
session.update(user);

// brisanje objekta
session.delete(user);

// zatvaranje sesije
session.getTransaction().commit();
session.close();
```

Kao što vidimo ORM pruža veoma jednostavan i fleksibilan pristup radu sa bazom podataka. Vidimo da ne moramo da imamo bilo kakvu informaciju o tome koja je baza podataka u pitanju jer će ORM generisati sve upite prilikom obavljanja operacija. Ukoliko se koristi ORM pristup, često se koristi i *migration* alat koji omogućava da se model baze podataka nesmetano modifikuje u skladu sa modifikacijom klasa, u ORM terminologiji - entiteta (eng. *entity*). Neki od poznatijih Java migracionih alata su **Flyway** i **Liquibase**. Oni omogućavaju verzioniranje modela baze time što se svaka izmena baze beleži kroz migracione skripte. Na taj način postiže to da je model baze uvek ponovljiv i na novim mašinama i serverima na kojim se vrši deployment aplikacije.

### Query builder pristup

Još jedan od pristupa za komunikaciju sa bazom podataka je *query builder* pristup. Ovaj pristup leži negde između pisanja upita i korišćenja ORM-a. Kod korišćenja query buildera imamo benefite validacije upita jer koristimo dinamički interfejs (neka vrsta DSL-a) i benefite performansi koje nam daje korišćenje upita.

Primer korišćenja **Querydsl** query buildera u Javi:

```java
// kreiranje upita
JPAQuery query = new JPAQuery(em);
QUser user = QUser.user;
List<User> users = query.from(user).where(user.name.eq("John Doe")).list(user);

// izmena user-a
JPAUpdateClause update = new JPAUpdateClause(em, user);
update.where(user.name.eq("John Doe")).set(user.name, "Jane Doe").execute();

// brisanje user-a
JPADeleteClause delete = new JPADeleteClause(em, user);
delete.where(user.name.eq("John Doe")).execute();
```

### Zaključak

Koji god pristup izaberemo moramo imati na umu koje zahteve imamo koji se tiču performansi, obim i kompleksnost modela, održavanje itd. ali u većini slučajeva ORM je sasvim zadovoljavajuće rešenje koje umnogome olakšava razvoj web aplikacija.

Sada kada imamo teorijsku osnovu šta sve jedan radni okvir treba da pokrije možemo da krenemo sa objašnjenjem kako je svaki od tih koncepata implementiran u **Grain** radnom okviru.

# Studija slučaja

## Grain

**Grain** je radni okvir koji je nastao kao rezultat potrebe za jednostavnim i fleksibilnim alatom za razvoj web aplikacija. Uz pomoć **Grain** radnog okvira možemo da razvijamo web aplikacije u **Java** programskom jeziku koje lako mogu da budu proširene bilo kojim drugim bibliotekama jer Grain framework sam po sebi podržava **dependency injection**. Grain radnom okviru je velika inspiracija **Spring** i **Spring Boot**, što ćemo videti u narednim primerima. Primeri će se sastojati od proširenja koncepta koji je opisan u poglavlju gde smo obrađivali teorijsku postavku, primera u Grain radnom okviru, primera u Spring Boot-u i opisa implementacije.

## HTTP

Prva i osnovna funkcionalnost web okvira je da ima mogućnost kreiranja servera odnosno proces koji može da razmenjuje informacije HTTP protokolom. Za potrebe ove funkcionalnosti proces mora da ima mogućnost da otvori osluškujući (eng. *listening*) *socket* na *host* računaru. Zatim da čita podatke koji stižu na taj socket u neblokirajućem (eng. *non-blocking*) režimu - što će reći paralelno koristeći više niti (eng. *thread*). Pročitane podatke mora parsirati u HTTP zahtev i imati mogućnost da HTTP odgovor upiše nazad.

### Primer

Implementacija osnovnog HTTP servera koji vraća bazični odgovor na HTTP zahtev:

```java
@SpringBootApplication
public class HttpServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HttpServerApplication.class, args);
    }

    @Controller
    @RequestMapping("/")
    public class HttpServerController {
        @GetMapping
        public @ResponseBody String index() {
            return "Hello World!";
        }
    }
}
```

Nakon startovanja ove Spring Boot aplikacije ukoliko u pretraživaču odemo na `http://localhost:8080` dobićemo odgovor `Hello World!`. Vidimo da postoji veoma malo koda koji mora biti napisan da bi se dobila ova bazična funkcionalnost. Neki delovi koda mogu i da budu obrisani ali su ostavljeni zbog čitljivosti.

Ekvivalentna implementacija u Grain radnom okviru:

```java
public class HttpServerApplication extends GrainApp {
    public static void main(String[] args) {
        GrainAppRunner.run(HttpServerApplication.class);
    }

    @Controller
    @RequestMapping("/")
    public class HttpServerController {
        @GetMapping
        public String index() {
            return "Hello World!";
        }
    }
}
```

Možemo da vidimo da su implementacije ove funkcionalnosti veoma slične u oba radna okvira. Kao i kod Spring aplikacije odlaskom na `http://localhost:8080` dobićemo odgovor `Hello World!`.

### Implementacija

U nastavku ćemo detaljnije objasniti kako je implementirana osnovna funkcionalnost HTTP servera u Grain radnom okviru. Iako je ovo veoma bazična funkcionalnost, da bi došli od `GrainAppRunner.run()` do `Hello World!` u pretraživaču moramo da konfigurišemo nekoliko podsistema.

Prilikom startovanja aplikacije `GrainAppRunner` kreira i konfiguriše instancu klase `GrainApp` odnosno korisničke klase koja nasleđuje `GrainApp`.

Koraci inicijalizacije su sledeći:

1. Kreiranje `Configuration` objekta i učitavanje konfiguracije

    `Configuration` objekat sadrži sve ključ-vrednost parove koji predstavljaju kofiguraciona podešavanja za framework. On je neophodan za inicijalizaciju aplikacije jer postoje parametri koji utiču na istu.

    1.0. Učitavanje aktivnih profila - profili su podešeni koristeći `GRAIN_PROFILES_ACTIVE` environment promenljivu 

    1.1. Učitavanje konfiguracije iz `application.properties` fajla i odgovarajućih `.properties` fajlova na osnovu aktivnog profila.

    1.1. Učitavanje konfiguracije iz okruženja (eng. *environment*). Ova konfiguracija ima prednost u odnosu na `.properties` fajlove.

    ```java
    String profilesString = Optional.ofNullable(System.getenv(PROFILES_ENV_VARIABLE))
            .orElse(",");

    // učitavanje profila iz okruženja
    List<String> profiles = Arrays.stream(profilesString
                    .split("\\s*,\\s*"))
            .collect(Collectors.toList());


    // učitavanje konfiguracije iz .properties fajlova
    PropertiesResolver propertiesResolver = new PropertiesResolver(profiles);
    propertiesResolver.resolve("META-INF/application",
            properties::load);
    propertiesResolver.resolve("application",
            properties::load);

    // učitavanje konfiguracije iz okruženja
    EnvironmentResolver environmentResolver = new EnvironmentResolver();
    environmentResolver.resolve(this::set);

    // vraćanje konfiguracije u okruženje (environment)
    // ovo olakšava pristup konfiguraciji iz drugih delova aplikacije
    // koji zahtevaju svoje konfiguracione fajlove tako da i one mogu
    // biti konfigurisane preko .properties fajlova
    properties.forEach((key, value) ->
            System.setProperty(key.toString(), value.toString()));
    ```

2. Kreiranje `ApplicationContext` objekta
    
    `ApplicationContext` interfejs predstavlja srž aplikacije. On sadrži `DependencyContainer` koji sadrži sve inicijalizovane komponente koje su kreiranje u toku umetanja zavisnosti. O tome ćemo govoriti u kasnijem poglavlju. Ovaj kontekst objekat ima jednu statičku instancu kojoj se može pristupiti putem `ApplicationContextHolder` singltona. `ApplicationContext` se takođe može ručno instancirati koristeći `ApplicationContextImpl` klasu koja mu je ujedno i jedina implementacija.

    2.1. Odigravanje dependency injection životnog ciklusa - `ApplicationContext` je zadužen za pokretanje dependency injection životnog ciklusa:

    ```java
    public ApplicationContextImpl(String basePackage, Configuration configuration) {
        this.basePackage = basePackage;
        this.configuration = configuration;
        // kreiramo injector
        GrainInjector injector = new GrainInjector(configuration);

        // ucitavamo sve klase koje su anotirane sa odgovarajućim anotacijama
        Set<Class<?>> classes = Arrays.stream(new String[]{GrainApp.getBasePackage(), basePackage})
            .flatMap(pkg -> new GrainJarClassLoader(pkg)
                .loadClasses(cl -> !cl.isAnnotation() && isAnnotationPresent(cl, Grain.class))
                .stream())
            .collect(Collectors.toCollection(LinkedHashSet::new));
        // dodajemo sve klase u dependency injection pipeline
        injector.inject(classes);

        // preuzimamo gotov konfigurisan dependency container
        // koji je zadužen za čuvanje svih inicijalizovanih komponenti
        this.dependencyContainer = grainInitializer.getContainer();
    }

    ```
3. Otvaranje socket-a za HTTP server

    Socket mora da bude spreman da paralelno obrađuje zahteve. Stoga kreiramo thread-pool koji je zadužen za to.

    ```java

    // kreiramo thread pool koji će biti zadužen za obradu zahteva
    ExecutorService executor = Executors.newFixedThreadPool(configuration.getInt(ConfigurationKey.SERVER_THREADS));

    // kreiramo server socket
    try (ServerSocket serverSocket = new ServerSocket(configuration.getInt(ConfigurationKey.SERVER_PORT), -1, InetAddress.getByName(configuration.get(ConfigurationKey.SERVER_HOST)))) {

        // sve dok je aplikacija "running" slušamo zahteve
        // i izvršavamo ih u thread pool-u
        while (running) {
            Socket socket = serverSocket.accept();
            executor.execute(new RequestHandlerRunnable(context, socket));
        }

    } catch (UnknownHostException e) {
        throw new AppInitializationException("Unable to resolve host " + configuration.get(ConfigurationKey.SERVER_HOST), e);
    } catch (IOException e) {
        throw new AppInitializationException("Unable to create server socket", e);
    }
    ```

    Za opsluživanje zahteva koristimo `RequestHandlerRunnable` koji je zadužen za parsiranje zahteva, pozivanje odgovarajućeg "handlera" i slanje odgovora. Ova klasa naravno implementira `Runnable` interfejs koji joj omugućava da se izvršava na odvojenom thread-u. Više o ovoj klasi ćemo govoriti kada budemo pričali o tome kako se procesuira HTTP zahtev.

Po završetku ovih koraka imamo kreiran HTTP server koji je spreman da sluša zahteve na podrazumevanom port-u 8080.

```log
17-10-2022 12:37:42.227   DEBUG - [           main] c._.grain.core.component.GrainInjector   : Evaluating @Value annotations
17-10-2022 12:37:42.227   DEBUG - [           main] c._.grain.core.component.GrainInjector   : Calling lifecycle methods
17-10-2022 12:37:42.233   DEBUG - [           main] c._.grain.core.component.GrainInjector   : Loaded 51 Grain classes
17-10-2022 12:37:42.233    INFO - [           main] com._7aske.grain.GrainApp                : Initialized application context
17-10-2022 12:37:42.233   DEBUG - [           main] com._7aske.grain.GrainAppRunner          : Startup took 1614ms
17-10-2022 12:37:42.233    INFO - [           main] com._7aske.grain.GrainApp                : Started Grain application on 0.0.0.0:8080
```

# Primer gotove aplikacije

# Zaključak

# Reference

1. Perforce Software Inc., 2022, *2021 Java Developer Productivity Report*, [https://www.jrebel.com/resources/java-developer-productivity-report-2021](https://www.jrebel.com/resources/java-developer-productivity-report-2021)

2. Wikipedia, 2022, *Hypertext Transfer Protocol*, [https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol](https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol)

3. David H. Crocker, 1982, *RFC 822 - STANDARD FOR THE FORMAT OF ARPA INTERNET TEXT MESSAGES*, Dept. of Electrical Engineering University of Delaware, [https://www.rfc-editor.org/rfc/rfc822](https://www.rfc-editor.org/rfc/rfc822)

4. IETF, 1999, *RFC 2616 - Hypertext Transfer Protocol -- HTTP/1.1*, [https://www.rfc-editor.org/rfc/rfc2616](https://www.rfc-editor.org/rfc/rfc2616)

5. IETF, 2017, *RFC 8259 - The JavaScript Object Notation (JSON) Data Interchange Format*, [https://www.rfc-editor.org/rfc/rfc8259](https://www.rfc-editor.org/rfc/rfc8259)

6. IETF, 2015, *RFC 7519 - JSON Web Token (JWT)*, [https://www.rfc-editor.org/rfc/rfc7519](https://www.rfc-editor.org/rfc/rfc7519)
