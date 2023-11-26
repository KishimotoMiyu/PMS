<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ page import="java.util.* , java.util.ArrayList, model.Info , java.util.List" %>
<!DOCTYPE html>
	<html>
	<head>
	<meta charset="UTF-8">
	<title>従業員情報管理システム</title>
	<link rel="stylesheet" href="css/allList.css">
	<% List <Info> info = (List)request.getAttribute("info"); %>
	</head>
	<body>
	<header>
		<div class="header_container">
			<h1>Hoge株式会社</h1>
			<nav>
				<ul>
					<li class="loginUser">${userName}<span id="span">さんログイン中</span></li>
					<li><a href="passChange">Password変更</a></li>
					<li><a href="add">従業員新規登録</a></li>
					<li><a href="logout">Logout</a></li>
				</ul>
			</nav>
		</div>
	</header>
	<main>
		<div class="wrapper">
			<h1>全員の情報一覧表</h1>
			<p class="message">${message}</p>
			<div class="table_container">
				<table>
					<tr>
						<th>氏名</th>
						<th>所属</th>
						<th>役職</th>
						<th>職務内容</th>
						<th>誕生日</th>
						<th>性別</th>
						<th></th>
						<th></th>
					</tr>
				
				 <%for(Info columns  : info) { %>
				 <tr>
					 <td><p><%= columns.getName() %></p></td>
					 <td><p><%= columns.getAffiliation() %></p></td>
					 <td> <p><%= columns.getPost() %></p></td>
					<td> <p><%= columns.getJob() %></p></td>
					<td> <p><%= columns.getBirthday() %></p></td>
					<td>  <p><%= columns.getGender() %></p></td>
					<td><button><a class="update" href='update?updateuserNo=<%= columns.getUserNo() %>'>編集</a></button></td>
					︎<td><button><a class="delete" href='delete?deleteUserNo=<%= columns.getUserNo() %>'>削除</a></button></td>
				 <tr>
				 <% } %>
				 </table>
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