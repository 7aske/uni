#include <netinet/in.h>
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/select.h>
#include <sys/types.h>
#include <unistd.h>

#define BUF_S 1024
#define ADDR_S 32
#define NAME_S 16

// maximum number of clients
#define C_MAX 5

struct socket_data {
    int* active_sockets;
    int socket;
};

void panic(char*);
void* handler(void*);
int start_server(int, struct sockaddr_in*);
int find_free_fd(int*);
void close_fd(int, int*);

int main(int argc, char* argv[])
{
    int sock, client_sock, client_len, n, port, pid;
    struct sockaddr_in server;
    struct sockaddr_in client;
    char buf[BUF_S];
    char addr[ADDR_S];

    char name[NAME_S];

    pthread_t thread;
    pthread_t t_pool[C_MAX];

    int* active_socks;
    int current_sock;

    struct socket_data* s_data;

    if (argc < 2) {
        printf("ERROR usage: <port>");
        exit(0);
    }

    // clear memory locations
    bzero(&server, sizeof server);
    bzero(buf, BUF_S);
    bzero(t_pool, C_MAX);

    port = atoi(argv[1]);
    current_sock = 0;

    server.sin_family = AF_INET;
    server.sin_addr.s_addr = INADDR_ANY;
    server.sin_port = htons(port);

    // allocate memory to store active socket file descriptors
    active_socks = calloc(C_MAX, sizeof(int));

    // create server socket
    sock = socket(AF_INET, SOCK_STREAM, 0);
    if (sock < 0) {
        panic("ERROR failed to create socket");
    }
    printf("SUCCESS socket created\n");

    // start server by binding the socket
    if (start_server(sock, &server) < 0) {
        panic("ERROR unable to start server");
    }
    printf("INFO server started on port %d\n", port);

    // request listening loop
    while (1) {

        //listen for connections
        client_len = sizeof client;
        client_sock = accept(sock, (struct sockaddr*)&client, (socklen_t*)&client_len);
        if (client_sock < 0) {
            panic("ERROR failed to create client socket");
        }
        // parse client ip
        inet_ntop(AF_INET, &(client.sin_addr), addr, BUF_S);
        printf("INFO client connected ip: %s sock_fd: %d\n", addr, client_sock);

        // get the index of a free spot in active_sockets array
        // if the result is -1 that means that there are more
        // available connections
        current_sock = find_free_fd(active_socks);
        if (current_sock == -1) {
            strcpy(buf, "Server full :(");
            send(client_sock, buf, strlen(buf), 0);
            close(client_sock);
        } else {
            // allocate memory to store socket specific data
            // needed to repspond to all connected clients
            s_data = malloc(sizeof(struct socket_data));
            memset(s_data, 0, sizeof s_data);

            // set the current client socket to array
            active_socks[current_sock] = client_sock;
            // set the pointer to that array to be
            // passed to the handler thread
            s_data->socket = client_sock;
            s_data->active_sockets = active_socks;
            current_sock++;

            // print file descriptors and memory locations for debugging
            printf("%x afd: %d\n", &(s_data->socket), s_data->socket);
            for (int i = 0; i < C_MAX; i++) {
                printf("%x  fd: %d\n", &(s_data->active_sockets[i]), s_data->active_sockets[i]);
            }

            // create thread with the pointer to socket data
            // that is stored in s_data
            n = pthread_create(&thread, NULL, handler, s_data);
            if (n < 0) {
                printf("ERROR failed creating thread %d ", n);
            }
        }
    }

    // close sockets and kill thread
    close(client_sock);
    close(sock);
    pthread_join(thread, NULL);
    free(active_socks);
    printf("exited\n");
}

// handler func called on start_routine()
void* handler(void* sockets)
{
    struct socket_data* s_data;
    int n;
    char buf[BUF_S];
    memset(buf, 0, BUF_S);

    // cast thread arg pointer to socket_data struct
    s_data = (struct socket_data*)sockets;

    // listen for messages on the socket passed to the thread
    printf("INFO starting new thread for fd %d\n", s_data->socket);
    while ((n = recv(s_data->socket, buf, BUF_S, 0)) > 0) {
        printf("INFO %s\n", buf);

        // on sent message forward the message
        // to all active clients except to self
        for (int i = 0; i < C_MAX; i++) {
            if (s_data->active_sockets[i] != s_data->socket) {
                send(s_data->active_sockets[i], buf, strlen(buf), 0);
                bzero(buf, BUF_S);
            }
        }
    }

    // on socket disconnects clear memory and free array spots
    if (n == 0) {
        printf("INFO client disconnected - %d\n", s_data->socket);
        free(sockets);
        close_fd(s_data->socket, s_data->active_sockets);
        return;
    } else if (n == -1) {
        printf("INFO client disconnected - %d\n", s_data->socket);
        free(sockets);
        close_fd(s_data->socket, s_data->active_sockets);
        return;
    }
}

// close file descriptor and refresh active socket
// array to allow for more clients
void close_fd(int fd, int* active_fds)
{
    for (int i = 0; i < C_MAX; active_fds++, i++) {
        if (fd == *active_fds) {
            printf("closing fd %d\n", fd);
            close(fd);
            *active_fds = 0;
        }
    }
}

// get the index of a free socket
int find_free_fd(int* active_fds)
{
    for (int i = 0; i < C_MAX; active_fds++, i++) {
        if (0 == *active_fds) {
            return i;
        }
    }
    return -1;
}

// initialize server by binding server socket to the address
int start_server(int sock, struct sockaddr_in* address)
{
    if (bind(sock, (struct sockaddr*)address, sizeof *address) < 0) {
        // panic("ERROR binding socket");
        return -1;
    }
    printf("SUCCESS socket bound\n");
    return listen(sock, C_MAX);
}

// print error and exit
void panic(char* msg)
{
    perror(msg);
    exit(0);
}