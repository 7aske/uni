import json
import socket

HOST = '127.0.0.1'  # Standard loopback interface address (localhost)
PORT = 54321  # Port to listen on (non-privileged ports are > 1023)

data = {}
with open("data.json", "r") as file:
	data = json.loads(file.read())
	print(data)

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
	s.bind((HOST, PORT))
	s.listen(0)
	while True:
		conn, addr = s.accept()
		with conn:
			conn.sendall(bytes(str(data), encoding="utf8"))
