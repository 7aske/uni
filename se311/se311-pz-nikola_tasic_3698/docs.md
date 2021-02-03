\\
\
\
\
\
\
\

 <div align="center">
 
 ![](../assets/logo.png) 

 </div>

 <div align="center">
 
\
\
\
\
\
\
\
\
\

## Prolećni semetar, 2020/21

## *SE311: PROJEKTOVANJE I ARHITEKTURA SOFTVERA*

## Projektni zadatak

</div>

\
\
\
\
\
\
\
\
\
\
\
\
\
\
\
\
\
\
\
\

Ime i prezime: **Nikola Tasić**

Broj indeksa: **3698**

\
\
\
\
\
\
\
\
\
\

# Odabir sistema

---

Razvija se sistem elektronskog odnosno online savetovališta za osobe koje preko svog telefona ili veb pretraživača žele da komuniciraju sa lekarom. Sistem se ravija tako da pruži mogućnost odgovora na pitanja koja potencijalni pacijenti mogu da imaju bez bespotrebnog odlaska u zdravstvenu ustnaovu. Sistem će funkcionisati tako što korisnici putem svog telefona ili veb interfejsa šalju poruke sa opisima svojih simptoma ili pitanja koja imaju u vezi svog stanja odgovarajućem lekaru i tako započinju dvosmernu komunikaciju. Lekari će biti odabrani od strane stručnog administratora sistema. Lekari koje odgovaraju na pitanja to mogu uraditi putem mobilne aplikacije ili veb interfejsa. Takođe kao i lekari, korisnici imaju ova dva načina za slanje svojih poruka. Poruke koje korisnici šalju mogu biti anonimne.

# Definisanje faza i projektnih aktivnosti

---

Pri razvoju ove aplikacije primenjivaće se agilni razvoj softvera da bi se što pre sitglo do MVP-(minimum viable product). Osnovne funkcionalnosti na kraju inicijalne faze razvoja treba da omoguće korisniku da ima neometanu i bezbednu dvosmernu komunikaciju sa odabranim lekarom.

Inicijalna aktivnost jeste inženjerstvo zahteva na osnovu intervjua sa potencijalnim korisnicima sistema kao i sa lekarima. Takođe je neophodno definisati nefunkcionalne zahteve koji se tiču privatnosti i sigurnosti informacija.

Projektovanje arhitekture softvera najviše zavisi od zahteva i potencijalnih modifikacija softvera usled izmene inicjinih zahteva bilo to usled defekata ili dodavanja funkcionalnosti.

Implementacija sistema se vrši uz kontinualno testiranje koristeći TDD(test driven development) tehniku razvoja radi smanjenja grešaka u sistemu.

Testiranje se vrši na kraju svakog sprinta i takođe na kraju celokupnog projekta.

Poslednja faza je održavanje softvera koje uključuje otklanjanje defekata i dodavanje novih funkcionalnosti.

# Zahtevi

1. Funkcionalni zahtevi

	1. Administrator

		Administrator vrši inspekciju trenutnog rada lekara i ima uvid u broj aktvinih konverzacija sa klijentima i njihovim statusima. Takođe administator vrši dodavanje novih lekara na sistem na osnovu preporuke stručnog lica.
	2. Lekar

		Lekari imaju pristup interfejsu gde imaju mogućnost pregleda primljenih ali i aktivnih poruka sa klijentima. Lekari imaju mogućnost odgovaranja i razmenjivanja poruka sa klijentima.
	
	3. Klijent

		 Klijent ima pristup interfejsu gde može da na osnovu kategorije svog problema odabere ili dobije predloženog lekara  sa kojim može započeti konverzaciju slanjem inicijalne poruke sa opisom istog.

	4. ...

1. Nefunkcionalni zahtevi

	* Aplikacija mora da zaštiti pravo privatnosti korisnika
	* Aplikacija mora da poštuje najskorije standarde o privatnosti i bezbednosti podataka
	* Aplikacija mora da poštuje zakone u zdravstvu
	* ...
