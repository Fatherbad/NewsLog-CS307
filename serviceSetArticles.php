<?php
	$link = mysqli_connect("localhost", "root", "", "test1");
	
	if ($link === false) {
		die("ERROR: Could not connect. " . mysqli_connect_error());
	}
	
	$url = $_POST['url'];
	$category = $_POST['category'];
	
	$sql = "INSERT INTO articles (url, category) VALUES ('$url', '$category')";
	
	if (mysqli_query($link, $sql)) {
		echo "Records added successfully.";
	}
	else {
		echo "ERROR: Could not execute $sql. " . mysqli_error($link);
	}
	
	mysqli_close($link);
?>