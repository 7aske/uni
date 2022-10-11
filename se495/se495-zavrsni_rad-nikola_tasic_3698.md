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

## Kreiranje templating jezika

## ORM i komunikacija sa bazom

# Studija slučaja

# Primer gotove aplikacije

# Zaključak

# Reference

1. Perforce Software Inc., 2022, *2021 Java Developer Productivity Report*, [https://www.jrebel.com/resources/java-developer-productivity-report-2021](https://www.jrebel.com/resources/java-developer-productivity-report-2021)

2. Wikipedia, 2022, *Hypertext Transfer Protocol*, [https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol](https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol)

3. David H. Crocker, 1982, *RFC 822 - STANDARD FOR THE FORMAT OF ARPA INTERNET TEXT MESSAGES*, Dept. of Electrical Engineering University of Delaware, [https://www.rfc-editor.org/rfc/rfc822](https://www.rfc-editor.org/rfc/rfc822)

4. IETF, 1999, *RFC 2616 - Hypertext Transfer Protocol -- HTTP/1.1*, [https://www.rfc-editor.org/rfc/rfc2616](https://www.rfc-editor.org/rfc/rfc2616)

5. IETF, 2017, *RFC 8259 - The JavaScript Object Notation (JSON) Data Interchange Format*, [https://www.rfc-editor.org/rfc/rfc8259](https://www.rfc-editor.org/rfc/rfc8259)

6. IETF, 2015, *RFC 7519 - JSON Web Token (JWT)*, [https://www.rfc-editor.org/rfc/rfc7519](https://www.rfc-editor.org/rfc/rfc7519)
