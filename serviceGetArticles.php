<?php
	# Get articles from sources and insert into `articles` table in database
	
	# Ensure that the client has provided a value for `url`
	if (isset($_POST["urlToSearch"]) && $_POST["urlToSearch"] != "") {
		# Setup variables
		$url = $_POST["urlToSearch"];
		
		# Connect to Database
		$con = mysqli_connect("localhost", "root", "", "test1");
		
		# Check connection
		if (mysqli_connect_errno()) {
			echo 'Database connection error: ' . mysqli_connect_error();
			exit();
		}
		
		# Query the database to get article details
		$articleDetails = mysqli_query($con, "SELECT * FROM articles WHERE url = '$url'");
		
		# If no data was returned, check for any SQL errors
		if (!$articleDetails) {
			echo 'Could not run query: ' . mysqli_error($con);
			exit;
		}
		
		# Get the first row of the results
		$row = mysqli_fetch_row($articleDetails);
		
		# Build the result array (Assign keys to the values)
		$result_data = array(
			'url' => $row[1],
			'cat_id' => $row[2]
		);
		
		# Output the JSON data
		echo json_encode($result_data);
	}
	else {
		echo "Could not complete query. Missing parameter.";
	}
?>