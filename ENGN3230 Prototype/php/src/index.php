<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Cp1252">
<title>Insert title here</title>
</head>
<body>
	<form action="login.php" method="POST">
	<p>Username</p><input type ="text" name="user"/>
	<p>password</p><input type="password" name="pass"/>
	<br />
	<input type="submit" value="login"/>
	</form>
	<br>
	<form action="register.php" method="POST">
	<p>Username</p><input type ="text" name="user"/>
	<p>password</p><input type="password" name="pass"/>
	<br />
	<input type="submit" value="register"/>
	</form>
	<br>
	<?php phpinfo(); ?>
</body>
</html>