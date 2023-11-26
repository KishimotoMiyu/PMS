<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.* , java.util.ArrayList, model.Info , java.util.List" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>更新画面</title>
		<link rel="stylesheet" href="css/form.css">
		<% List <Info> info = (List)request.getAttribute("info"); %>
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
				 <form action="jobSelect" method="post">
				 	<p>${message}</p>
				 	<%for(Info columns  : info) { %>
				    <input type="hidden" name="updateUserPost" value="<%= columns.getPost() %>"> 
				    <input type="hidden" name="updateUserAffiliation" value="<%= columns.getAffiliation() %>"> 
				    <input type="hidden" name="updateUserNo" value="<%= columns.getUserNo() %>"> 
				    <label>氏名</label><br>
				 	<input class="text"  type="text" name="name" value="<%= columns.getName() %>"><br><br>
					 <label>所属</label><br>
				 	<input type="radio" name="affiliation" value="経理課" checked>経理課
				 	<input type="radio" name="affiliation" value="営業課">営業課
				 	<input type="radio" name="affiliation" value="人事課">人事課<br><br>
				 	<label>性別</label><br>
				 	<input type="radio" name="gender" value="男性" checked>男性
				 	<input type="radio" name="gender" value="女性">女性<br><br>
				 	<label>役職</label><br>
				 	<input type="radio" name="post" value="課長" checked>課長
				 	<input type="radio" name="post" value="係長">係長
				 	<input type="radio" name="post" value="主任">主任
				 	<input type="radio" name="post" value="役職なし">役職なし<br><br>
				 	<label>生年月日</label><br>
				 	<input class="text" type="date" name="birthday" value="<%= columns.getBirthday() %>"><br><br>
				 	<input class="submit" type="submit" value="次へ"><br>
				 	 <% 
				   
				    String Post = (String)session.getAttribute("userPost");  
				    String back = "";
				   
					if(Post.equals("課長") || Post.equals("係長") ) { //役職ごとの画面遷移
						back = "allList";
					}else if(Post.equals("主任") || Post == null ) {
						back = "generalList";
					}
					
					%>
		 			 <p><a class="back" href="<%= back %>">戻る</a></p>
				 </form>
				 
		  <% } %>
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