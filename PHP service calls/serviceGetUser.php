<?php
	$link = mysqli_connect("localhost", "root", "", "test1");
	
	if ($link === false) {
		die("ERROR: Could not connect. " . mysqli_connect_error());
	}
	
	$username = $_POST['username'];
	$password = $_POST['password'];
	
	$dbh = new PDO('mysql:host=localhost; dbname=test1; charset=latin1', 'root', '');
	
	$sth = $dbh->prepare('
		SELECT username, password
		FROM users
		WHERE username = :username
		LIMIT 1
	');
	
	$sth->bindParam(':username', $username);
	
	$sth->execute();
	
	$user = $sth->fetch(PDO::FETCH_OBJ);
	
	if ($user->password == crypt($password, $user->password)) {
		$row = array();
		$row[] = $user->username;
		echo json_encode($row);
	}
	else {
		echo "Access denied";
	}
?>