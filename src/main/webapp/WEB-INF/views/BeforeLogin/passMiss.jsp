<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>アカウントロック解除</title>
	<link rel="stylesheet" href="css/login.css"
	</head>
	<body>
		<main>
			<div class="contents_wrapper">
				<div class="form_wrapper">
					<p>${message}</p>
					<form action="unlock" method="post">
						<input class="text" type="text" name="userId" placeholder="UserID">
						<input class="text" type="text" name="affiliation" placeholder="⚪︎⚪︎課︎">
						<input class="text" type="number" name="birthday" placeholder="生年月日　記入例 : 20230101">
						<input class="submit" type="submit" value="次へ">
					</form>
				</div>
			</div>
		</main>
	</body>
</html>