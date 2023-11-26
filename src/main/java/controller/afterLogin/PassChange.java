package controller.afterLogin;

import java.io.IOException;
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
@WebServlet("/passChange")
public class PassChange extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			request.setAttribute("passchange", "change");
			request.setAttribute("message", "新しいパスワードを設定してください");
			RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/views/BeforeLogin/passChange.jsp");
			dis.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userNostr = request.getParameter("userNo");
		int userNo = Integer.parseInt(userNostr);
		String password = request.getParameter("password");
		String passchange = request.getParameter("passchange");
		
		if(password.isEmpty()) { //空白チェック
			request.setAttribute("message","パスワードが空白です");
			if(passchange.equals("miss")) {
				request.setAttribute("userNo",userNo);
				request.setAttribute("passchange","miss");
			}else if(passchange.equals("change")) {
				request.setAttribute("passchange", "change");
			}
			RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/views/BeforeLogin/passChange.jsp");
			dis.forward(request, response);
		}	
		
		try {
			password = Hash.generateHash(password);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		String sql = "UPDATE UserLogin SET pass = '" + password + "' , count = 0 WHERE userNo = " + userNo;
		PMSDAO dao = new PMSDAO();
		try{
			dao.zeroDAO(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if(passchange.equals("miss")) { //アカウントロックの場合
			request.setAttribute("message", "アカウントロックが解除されました");
			RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/views/BeforeLogin/login.jsp");
			dis.forward(request, response);
		} else if(passchange.equals("change")){ //ログイン後のパスワード変更の場合
			HttpSession session = request.getSession();
			String post = (String)session.getAttribute("userPost");
			request.setAttribute("message", "パスワードを変更しました");
			 
             if(post.equals("課長") || post.equals("係長") ) { //役職ごとの画面遷移
             	RequestDispatcher dispatcher = request.getRequestDispatcher("/allList");
                  dispatcher.forward(request, response);	
             }else if(post.equals("主任") || post.isEmpty()|| post.equals( "一般社員") ) {
             	RequestDispatcher dispatcher = request.getRequestDispatcher("/generalList");
                 dispatcher.forward(request, response);	
             }
		}
	}
}
