#include <stdio.h>
#include <assert.h>

/*9) Pretpostaviti da prodavnica ima 6 zaposlenih radnika sa ID-ovima 909, 185, 657, 116, i 150.
Pretpostaviti da heš tabela, HT, ima 7 polja označenih indeksima 0, 1, 2, . . ., 6. Pokazati kako se
ID-ovi zaposlenih, dati u prethodno definisanom redosledu, pakuju u heš tabelu, HT, korišćenjem
heš funkcije h(k) = k % 7. Koristiti kvadratnu funkciju c i2
za razrešavanje kolizije.*/


// podesavamo tih 7 pocetnih mesta
#undef HTABLE_NMEMB
#define HTABLE_NMEMB 7

// sprecavamo sirenje tabele na 50% popunjenosti
#undef HTABLE_THOLD
#define HTABLE_THOLD 0.99f

#include "../structs/include/htable.h"

struct employee {
	unsigned long id;
	char fname[32];
	char lname[32];
} employee_t;


unsigned long z09probefunc(int nmemb, unsigned long hash) {
	static unsigned long c = 0;
	c++;
	printf("Hash position %lu is taken\n", hash);
	printf("Re-hashing %lu ", hash);
	hash = (hash + c * c) % nmemb;
	printf("into %lu using (h + c^2) %% n\n", hash);
	return hash;
}


int main() {
	struct employee e1 = {909u, "JAMES", "JOHNSON"};
	struct employee e2 = {185u, "JOHN", "SMITH"};
	struct employee e3 = {657u, "ROBERT", "WILLIAMS"};
	struct employee e4 = {116u, "MICHAEL", "BROWN"};
	struct employee e5 = {150u, "WILLIAM", "GARCIA"};
	struct employee e6 = {346u, "DAVID", "DAVIS"};

	htable_t* emptable = htable_new(sizeof(struct employee));
	emptable->probefunc = z09probefunc;
	printf("Hashing employee: {%lu, '%s', '%s'} into %lu\n", e1.id, e1.fname, e1.lname, _hashfunc(emptable->nmemb, &e1,
																								  sizeof(struct employee)));
	htable_add(emptable, &e1);
	printf("Hashing employee: {%lu, '%s', '%s'} into %lu\n", e2.id, e2.fname, e2.lname, _hashfunc(emptable->nmemb, &e2,
																								  sizeof(struct employee)));
	htable_add(emptable, &e2);
	printf("Hashing employee: {%lu, '%s', '%s'} into %lu\n", e3.id, e3.fname, e3.lname, _hashfunc(emptable->nmemb, &e3,
																								  sizeof(struct employee)));
	htable_add(emptable, &e3);
	printf("Hashing employee: {%lu, '%s', '%s'} into %lu\n", e4.id, e4.fname, e4.lname, _hashfunc(emptable->nmemb, &e4,
																								  sizeof(struct employee)));
	htable_add(emptable, &e4);
	printf("Hashing employee: {%lu, '%s', '%s'} into %lu\n", e5.id, e5.fname, e5.lname, _hashfunc(emptable->nmemb, &e5,
																								  sizeof(struct employee)));
	htable_add(emptable, &e5);
	printf("Hashing employee: {%lu, '%s', '%s'} into %lu\n", e6.id, e6.fname, e6.lname, _hashfunc(emptable->nmemb, &e6,
																								  sizeof(struct employee)));
	htable_add(emptable, &e6);

	assert(6 == htable_size(emptable));
	return 0;
}
