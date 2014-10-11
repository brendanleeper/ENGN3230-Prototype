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
		
		$cmd = '"C:\Program Files (x86)\Java\jre7\bin\keytool.exe"';
		$cmd .=	" -genkey -noprompt -alias $username -keyalg RSA -keysize 2048 -validity 365 -keystore";
		$cmd .= ' "E:/Dropbox/git/ENGN3230 Prototype/keystore.jks" -dname "CN=Brendan Leeper, EMAILADDRESS=brendan.leeper@gmail.com, OU=ENGN3230 Prototype, O=ANU ENGN3230 2014, L=Canberra, S=ACT, C=AU" -storepass security -keypass security 2>&1';
		
		exec($cmd, $output, $return_val);
		echo "return code: $return_val\n";
		foreach($output as $line) echo "$line\n";
		
		// insert into the db
		$query = $dbh->query("INSERT INTO users (username, hash) VALUES ('$username', '$hash')");
		if($query == FALSE) {
			echo 'query is false';
		}
		echo '<br>Registration complete';
	}
?>