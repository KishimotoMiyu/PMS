<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.* , java.util.ArrayList, model.Info , java.util.List" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>従業員情報管理システム</title>
	<link rel="stylesheet" href="css/generalList.css">
	<% List <Info> info = (List)request.getAttribute("info"); %>
	</head>
	<body>
		<header>
			<div class="header_container">
				<h1>Hoge株式会社</h1>
				<nav>
					<ul>
						<%for(Info columns  : info) { %>
						<li class="loginUser"><%= columns.getName() %><span id="span">さんログイン中</span></li>
						<li><a href="passChange">Password変更</a></li>
						<li><a href="logout">Logout</a></li>
					</ul>
				</nav>
			</div>
		</header>
		<main>
			<div class="wrapper">
				<div class="contents">
					<p>氏名</p>
					 <p class="text"><%= columns.getName() %></p><br>
					 <p>所属</p>
					 <p class="text"><%= columns.getAffiliation() %></p><br>
					 <p>役職</p>
					 <p class="text"><%= columns.getPost() %></p><br>
					 <p>職務内容</p>
					 <p class="text"><%= columns.getJob() %></p><br>
					 <p>生年月日</p>
					 <p class="text"><%= columns.getBirthday() %></p><br>
					 <p>性別</p>
					 <p class="text"><%= columns.getGender() %></p><br>
					 <% } %>
					 
					 <p><a class="update" href="update?updateuserNo=${userNo}">情報を更新する</a></p>
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