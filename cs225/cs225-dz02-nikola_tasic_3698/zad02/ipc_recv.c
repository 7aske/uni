#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>

#include "msg.h"

int main() {
	int running = 1;
	msg_t data;

	int msgid = msgget((key_t) 12345, 0666 | IPC_CREAT);
	if (msgid == -1) {
		perror("Unable to create queue");
		exit(EXIT_FAILURE);
	}

	while (running) {
		msgrcv(msgid, (void*) &data, MSG_BUFSIZ, 0, 0);
		if (data.type == MSG_TYPE_EXIT) {
			running = 0;
		} else if (data.type == MSG_TYPE_TEXT) {
			printf("%s", data.buf);
		}
	}
}
