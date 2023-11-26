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
import model.dao.PMSDAO;

@WebServlet("/delete")
public class Delete extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		int userNo = (int)session.getAttribute("userNo");
		String str = String.valueOf(userNo);
		String deleteUserAffiliation = "";
		String deleteUserPost = "";
		
		if( str == null) {
			response.sendRedirect("login");
		} else { 
			String deleteUserName = null ;
			String deleteUserNostr = request.getParameter("deleteUserNo");
			int deleteUserNo = Integer.valueOf(deleteUserNostr);
			
			if(deleteUserNo == userNo) {
				request.setAttribute("message","自分自身は削除できません");
				RequestDispatcher dis = request.getRequestDispatcher("/allList");
				dis.forward(request, response);
			}
            
            try{
            	 PMSDAO dao = new PMSDAO();
            	 String sql = "SELECT name FROM List WHERE userNo = " + deleteUserNo ;
                 deleteUserName = dao.searchStringDAO(sql,"name");
                 sql = "SELECT affiliation FROM List WHERE userNo = " + deleteUserNo ;
                 deleteUserAffiliation = dao.searchStringDAO(sql,"affiliation");
                 sql = "SELECT post FROM List WHERE userNo = " + deleteUserNo ;
                 deleteUserPost = dao.searchStringDAO(sql,"post");
            } catch(Exception e) {
            	e.printStackTrace();
            }
            
            request.setAttribute("deleteUserName", deleteUserName);
			request.setAttribute("deleteUserNo", deleteUserNo);
			request.setAttribute("deleteUserAffiliation", deleteUserAffiliation);
			request.setAttribute("deleteUserPost", deleteUserPost);
			RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/All/delete.jsp");
			dis.forward(request,response);
		}
	}
	
	protected void doPost (HttpServletRequest request ,HttpServletResponse response) throws IOException , ServletException{
		
		HttpSession session = request.getSession();
		int userNo = (int)session.getAttribute("userNo"); 
		String str = String.valueOf(userNo);
		String deleteUserAffiliation = request.getParameter("deleteUserAffiliation");
		String deleteUserPost = request.getParameter("deleteUserPost");
		int postCount = 0;
		
		if( str == null) {
			response.sendRedirect("login");
		} else { 
			
			String deleteUserStr = request.getParameter("deleteUserNo");
			int deleteUser = Integer.parseInt(deleteUserStr);
			PMSDAO dao = new PMSDAO();
			
			String sql= "SELECT COUNT( post = '課長' OR post = '係長' ) AS count FROM List WHERE affiliation = '" + deleteUserAffiliation + "'" ;
			try {
				postCount = dao.postCountDAO(sql);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			if(postCount == 1) {
				if(deleteUserPost.equals("課長") || deleteUserPost.equals("係長")) {
					request.setAttribute("message", deleteUserAffiliation +"で編集権限をもった人が<br>いなくなるので変更できません。");
					RequestDispatcher dis = request.getRequestDispatcher("/allList");
					dis.forward(request, response);
				}
			}

		    sql = "DELETE FROM PMS.List WHERE userNo = " + deleteUser;
			try {
				 dao.zeroDAO(sql);
			} catch (SQLException e) {
		        e.printStackTrace();
		        throw new ServletException("Database Connection Failed", e);
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();
		        throw new ServletException("Generate hash Failed", e);
		    }
			
			 sql = "DELETE FROM PMS.UserLogin WHERE userNo = " + deleteUser;
			
			try {
    		   dao.zeroDAO(sql);
    		   request.setAttribute("message", "ユーザー情報を削除しました");    		   
    		   RequestDispatcher dispatcher = request.getRequestDispatcher("/allList");
      	       dispatcher.forward(request, response);
      	       
    		}catch(SQLException | ClassNotFoundException e) {
    			e.printStackTrace();
    		}
		}
		
	}
}


