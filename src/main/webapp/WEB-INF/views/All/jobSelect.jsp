<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>更新画面</title>
	<link rel="stylesheet" href="css/job.css">
	</head>
	<body>
		<header>
			<div class="header_container">
				<h1>Hoge株式会社</h1>
				<p class="loginUser">${userName}<span id="span">さんログイン中</span></p>
			</div>
		</header>
		<main>
			<div class="wrapper">
				<div class="contents">		
				<p>職務内容を選んでください</p>
				 <form action="update" method="post">
					     <input type="hidden" name="name" value="${name}">
					     <input type="hidden" name="userNo" value= "${userNo}">
					     <input type="hidden" name="post" value= "${post}">
					     <input type="hidden" name="birthday" value= "${birthday}">
					     <input type="hidden" name="gender" value= "${gender}">
					     <input type="hidden" name="affiliation" value= "${affiliation}">
					 	 <select class="select" name="job">
					 	   ${select}
					 	</select><br>
					 	<input class="submit" type="submit" value="更新する">
				 </form>
				</div>	
			</div>
		</main>
		<footer>
			<p>
				<small>
				   &copy; Hoge株式会社
				</small>
			</p>
		</footer>
	</body>
</html>
