<?php
	$dir = 'sqlite:E:/Dropbox/git/ENGN3230 Prototype/prototype.db';
	$dbh = new PDO($dir) or die("cannot open db");
	
	$username = $_POST['user'];
	$pass = $_POST['pass'];

	$query = $dbh->query("SELECT username, hash FROM users WHERE username='$username'");
	if($query == FALSE) {
		die(var_export($dbh->errorinfo(), TRUE));
	}

	$result = $query->fetch(PDO::FETCH_ASSOC);
	//print_r($result);
	
	$hash = $result['hash'];
	$salt = md5($username);
	
	if(crypt($pass, $salt) == $hash) {
		echo 'login successful';
	} else {
		echo 'login failed';
	}
?>