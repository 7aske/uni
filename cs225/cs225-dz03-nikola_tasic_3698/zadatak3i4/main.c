#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>

#define BUF_SIZE 64

int main() {
	int inp_fd = open("./input.txt", O_RDONLY);
	int out_fd = open("./output.txt", O_WRONLY | O_CREAT);

	char buf[BUF_SIZE];
	size_t n_read;
	size_t total_read = 0;
	while ((n_read = read(inp_fd, buf, BUF_SIZE))) {
		total_read += n_read;
		write(out_fd, buf, n_read);
	}

	// Ovo je ujedno i resenje zadataka broj 4
	printf("Total read: %ld\n", total_read);

	close(inp_fd);
	close(out_fd);

	return 0;
}
