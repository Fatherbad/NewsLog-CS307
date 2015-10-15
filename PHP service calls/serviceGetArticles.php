<?php
	$link = mysqli_connect("localhost", "root", "", "test1");
	
	if ($link === false) {
		die("ERROR: Could not connect. " . mysqli_connect_error());
	}
	
	$category = $_POST['category'];
	
	$sql = "SELECT url FROM articles WHERE category = '$category'";
	
	$article_details = mysqli_query($link, $sql);
	
	if ($article_details) {
		$rows = array();
		while($r = mysqli_fetch_assoc($article_details)) {
			$rows[] = $r;
		}
		
		echo json_encode($rows);
	}
	else {
		echo "ERROR: Could not execute $sql. " . mysqli_error($link);
	}
	
	mysqli_close($link);
?>