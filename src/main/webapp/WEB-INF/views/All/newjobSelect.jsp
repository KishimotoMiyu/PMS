<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="java.util.* , java.util.ArrayList, model.Info , java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録画面画面</title>
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
				 <form action="add" method="post">
				     <input type="hidden" name="name" value= "${name}">
				     <input type="hidden" name="userId" value= "${userId}">
				     <input type="hidden" name="password" value= "${password}">
				     <input type="hidden" name="userNo" value= "${userNo}">
				     <input type="hidden" name="post" value= "${post}">
				     <input type="hidden" name="birthday" value= "${birthday}">
				     <input type="hidden" name="gender" value= "${gender}">
				     <input type="hidden" name="affiliation" value= "${affiliation}">
				 	 <select name="job">
				 	   ${select}
				 	</select>
				 	<input type="submit" value="追加する">
				 </form>
			</div>
		</div>
	<main>
<footer>
		<p>
			<small>
			   &copy; Hoge株式会社
			</small>
		</p>
</footer>
  
</body>
</html>
