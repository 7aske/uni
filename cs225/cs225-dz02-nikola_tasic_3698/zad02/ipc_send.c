#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>

#include "msg.h"

int main() {
	int running = 1;
	msg_t data;
	int msgid = msgget((key_t)12345, 0666 | IPC_CREAT);
	if (msgid == -1) {
		perror("Unable to create queue");
		exit(EXIT_FAILURE);
	}

	while (running) {
		printf("> ");
		fgets(data.buf, MSG_BUFSIZ, stdin);

		if (strncmp(data.buf, "exit", 4) == 0) {
			running = 0;
			data.type = MSG_TYPE_EXIT;
		} else {
			data.type = MSG_TYPE_TEXT;
		}

		if (msgsnd(msgid, (void*) &data, MSG_BUFSIZ, 0) == - 1) {
			perror("Message not sent");
		}
	}
}
