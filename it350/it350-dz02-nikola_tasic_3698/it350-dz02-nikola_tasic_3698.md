 simbol   |                opis
:-----:   |:------------------:
   &pi;   |          projekcija
&sigma;   |           selekcija
  &chi;   |   dekartov proizvod 
  &cup;   |               unija
  &cap;   |              presek
  &#10781;|                join
---
1. Pronaći sve brojeve modela crno-belih štampača
2. Koji PC modeli imaju brzinu jednaku 50 GHz
3. Pronađite parove proizvođača sa najmanje 2 različita računara (PC ili laptop) sa brzinama ne manjim od 2.80
4. Pronađite sve laptopove čija je cena veća od 1000
5. Pronađite proizvođače računara sa najmanje tri različite brzine
6. Pronađite proizvođače računara koji prodaje tačno tri različita modela PC-a

---
1. &pi; <sub>model</sub> (&sigma; <sub>color=false</sub> (Printer))
2. &pi; <sub>model</sub> (&sigma; <sub>speed=50GHz</sub> (PC))
3. &pi; <sub>maker</sub> (&sigma; <sub>type=PC&or;type=laptop</sub> (Product)) &chi; &pi; (&sigma; <sub>speed&ge;2.80&and;COUNT&ge;2</sub> (&pi; <sub>speed</sub> (PC) &cup; &pi; <sub>speed</sub> (Laptop)))
4. &pi; (&sigma;<sub>price&gt;1000</sub> (Laptop))
5. &pi; <sub>maker,model</sub> (&sigma; <sub>type=PC&or;type=laptop</sub> (Product)) &#10781; &pi; (&sigma; <sub>COUNT&ge;3</sub> (&pi; <sub>speed,model</sub> (PC) &cup; &pi; <sub>speed,model</sub> (Laptop)))
6. &pi; <sub>maker,model</sub> (&sigma; <sub>type=PC&or;type=laptop</sub> (Product)) &#10781; &pi; ( &sigma; <sub>COUNT=3</sub> (&pi; <sub>speed,model</sub> (PC) &cup; &pi; <sub>speed,model</sub> (Laptop)))
---
1. Sporazum u Vašingtonu iz 1921 godine zabranjuje korišćenje brodova težih od
35000 tona. Pronaći brodove koji krše ovaj sporazum
2. Pronaći sve zemlje koje su posedovale borbene brodove (bb) i borbene kruzere (bc)
---
1. &pi; <sub>name</sub> (&sigma; <sub>displacement>35000</sub> (Ships &#10781; Class))
2. &pi; (&pi; <sub>country</sub> (&sigma; <sub>type=bc</sub> (Ships &#10781; Class))) &cap; (&pi; <sub>country</sub> (&sigma; <sub>type=cc</sub> (Ships &#10781; Class)))