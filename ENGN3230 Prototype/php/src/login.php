<?php
	$dir = 'sqlite:E:/Dropbox/git/ENGN3230 Prototype/engnprototype.db';
	$dbh = new PDO($dir) or die("cannot open db");

	$username = stripslashes($_POST['user']);
	$pass = stripslashes($_POST['pass']);
	
	$query = "SELECT count(username) as 'count' FROM users WHERE username='$username' AND password='$pass'";
	$result = $dbh->query($query);
	
	if($result->fetchColumn()==1) {
		echo 'login successful';
	} else {
		echo 'login failed';
	}
?>