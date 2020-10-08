# SE211 DZ02

## Prikazati strukturni način programiranja

```go
func (d *Type) Run(inst instance.JSON) (*instance.Instance, error) {
	if _, ok := GetRunningInstanceById(inst.Id); ok {
		return nil, errors.New("instance already running")
	}
	switch inst.Backend {
	case instance.Node:
		return d.runNode(inst)
	case instance.Python:
		return d.runPython(inst)
	case instance.Web:
		return d.runWeb(inst)
	case instance.Flask:
		return d.runFlask(inst)
	case instance.Npm:
		return d.runNpm(inst)
	}
	return nil, errors.New("backend not implemented")
}
```

## Opisati na koji način je moguće prepoznati strukturni način programiranja

Struktuirani nacin programiranja se moze prepoznati po upotrebi kontrolnih strukura kao sto su `for`, `if`, `while`, `switch` etc. I po jasnom nedostatku `goto` naredbe koja se smatra veoma stetnom za jasnocu i citljivost koda.

## Prikazati upotrebu return instrukcije u aplikaciji

```rust
pub fn endianness(buffer: &[u8]) -> Result<Endianness, EndiannessError> {
    return if TIFF_MM.iter().zip(buffer.iter()).all(|(a, b)| a == b) {
        Ok(Endianness::BigEndian)
    } else if TIFF_II.iter().zip(buffer.iter()).all(|(a, b)| a == b) {
        Ok(Endianness::LittleEndian)
    } else {
        Err(EndiannessError)
    };
}
```
Rust koristi poslednju instrukciju (bez `;`) kao vrednost za `return` instrukciju. Ovo ima smisla jer je uglavnom rezultat poslednje instrukcije na vrhu steka. Drugi nacin je primena `return if` kontrukcije.

## Da li je moguće koristiti neku drugu instrukciju umesto return u aplikaciji?

Ne. Jer return instrukcija ima za ulogu da izmedju ostalog 'vrati' vrednost iz podprocedure u kojoj se poziva. Ako je u pitanju podprocedura koja nema povratnu vrednost moguce je iskoristiti `goto` instrukciju u jezicima koji je podrzavaju sto doduse nije preporucljivo.