
package controller.afterLogin;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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

@WebServlet("/add")
public class Add extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int userNo = (int)session.getAttribute("userNo");
		String str = String.valueOf(userNo);
		
		if( str == null) {
			response.sendRedirect("login");
		} else {
			
			request.setAttribute("message","新規登録する従業員の情報を入力してください");
			RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/All/allNew.jsp");
			dis.forward(request, response);
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	String name = request.getParameter("name");
	String gender = request.getParameter("gender");
	String birthday = request.getParameter("birthday");
	String post = request.getParameter("post");
	String affiliation = request.getParameter("affiliation");
	String job = request.getParameter("job");
    String userId = request.getParameter("userId");
	String password = request.getParameter("password");
	String hashedPassword = "";
	
	if(post.equals("役職なし")) {
		post = "一般社員";
	} 
		
	if(post.equals("課長")) {
		job = "課内全体管理";
	} 	
	
	try {
		hashedPassword = Hash.generateHash(password);
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
        throw new ServletException("Generate hash Failed", e);
	}
	
	PMSDAO dao = new PMSDAO();
	String sql = "INSERT INTO PMS.List (affiliation , post, name , job , birthday , gender )VALUES ('"+ affiliation + "' , '" + post + "' , '" + name + "' , '" + job + "'  , '" + birthday + "' , '" + gender +"')";
	try {
			dao.zeroDAO(sql);
	} catch (SQLException e) {
        e.printStackTrace();
        request.setAttribute("message", "入力項目が正しくありません");
        RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/All/allNew.jsp");
		dis.forward(request, response);
        throw new ServletException("Database Connection Failed", e);
      
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        throw new ServletException("Generate hash Failed", e);
    }
	
	 sql =" SELECT userNo FROM List WHERE affiliation = '" + affiliation + "' AND post = '" + post + "' AND job = '" + job + "' AND birthday = '" + birthday + "' AND gender = '" + gender + "'"  ;
	 int userNo = 0;
		try {
			userNo = dao.searchIntDAO(sql,"userNo");
		} catch (SQLException e) {
	        e.printStackTrace();
	        throw new ServletException("Database Connection Failed", e);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        throw new ServletException("Generate hash Failed", e);
	    }

	sql ="INSERT INTO PMS.UserLogin ( userId , pass , count , userNo )VALUES ( '" + userId + "' , '" + hashedPassword + "' , 0  , " + userNo + ")";
	
	try {
		dao.zeroDAO(sql);
	} catch (SQLException e) {
        e.printStackTrace();
        throw new ServletException("Database Connection Failed", e);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        throw new ServletException("Generate hash Failed", e);
    }	
	
	request.setAttribute("message","従業員情報を追加しました");
 	RequestDispatcher dispatcher = request.getRequestDispatcher("/allList");	
 	dispatcher.forward(request, response);
 }
}

