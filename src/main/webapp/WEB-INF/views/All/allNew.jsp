<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>新規登録</title>
	<link rel="stylesheet" href="css/new.css">
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
			 <form action="newjobSelect" method="post">
				 <p>${message}</p>
				  <label>氏名</label><br>
			 	 <input typa="text" name="name" placeholder="ユーザーネーム"><br>
			 	  <label>ユーザーID</label><br>
			 	 <input typa="text" name="userId" placeholder="userID"> <br>
			 	  <label>パスワード</label><br>
			 	 <input typa="password" name="password" placeholder="password"><br>
			  　　<label>所属</label><br>
			 	 <input type="radio" name="affiliation" value="経理課" checked>経理課
			   	<input type="radio" name="affiliation" value="営業課" checked>営業課
			 	 <input type="radio" name="affiliation" value="人事課" checked>人事課<br>
			 	 <label>性別</label><br>
			 	 <input type="radio" name="gender" value="男性" checked>男性
			 	 <input type="radio" name="gender" value="女性">女性<br>
			 	  <label>役職</label><br>
			 	 <select name="post">
				 	  <option value="課長">課長</option>
				 	  <option value="係長">係長</option>
				 	  <option value="主任">主任</option>
				 	  <option value="役職なし">役職なし</option>
			 	 </select><br>
			 	  <label>生年月日</label><br>
			  	<input type="number" name="birthday" placeholder="19930101">
			 	<input type="submit" value="次へ">
			 	<p><a href="allList">戻る</a></p>
			 </form>
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