<?php
	$dir = 'sqlite:E:/Dropbox/git/ENGN3230 Prototype/prototype.db';
	$dbh = new PDO($dir) or die("cannot open db");
	
	$username = $_POST['user'];
	$password = $_POST['pass'];
	
	$query = $dbh->query("SELECT COUNT(username) as count FROM users WHERE username='$username'");
	if($query == FALSE) {
		die(var_export($dbh->errorinfo(), TRUE));
	}

	$result = $query->fetch(PDO::FETCH_ASSOC);
	
	$count = $result['count'];
	
	if($count>0) {
		echo 'Username already exists';
	} else {
		// use hashed username as salt
		$hash = crypt($password, crypt($username));
		print_r(crypt($username));
		print_r($hash);
		
		// insert into the db
		$query = $dbh->query("INSERT INTO users (username, hash) VALUES ('$username', '$hash')");
		if($query == FALSE) {
			echo 'query is false';
		}
		echo 'Registration complete';
	}
?>