all: server

server: Server.java User.java UserManager.java 
	javac -cp ".:../bin/jar/*" -d "../bin" Server.java
	java -cp ../bin Server

