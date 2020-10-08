#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define pinc(p) if (p != NULL) { *p=0; p++; }
#define sstrip(p, chr) while(*p == chr) { p++; }

/*
18. (Zajednički prefiks) Napisati metod koji vraća najduži zajednički prefiks dva stringa. Na primer,
najduži zajednički prefiks stringova distance i disinfection je dis. Zaglavlje metoda treba da bude:
public static String prefix(String s1, String s2)
Ukoliko dva stringa nemaju zajednički prefiks, rezultat treba da bude prazan string. Napisati i glavni
main metod pomoću kojeg se učitavaju stringovi iz konzole, poziva metod prefix, a zatim i štampa
najduži zajednički prefiks dva stringa.
*/

char* strnpref(char* str1, char* str2, int limit) {
	char* out = (char*) calloc(1, sizeof(char));
	if (!str1 || !str2) {
		return out;
	}
	sstrip(str2, ' ')
	char* str1p = str1;
	char* str2p = str2;
	int n = 0;
	while (*str1p == *str2p && limit > 0) {
		str1p++;
		str2p++;
		limit--;
		n++;
	}

	if (n > 0) {
		out = (char*) realloc(out, (n + 1) * sizeof(char));
		strncpy(out, str1, n);
	}
	return out;
}
// echo "phonetic phony" | cmake-build-debug/cs103_dz01_nikola_tasic_3698
// "phon"
// echo "test garden" | cmake-build-debug/cs103_dz01_nikola_tasic_3698
// ""
int main(int argc, char** argv) {
	#define MAX_LEN 256
	char* buf = NULL, * tok = NULL, * out = NULL;
	if (argc == 1) {
		buf = (char*) calloc(MAX_LEN, sizeof(char));
		fgets(buf, MAX_LEN, stdin);
		sstrip(buf, ' ')
		tok = strchr(buf, ' ');
		pinc(tok)
	} else if (argc == 3) {
		buf = argv[1];
		tok = argv[2];
	}
	out = strnpref(buf, tok, MAX_LEN);
	if (out) {
		printf("%s\n", out);
	}
	return 0;
}