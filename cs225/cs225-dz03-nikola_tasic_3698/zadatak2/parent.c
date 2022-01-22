#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/shm.h>
#include <string.h>
int main()
{
	void *shared_memory;
	char buff[100];
	int shmid;
	shmid = shmget((key_t)2345, 2048, 0666 | IPC_CREAT);
	printf("Key of shared memory is %d\n", shmid);
	shared_memory = shmat(shmid, NULL, 0);
	printf("Process attached at %p\n", shared_memory);

	strcpy(buff, "Some data to be read");
	strcpy(shared_memory, buff);
}