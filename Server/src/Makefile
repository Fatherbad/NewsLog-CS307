all: server

server: Server.java User.java UserManager.java 
	javac -cp ".:../../jar/*" -d "../bin" Server.java
	java -cp "../../jar/*:../bin" Server

