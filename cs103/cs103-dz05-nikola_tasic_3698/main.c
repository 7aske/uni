#include <stdio.h>
#include "../structs/linkedlist.h"

// 3. Napisati funkciju:
// 		a. kojom se od neprazne jednostruko povezane liste L formira kružna lista
// 		b. kojom se ispisuje sadržaj neprazne kružne liste L.


// llist_t je implementacija duple linked liste ali za potrebe ovog zadatka
// ignorisacemo da postoji tail pointer i node->prev pointer
void clist_conv(node_t* head) {
	node_t* first = head;
	node_t* last = head;

	while (last->next != NULL) {
		last = last->next;
	}

	if (last != NULL && first != NULL)
		last->next = first;

}

void clist_print(node_t* head) {
	node_t* current = head;
	if (head != NULL) {
		printf("%p [ ", head);
		do {
			printf("%d ", *(int*) current->data);
			current = current->next;
		} while (current != head);
		printf("]\n");
	}
}

int main() {
	llist_t* intlist1 = llist_new(sizeof(int));

	// makro _ptr vraca pointer na struct literal
	// jer je add metoda zahteva pointer i ne prima literale
	// &(int){10}
	llist_add_back(intlist1, _ptr(10, int));
	llist_add_back(intlist1, _ptr(20, int));
	llist_add_back(intlist1, _ptr(30, int));
	llist_add_back(intlist1, _ptr(40, int));

	llist_t* intlist2 = llist_copy(intlist1);

	node_t* head1 = intlist1->head;
	node_t* head2 = intlist2->head->next;

	clist_conv(head1);
	clist_conv(head2);
	clist_print(head1);
	clist_print(head2);
	return 0;
}