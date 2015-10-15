<?php
	$link = mysqli_connect("localhost", "root", "", "test1");
	
	if ($link === false) {
		die("ERROR: Could not connect. " . mysqli_connect_error());
	}
	
	$username = $_POST['username'];
	$password = $_POST['password'];
	
	$salt = strtr(base64_encode(mcrypt_create_iv(16, MCRYPT_DEV_URANDOM)), '+', '.');
	$salt = sprintf("$2a$%02d$", 10) . $salt;
	
	$password = crypt($password, $salt);
	
	$sql = "INSERT INTO users (username, password) VALUES ('$username', '$password')";
	
	if (mysqli_query($link, $sql)) {
		echo "Records added successfully.";
	}
	else {
		echo "ERROR: Could not execute $sql. " . mysqli_error($link);
	}
	
	mysqli_close($link);
?>