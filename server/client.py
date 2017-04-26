import socket

IP = "139.59.79.157"
#IP = "0.0.0.0"
PORT = 3301
BUFF = 50

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((IP, PORT))
print "Connected!"
try:
	while True:
		data = raw_input("Enter data to send!")
		s.send(data)

except KeyboardInterrupt:
	s.close()

