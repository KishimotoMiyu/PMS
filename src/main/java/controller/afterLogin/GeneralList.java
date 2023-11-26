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

@WebServlet("/generalList")
public class GeneralList extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int userNo = (int)session.getAttribute("userNo");
		String str = String.valueOf(userNo);
		PMSDAO dao = new PMSDAO();
		
		if(str == null ) {
			response.sendRedirect("login");
		} else {	
			 String sql = "SELECT * FROM List WHERE userNo = " + userNo;
			
			try {
    			List <Info> info = dao.listDAO(sql);
    			request.setAttribute("info", info);
     		    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/General/general.jsp");
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
		PMSDAO dao = new PMSDAO();
	
		if(str == null ) {
			response.sendRedirect("login");
		} else {
			try {
				String sql = "UPDATE UserLogin SET count = 0 WHERE userNo = " + userNo ;
				dao.zeroDAO(sql);
				sql = "SELECT * FROM List WHERE userNo = " + userNo;
    			List <Info> info = dao.listDAO(sql);
    			request.setAttribute("info", info);
    		    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/General/general.jsp");
      	        dispatcher.forward(request, response);     
    		}catch(SQLException | ClassNotFoundException e) {
    			e.printStackTrace();
    		}
		}
	}
}
