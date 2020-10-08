#include <stdio.h>
#include <stdlib.h>

#include "../structs/queue.h"
#include "../structs/stack.h"

/*13. Zadate su funkcije za stavljanje i skidanje elemenata sa stek i za stavljanje i skidanje elemenata
iz reda (realizovanih listom):
		int dodajNaStek (int element, atom **glava);
		int skiniSaSteka (int *element, atom **glava);
		int dodajURed (int element, atom **glava, atom **rep);
		int skiniIzReda (int *element, atom **glava, atom **rep);
ili
		public int dodajNaStek (int element, Stek glava);
		public int skiniSaSteka (int element, Stek glava);
		public int dodajURed (int element, Stek glava, Stek rep)prebaci;
		public int skiniIzReda (int element, Stek glava, Stek rep);
Implementirati gore navedene metode.
Napišite rekurzivnu funkciju, koristeći gore navedene funkcije, koja prebacuje elemente sa
steka u red, prototipa:
		void prebaci (atom **glavaSteka, atom **glavaReda, atom **repReda); ili
		public void prebaci (Stek glavaSteka, Red glavaReda, Red repReda)
Početni stek mora ostati očuvan. Redosled elemenata u redu isti je kao na steku (onaj element
koji prvi izlazi sa steka je prvi element koji izlazi i iz reda). Red je na početku prazan.*/

void transfer(void** stackhead, void** queuehead) {
	static queue_t* helper = NULL;
	if (helper == NULL) {
		helper = queue_new((*(stack_t**) stackhead)->size);
	}

	void* elem = stack_pop(*(stack_t**) stackhead);
	queue_enqueue(helper, elem);

	if (!stack_isempty(*(stack_t**) stackhead)) {
		transfer(stackhead, queuehead);
		stack_push(*(stack_t**) stackhead, elem);
	} else {
		stack_push(*(stack_t**) stackhead, elem);
		while (!queue_isempty(helper)) {
			void* elem2 = queue_dequeue(helper);
			queue_enqueue(*(queue_t**) queuehead, elem2);
			free(elem2);
		}
		queue_destroy(helper);
	}
	free(elem);
}

int main() {
	stack_t* stack = stack_new(sizeof(int));
	queue_t* queue = queue_new(sizeof(int));

	stack_push(stack, _ptr(10, int));
	stack_push(stack, _ptr(20, int));
	stack_push(stack, _ptr(30, int));
	stack_push(stack, _ptr(40, int));
	transfer((void**) &stack, (void**) &queue);

	printf("Initial stack:\n");
	while (!stack_isempty(stack)) {
		void* elem = stack_pop(stack);
		printf("%d\n", *(int*) elem);
		free(elem);
	}

	printf("Resulting queue:\n");
	while (!queue_isempty(queue)) {
		void* elem = queue_dequeue(queue);
		printf("%d\n", *(int*) elem);
		free(elem);
	}

	stack_destroy(stack);
	queue_destroy(queue);
	return 0;
}