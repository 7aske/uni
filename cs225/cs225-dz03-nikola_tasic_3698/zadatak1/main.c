#include<stdio.h>
#include<pthread.h>
#include<unistd.h>

void* function1();
void* function2();
void* function3();
void* function4();
pthread_mutex_t first_mutex;  //mutex lock
pthread_mutex_t second_mutex;

int main() {
	pthread_mutex_init(&first_mutex, NULL);  //initialize the lock
	pthread_mutex_init(&second_mutex, NULL);

	pthread_t thread1, thread2, thread3, thread4;

	pthread_create(&thread1, NULL, function1, NULL);  // create thread
	pthread_create(&thread2, NULL, function2, NULL);  // create thread
	pthread_create(&thread3, NULL, function3, NULL);  // create thread
	pthread_create(&thread4, NULL, function4, NULL);

	pthread_join(thread1, NULL);
	pthread_join(thread2, NULL);
	pthread_join(thread3, NULL);
	pthread_join(thread4, NULL);

	printf("Threads joined\n");
}

void* function1() {
	printf("Thread ONE started\n");
	sleep(1);
	printf("Thread ONE finished\n");
	return NULL;
}

void* function2() {
	pthread_mutex_lock(&second_mutex);
	printf("Thread TWO acquired second_mutex\n");
	sleep(1);
	pthread_mutex_lock(&first_mutex);
	printf("Thread TWO acquired first_mutex\n");
	pthread_mutex_unlock(&first_mutex);
	printf("Thread TWO released first_mutex\n");
	pthread_mutex_unlock(&second_mutex);
	printf("Thread TWO released second_mutex\n");
	return NULL;
}

void* function3() {
	printf("Thread THREE started\n");
	sleep(1);
	printf("Thread THREE finished\n");
	return NULL;
}

void* function4() {
	pthread_mutex_lock(&first_mutex);  // to acquire the resource/mutex lock
	printf("Thread ONE acquired first_mutex\n");
	sleep(1);
	pthread_mutex_lock(&second_mutex);
	printf("Thread ONE acquired second_mutex\n");
	pthread_mutex_unlock(&second_mutex); // to release the resource
	printf("Thread ONE released second_mutex\n");
	pthread_mutex_unlock(&first_mutex);
	printf("Thread ONE released first_mutex\n");
	return NULL;
}
