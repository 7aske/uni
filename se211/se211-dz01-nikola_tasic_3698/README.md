# SE211 DZ01

## Prikazati primer dobro formatirane klase unutar aplikacije

Primer komentarisanja koda preuzet iz source koda [Linux kernela](https://github.com/torvalds/linux/blob/master/fs/debugfs/internal.h) iz fajla:

`fs/debugfs/internal.h`

```c
// SPDX-License-Identifier: GPL-2.0
/*
 *  internal.h - declarations internal to debugfs
 *
 *  Copyright (C) 2016 Nicolai Stange <nicstange@gmail.com>
 */

#ifndef _DEBUGFS_INTERNAL_H_
#define _DEBUGFS_INTERNAL_H_

struct file_operations;

/* declared over in file.c */
extern const struct file_operations debugfs_noop_file_operations;
extern const struct file_operations debugfs_open_proxy_file_operations;
extern const struct file_operations debugfs_full_proxy_file_operations;

struct debugfs_fsdata {
	const struct file_operations *real_fops;
	refcount_t active_users;
	struct completion active_users_drained;
};

/*
 * A dentry's ->d_fsdata either points to the real fops or to a
 * dynamically allocated debugfs_fsdata instance.
 * In order to distinguish between these two cases, a real fops
 * pointer gets its lowest bit set.
 */
#define DEBUGFS_FSDATA_IS_REAL_FOPS_BIT BIT(0)

#endif /* _DEBUGFS_INTERNAL_H_ */
```



## Navesti vrste komentara i načine na koje je moguće komentarisati programski kod

* Generalni komentar na pocetku samog fajla. On opisuje svrhu fajla. Izmedju ostalog taj deo komentara moze da sadrzi informacije o autoru zajedno sa kontakt informacijama (npr. email) ili na primer informacije o licenci izvornog koda koji zadrzi.

* Komentarisanje metoda i funkcija. Komentari ovde sluze da pojasne sta funkcija ili metoda radi i opciono opisuje povratni tip podataka kao i sve parametre koje funcija prima kao argumente.

* Komentarisanje individualnih linija. Pomaze da objasni rezultat odredjene linije. To moze da bude korisno u slucajevima gde se mora isposovati odredjena procedura izvrsavnja instrukcija ili na primer kod odredjenih optimizacija gde moze doci do necitljivog koda. Slican primer je i komentarisanje klasa ili sturktura.

## Prikazati način na koji se vrši komentarisanje programskog koda unutar aplikacije

Ovde vidimo primer opisivanja informacija svakog atributea jedne strukture po odredjenom standardu opisanom na [kernel.org](https://kernel.org).

```c
/**
 * caam_ctx - per-session context
 * @flc: Flow Contexts array
 * @key:  [authentication key], encryption key
 * @flc_dma: I/O virtual addresses of the Flow Contexts
 * @key_dma: I/O virtual address of the key
 * @dir: DMA direction for mapping key and Flow Contexts
 * @dev: dpseci device
 * @adata: authentication algorithm details
 * @cdata: encryption algorithm details
 * @authsize: authentication tag (a.k.a. ICV / MAC) size
 */
struct caam_ctx {
	struct caam_flc flc[NUM_OP];
	u8 key[CAAM_MAX_KEY_SIZE];
	dma_addr_t flc_dma[NUM_OP];
	dma_addr_t key_dma;
	enum dma_data_direction dir;
	struct device *dev;
	struct alginfo adata;
	struct alginfo cdata;
	unsigned int authsize;
};
```