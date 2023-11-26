<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.* , java.util.ArrayList, model.Info , java.util.List" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>従業員情報管理システム</title>
	<link rel="stylesheet" href="css/delete.css">
	
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
						<p>${deleteUserName}さんの情報を本当に削除しますか？</p>
						<button id="modalOpen" class="button submit">削除する</button>
						<form action="delete" method="post">
						<input type="hidden" name="deleteUserNo" value="${deleteUserNo}">
						<input type="hidden" name="deleteUserAffiliation" value="${deleteUserAffiliation}">
						<input type="hidden" name="deleteUserPost" value="${deleteUserPost}">
						
						  <div id="easyModal" class="modal">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h1 class="check">最終確認画面</h1>
						        <span class="modalClose">×</span>
						      </div>
						      <div class="modal-body">
						        <p>一度削除した情報は復元できません<br>本当に削除しますか？</p>
						        <input type="submit" class="submit" value="削除する">
						      </div>
						    </div>
						  </diV>
						<p><a class="back" href="allList">戻る</a></p>
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
		<script>
		const buttonOpen = document.getElementById('modalOpen');
		const modal = document.getElementById('easyModal');
		const buttonClose = document.getElementsByClassName('modalClose')[0];
	
		// ボタンがクリックされた時
		buttonOpen.addEventListener('click', modalOpen);
		function modalOpen() {
		  modal.style.display = 'block';
		}
	
		// バツ印がクリックされた時
		buttonClose.addEventListener('click', modalClose);
		function modalClose() {
		  modal.style.display = 'none';
		}
	
		// モーダルコンテンツ以外がクリックされた時
		addEventListener('click', outsideClose);
		function outsideClose(e) {
		  if (e.target == modal) {
		    modal.style.display = 'none';
		  }
		}
	</script>
	</body>
</html>