CC=gcc
CLIENT_SRC=source/client/
CLIENT_OUT=build/
CLIENT_FN=client
SERVER_SRC=source/server/
SERVER_OUT=build/
SERVER_FN=server

server: $(SERVER_SRC)main.c
	$(CC) $(SERVER_SRC)main.c -o $(SERVER_OUT)$(SERVER_FN) -pthread
client: $(CLIENT_SRC)main.c
	$(CC) $(CLIENT_SRC)main.c -o $(CLIENT_OUT)$(CLIENT_FN) -pthread
run-server:
	$(SERVER_OUT)$(SERVER_FN) 8080
run-client:
	$(CLIENT_OUT)$(CLIENT_FN) 127.0.0.1 8080
