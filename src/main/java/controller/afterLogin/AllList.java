package controller.afterLogin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Info;
import model.dao.PMSDAO;

@WebServlet("/allList")
public class AllList extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userNo = (int)session.getAttribute("userNo");
		String str = String.valueOf(userNo);
		String message = (String)request.getAttribute("message");
		String post = "";
		
	
		if(str == null ) {
			response.sendRedirect("login");
		} else {
			PMSDAO dao = new PMSDAO();
			
			
			String sql = "UPDATE User SET count = 0 WHERE userNo = " + userNo ;
			try {
				dao.zeroDAO(sql);
			}catch(SQLException e) {
				e.printStackTrace();
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			sql = "SELECT post FROM List WHERE userNo = " + userNo ;
			try {
				post = dao.postDAO(sql);
			}catch(SQLException e) {
				e.printStackTrace();
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
  			
  			if(post.equals("主任")||post.equals("一般社員")||post.isEmpty()) {
  				 session.setAttribute("userPost", post);
  				 request.setAttribute("message", "");
               	 RequestDispatcher dispatcher = request.getRequestDispatcher("/allList");
                   dispatcher.forward(request, response);	
  			}
			
			
			sql = "SELECT * FROM List";
			
			try {
    			List <Info> info = dao.listDAO(sql);
    			request.setAttribute("info", info);
    			request.setAttribute("message", message);

    		   RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/All/allList.jsp");
      	       dispatcher.forward(request, response);
      	       
    		}catch(SQLException | ClassNotFoundException e) {
    			e.printStackTrace();
    		}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int userNo = (int)session.getAttribute("userNo");
		String str = String.valueOf(userNo);
	
		if(str == null ) {
			response.sendRedirect("login");
		} else {
			PMSDAO dao = new PMSDAO();
			String sql = "SELECT * FROM List";
			
			try {
    			List <Info> info = dao.listDAO(sql);
    			request.setAttribute("info", info);

    		   RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/All/allList.jsp");
      	       dispatcher.forward(request, response);
      	       
    		}catch(SQLException | ClassNotFoundException e) {
    			e.printStackTrace();
    		}
		}
	}

}
