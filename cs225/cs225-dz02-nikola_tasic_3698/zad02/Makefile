recv: ipc_recv.c msg.h
	gcc -O2 ipc_recv.c -o ipc_recv && ./ipc_recv

send: ipc_send.c msg.h
	gcc -O2 ipc_send.c -o ipc_send && ./ipc_send

clean:
	rm ipc_recv
	rm ipc_send
