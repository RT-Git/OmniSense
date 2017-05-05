import socket, threading

IP = '0.0.0.0'
PORT = 3301

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((IP, PORT))
s.listen(3)

def changer(conn1, conn2):
	lck = threading.Lock()
	while 1:
		try:
			with lck:
				masala = conn1.recv(1024)
				if masala:
					conn2.send(masala)
				#print("some done")
		except:
			print("Threads Killed")
			break

print ("Waiting ")
try:
	while True:
		conn_raspi, addr = s.accept()
		print("Raspi Connecetd")
		conn_phone, addr = s.accept()
		print("Phone Connecetd")

		t1 = threading.Thread(target = changer, args = (conn_raspi, conn_phone))
		t2 = threading.Thread(target = changer, args = (conn_phone, conn_raspi))

		t1.start()
		t2.start()
		t1.join()
		t2.join()

except KeyboardInterrupt:
	s.close()
	print (e)
