#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
	if (fork() == 0)  { // child 1
		printf("Child 1\n");
		return 0;
	}

	pid_t pid = fork();
	if (pid == 0) { // child 2
		printf("C Child 2:  pid  %d\n", getpid());
		printf("D Child 2:  ppid %d\n", getppid());
	} else { // parent
		printf("A Parent:   cpid %d\n", pid);
		wait(NULL);
		printf("B Parent:   pid  %d\n", getpid());
	}

	return 0;
}
