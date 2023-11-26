package controller.beforeLogin;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Hash;
import model.dao.PMSDAO;


//テーブル設計(UserLogin と　List)
//
//CREATE TABLE PMS.UserLogin (
//		id INTEGER auto_increment NOT NULL,　	 ID
//		userId varchar(100) NOT NULL,		     ユーザーID
//		pass varchar(100) NULL,					 パスワード
//		count INTEGER NOT NULL,				 　　 パスワードをミスした数
//		userNo INTEGER NOT NULL,	　　　　　　　　userNo 外部キー
//		CONSTRAINT Info_PK PRIMARY KEY (id)  　　 プライマリキー　ユーザーNo
//      FOREIGN KEY (userNo) REFERENCES List(userNo)
//)

//CREATE TABLE PMS.List (
//		userNo INTEGER auto_increment NOT NULL,　ユーザーNo
//		affiliation varchar(100) NOT NULL,		 部署
//		post varchar(100) NULL,					 役職
//		name varchar(100) NOT NULL,				 氏名
//		job varchar(100) NOT NULL,　　　　　　　　　職務内容
//		birthday DATE NOT NULL,　　　　　　　　　　　生年月日
//		gender varchar(100) NOT NULL,　　　　　　　 性別
//		CONSTRAINT Info_PK PRIMARY KEY (userNo)  プライマリキー　ユーザーNo
//	)


@WebServlet("/login")
public class Login extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("message", "ログイン画面");
		RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/BeforeLogin/login.jsp");
		dis.forward(request ,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		if( userId.isEmpty() || password.isEmpty() ) { //入力チェック
			request.setAttribute("message","未入力の項目があります");
			String view = "/WEB-INF/views/BeforeLogin/login.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(view);
            dispatcher.forward(request, response);	
		}
			                
        try (Connection connection = PMSDAO.getConnection()){
        	String hashedPassword = Hash.generateHash(password); //パスワードをハッシュ化
        	String sql = "SELECT * FROM UserLogin WHERE userId=? AND pass=?";
	        	try (PreparedStatement stmt = connection.prepareStatement(sql);) {
		                stmt.setString(1, userId);
		                stmt.setString(2, hashedPassword);
		                ResultSet rs = stmt.executeQuery();
		                if (rs.next()) { //中身があったら  	
		                    int userNo = rs.getInt("userNo");
		                    int count = rs.getInt("count");
				                    if( count >= 3 ){   	
				                    	request.setAttribute("message","パスワードを３回以上間違えたのでアカウントがロックされています。<br> "
				                    			+ "ロック解除及びパスワード変更は<a href='unlock'>こちら</a>");
				                    	String view = "/WEB-INF/views/BeforeLogin/login.jsp";
				                         RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				                         dispatcher.forward(request, response);
				                         
				                    	} else {
				                    		
				                    		HttpSession session = request.getSession();
						                    session.setAttribute("userNo", userNo);
						                   
						                   PMSDAO dao = new PMSDAO();
						                   String user = null ;
						                   String affiliation = null;
						                   String post = null;
						                    
						                    try{
						                    	 sql = "SELECT name FROM List WHERE userNo = " + userNo ;
								                 user = dao.searchStringDAO(sql,"name");
								                 sql = "SELECT affiliation FROM List WHERE userNo = " + userNo ;
						                    	 affiliation = dao.searchStringDAO(sql,"affiliation");
						                    	 sql = "SELECT post FROM List WHERE userNo = " + userNo ;
						                    	 post = dao.postDAO(sql);
						    	            } catch(Exception e) {
						    	            	e.printStackTrace();
						    	            }
						                    
						                    session.setAttribute("userName", user);
						                    session.setAttribute("affiliation", affiliation);
						                    session.setAttribute("userPost", post);
						                    
						                    if(post.equals("課長") || post.equals("係長") ) { //役職ごとの画面遷移
						                    	request.setAttribute("message", "");
						                    	RequestDispatcher dispatcher = request.getRequestDispatcher("/allList");
						                         dispatcher.forward(request, response);	
						                    }else if(post.equals("主任") || post.isEmpty() || post.equals("一般社員") ) {
						                    	RequestDispatcher dispatcher = request.getRequestDispatcher("/generalList");
						                        dispatcher.forward(request, response);	
						                    }
				                    		
				                    	}                    
                
		                } else { //中身がなかったらuserIdの情報を持ってpassmissへ
		                	
		                request.setAttribute("userId",userId);
	                    request.getRequestDispatcher("/passmiss").forward(request, response);

	               } // re.nextのif文終了
 
	        	} catch (SQLException e) { //SQLが送信できない場合
	        		 e.printStackTrace();
	        		request.setAttribute("userId",userId);
	                request.getRequestDispatcher("/passmiss").forward(request, response);
	        	}//SQLのtry-catch終了
  	
        } catch (SQLException e) { //DBに繋がらなかった時の例外処理

            e.printStackTrace();
            throw new ServletException("Database Connection Failed", e);
            
        } catch (NoSuchAlgorithmException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("Generate hash Failed", e);
        } //DB接続のtry-catch終了
		
	} //doPost

} //classの終了タグ
