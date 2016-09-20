<html>
<head>
<link rel="stylesheet" href="../css/wro.css"/>
</head>
<body>
	<div class="container">
		<h2>Please Confirm</h2>
 
		<form id="confirmationForm" name="confirmationForm"
			action="../oauth/authorize" method="post">
			<input name="user_oauth_approval" value="true" type="hidden" />
			<input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<button class="btn btn-primary" type="submit">Approve</button>
		</form>
		<form id="denyForm" name="confirmationForm"
			action="../oauth/authorize" method="post">
			<input name="user_oauth_approval" value="false" type="hidden" />
			<input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<button class="btn btn-primary" type="submit">Deny</button>
		</form>
	</div>
	<script src="../js/wro.js"	type="text/javascript"></script>
</body>
</html>