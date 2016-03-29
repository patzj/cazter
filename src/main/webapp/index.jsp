<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Welcome to OpenShift</title>
</head>
<body>
	<header>
		<h1>api.cazter.org</h1>
	</header>
	
	<main>
		<%
			out.println(System.getenv("OPENSHIFT_MYSQL_DB_URL"));
			out.println(System.getenv("OPENSHIFT_APP_DNS"));
			out.println(System.getenv("OPENSHIFT_GEAR_DNS"));
		%>
	</main>
	
	<footer>
		
	</footer>
</body>
</html>
