import socket, threading

IP = '0.0.0.0'
PORT = 3301

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((IP, PORT))
s.listen(3)

def changer(s):
	while 1:
		print(s.recv(1024))

t1 = threading.Thread(target = changer, args = (s,))
print ("Waiting ")

try:
    conn, addr = s.accept()
    print("Connected")
    t1.start(conn)
    print("Thread Running")
    t1.join()

except KeyboardInterrupt:
	s.close()
	print (e)
