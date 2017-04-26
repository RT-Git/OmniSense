import socket

IP = '0.0.0.0'
PORT = 3301
data = "masala"
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((IP, PORT))
s.listen(3)
print ("Waiting ")

while True:
	conn,addr = s.accept()
	print("Success")
	conn.send(bytes(data))
	conn.close()
