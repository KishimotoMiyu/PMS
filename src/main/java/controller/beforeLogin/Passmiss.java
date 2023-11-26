package controller.beforeLogin;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.PMSDAO;

@WebServlet("/passmiss")
public class Passmiss extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("message", "ログイン画面");
		RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/BeforeLogin/login.jsp");
		dis.forward(request ,response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException , ServletException{
		
		String userId = (String)request.getAttribute("userId");
		int count = 0;
		PMSDAO dao = new PMSDAO();
		
		try {
			String sql = "SELECT count FROM UserLogin WHERE userId = '" + userId + "'";
			count = dao.countDAO(sql);
		} catch (SQLException e) {
			request.setAttribute("message", "UserIDが間違っています");
			RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/BeforeLogin/login.jsp");
			dis.forward(request,response);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		count += 1 ;
		
		String sql = "UPDATE UserLogin SET count = " + count + " WHERE userId = '" + userId + "'"   ;
		
		try {
			dao.zeroDAO(sql);
			request.setAttribute("message", "userID または パスワードが間違っています");
			RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/BeforeLogin/login.jsp");
			dis.forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
