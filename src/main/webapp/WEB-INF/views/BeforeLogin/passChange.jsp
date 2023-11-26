<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>パスワード変更</title>
	<link rel="stylesheet" href="css/login.css">
	</head>
	<body>
		<main>
			<div class="contents_wrapper">
				<div class="form_wrapper">
					<p>${message}</p>
					<form action="passChange" method="post">
					    <input type="hidden" name="userNo" value="${userNo}">
					    <input type="hidden" name="passchange" value="${passchange}">
						<input class="text" type="password" name="password">
						<input class="submit" type="submit" value="パスワードを変更する">
					</form>
				</div>
			</div>
		</main>
	</body>
</html>