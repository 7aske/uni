import json
import socket


class Complex(object):
	r = 0
	i = 0

	def __init__(self, r, i):
		self.r = r
		self.i = i

	def __add__(self, other):
		self.r = self.r + other.r
		self.i = self.i + other.i
		return self

	def __sub__(self, other):
		self.r = self.r - other.r
		self.i = self.i - other.i
		return self

	def __repr__(self):
		return f"{self.r} + {self.i}i"

	def __mul__(self, other):
		pass

	def __divmod__(self, other):
		pass


sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
addr = ('localhost', 54321)
sock.connect(addr)

res = sock.recv(1024)
res = res.decode("utf8")
print(res)
res = res.replace('\'', '"', -1)
data = json.loads(res)

nums = []
sumc = Complex(0, 0)

for d in data["data"]:
	nums.append(Complex(d["r"], d["i"]))

for n in nums:
	sumc = sumc + n

print(sumc)
