/*Napisati program koji predstavlja graf preko matrice povezanosti i dozvoljava korisniku
da odabere dva čvora. Program zatim treba da proveri da li postoji direktan ili indirektan
put od prvog do drugog unetog čvora i o tome da obavesti korisnika.*/

#include <stdio.h>
#include <stdlib.h>

void print_header(int size) {
	int i;
	for (i = 0; i < size; ++i) {
		if (i == 0) {
			printf("  ");
		}
		printf("%c ", (char) i + 97);
		if (i == size - 1) {
			printf("\n");
		}
	}
}

void print_matrix(char* matrix, int size) {
	int i, j;
	print_header(size);
	for (i = 0; i < size; ++i) {
		for (j = 0; j < size; ++j) {
			if (j == 0) {
				printf("%c ", (char) i + 97);
			}
			printf("%d ", matrix[i * size + j]);
		}
		printf("\n");
	}

}

void populate_matrix(char* matrix, int size) {
	int i, j;
	char input;
	for (i = 0; i < size; ++i) {
		for (j = 0; j < size; ++j) {
			do {
				printf("%c -> %c (y/n|1/0): ", (char) i + 97, (char) j + 97);
				input = (char) fgetc(stdin);
				fgetc(stdin);
			} while (!(input == 'y' || input == 'n' || input == '1' || input == '0'));
			matrix[i * size + j] = input == 'y' || input == '1' ? 1 : 0;
		}
	}
}

void get_nodes(int* nodes, int size, int count) {
	int nodei = 0, input;
	while (nodei < count) {
		do {
			printf("Select %d%s node (%c - %c): ", nodei + 1, nodei + 1 == 1 ? "st" : (nodei + 1 == 2 ? "nd" : "rd"),
				   (char) 97, (char) 97 + size - 1);
			input = (char) fgetc(stdin);
			fgetc(stdin);
		} while (!(input - 97 >= 0 && input - 97 < size));
		nodes[nodei++] = input;
	}
}

void find_path(char const* matrix, int size, int node, int target) {
	int i, curr;
	for (i = 0; i < size; ++i) {
		curr = matrix[(node - 97) * size + i];
		if (curr == 1 && (i + 97) != node) {
			if (i + 97 == target) {
				printf("Indirect path\n");
			} else {
				find_path(matrix, size, i + 97, target);
			}
		}
	}
}

int main() {
	int nodes[2];
	int n = 0;
	char buf[4];
	char* matrix;
	do {
		printf("Enter number of nodes: ");
		fgets(buf, 4, stdin);
		n = (int) strtol(buf, NULL, 10);
	} while (n == 0);

	printf("Nodes: %d \n", n);

	matrix = (char*) calloc(n * n, sizeof(char));

	populate_matrix(matrix, n);

	printf("\n");

	print_matrix(matrix, n);

	get_nodes(nodes, n, 2);

	printf("\nNode 1: %c\nNode 2: %c\n", nodes[0], nodes[1]);
	if (matrix[(nodes[0] - 97) * n + nodes[1] - 97] == 1) {
		printf("Direct path: %c -> %c\n", nodes[0], nodes[1]);
	} else if (matrix[(nodes[1] - 97) * n + nodes[0] - 97] == 1) {
		printf("Direct path: %c -> %c\n", nodes[1], nodes[0]);
	} else {
		find_path(matrix, n, nodes[1], nodes[0]);
	}
	printf("\n");
	return 0;
}
