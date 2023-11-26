<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>従業員管理システム</title>
	<link rel="stylesheet" href="css/login.css">
	</head>
	<body>
		<main>
			<div class="contents_wrapper">
				<div class="form_wrapper">
					<p>${message}</p>
					<form action="login" method="post">
						<input class="text" type ="text" name="userId" placeholder="UserID"><br>
						<input class="text" type ="password" name="password" placeholder="Password"><br>
						<input class="submit" type ="submit" value="Login">
					</form>
				</div>
			</div>
		</main>
	</body>
</html>