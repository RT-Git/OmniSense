import socket, threading

#IP = "139.59.79.157"
IP = "0.0.0.0"
PORT = 3301
BUFF = 50

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((IP, PORT))
print "Connected!"

def changer(s):
	while 1:
		print(s.recv(1024))

t1 = threading.Thread(target = changer, args = (s,))

try:
	t1.start()
	while True:
		data = raw_input(">")
		s.send(data)
		# print(s.recv(1024))
	t1.join()
except KeyboardInterrupt:
	s.close()
