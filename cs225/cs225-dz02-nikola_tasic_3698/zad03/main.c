#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>

#define FIFO_FNAME "/tmp/cs225-fifo"

int main() {
	if (mkfifo(FIFO_FNAME, 0777 | O_NONBLOCK) == -1) {
		perror("Unable to create FIFO file");
		exit(EXIT_FAILURE);
	}

	pid_t writer_pid = fork();
	if (writer_pid != 0) {
		const char* message = "Message to be sent";
		int fd = open(FIFO_FNAME, O_WRONLY);
		if (fd == -1) {
			perror("Unable to open FIFO");
			return EXIT_FAILURE;
		}
		fprintf(stderr, "Writing '%s'\n", message);
		write(fd, message, strlen(message));
		return EXIT_SUCCESS;
	}

	pid_t reader_pid = fork();
	if (reader_pid != 0) {
		char buf[BUFSIZ] = {0};
		char* ptr = buf;
		int fd = open(FIFO_FNAME, O_RDONLY);
		if (fd == -1) {
			perror("Unable to open FIFO");
			return EXIT_FAILURE;
		}

		while (read(fd, ptr++, 1) > 0);
		fprintf(stderr, "Reading '%s'\n", buf);
		return EXIT_SUCCESS;
	}

	waitpid(writer_pid, NULL, 0);
	waitpid(reader_pid, NULL, 0);

	fprintf(stderr, "Removing %s\n", FIFO_FNAME);
	remove(FIFO_FNAME);
	return EXIT_SUCCESS;
}
