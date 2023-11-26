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

@WebServlet("/unlock")
public class Unlock extends HttpServlet {
	 
	protected void doGet (HttpServletRequest request,HttpServletResponse response) throws IOException , ServletException{
		request.setAttribute("message", "下記項目を入力してください");
		RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/views/BeforeLogin/passMiss.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException , ServletException{
		
		String userId = request.getParameter("userId");
		String birthday = request.getParameter("birthday");
		String affiliation = request.getParameter("affiliation");
		int n = 0;
		
		if(userId.isEmpty() || birthday.isEmpty() || affiliation.isEmpty()) {
			request.setAttribute("message", "未入力の項目があります。");
			RequestDispatcher dis =request.getRequestDispatcher("/WEB-INF/views/BeforeLogin/passMiss.jsp");
			dis.forward(request, response);
		} else {
			String sql ="SELECT UserNo FROM UserLogin WHERE userId = '" + userId +"'" ;
			PMSDAO dao = new PMSDAO();
			
			try {
				n = dao.searchIntDAO(sql,"userNo");
				sql ="SELECT userNo FROM List WHERE birthday = '" + birthday + "' AND  affiliation = '" + affiliation + "' AND userNo = " + n ;
				n = dao.searchIntDAO(sql,"userNo");
			}catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
		
			}
			
			if( n == 0 ) {
				request.setAttribute("message","入力項目が正しくありません");
				RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/BeforeLogin/passMiss.jsp");
				dis.forward(request, response);
			}
			
			request.setAttribute("userNo",n);
			request.setAttribute("passchange","miss");
			request.setAttribute("message", "新しいパスワードを設定してください");
			RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/views/BeforeLogin/passChange.jsp");
			dis.forward(request, response);
		}
		
	}

}
