# CS225 DZ05

## Zadatak 1

* Napisati cron mehanizam koji preokreće skriptu /home/cs225/cs225.sh svakog radnog dana (od ponedeljka do petka) u period od 09h do 17h u intervalima od sat vremena.
	```cron
		0 9-17 * * 1-5 bash /home/cs225/cs225.sh
	```
* Napraviti cron mehanizam koji pokreće ping komandu prema serveru metropolitan.ac.rs, u podne, prvog dana u mesecu, svaki mesec.
	```cron
		0 12 1 * * ping metropolitan.ac.rs
	```
* Napisati cron mehanizam koji preokreće skriptu na putanji /home/cs225/cs225.sh svakih deset minuta kao root korisnik.
	```cron
		*/10 * * * * root bash /home/cs225/cs225.sh
	```

## Zadatak 2

![](./ss1.png)

### Napisati bash skripte koje:

* Nalaze sve datoteke koje pripadaju aktivnom korisniku čija veličina je manja od 2M i koji u svom imenu sadrže slovo i na bilo kojoj lokaciji, praćeno bilo kojim karakterima.
	```bash
		find . -type d -user $USER -size -2M -regex '.*[A-Za-z].*'
	```

* Nalaze sve direktorijume koji pripadaju aktivnom korisniku koji u svom imenu sadrže cifru 2.
	```bash
		find . -type d -user $USER -name '*2*'
	```

* Nalaze sve datoteke ili direktorijume koji us svom imenu sadrže bilo koje karaktere, praćene slovom e, iza kojeg se nalaze bilo koji karakteri i broj 33. Iza broja 33 može ali i ne mora da se nalazi još karaktera.
	```bash
		find . -name '*e*33*'
	```
