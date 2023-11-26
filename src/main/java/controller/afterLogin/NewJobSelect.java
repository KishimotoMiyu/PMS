package controller.afterLogin;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.PMSDAO;
@WebServlet("/newjobSelect")
public class NewJobSelect extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.sendRedirect("login");	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userNo = (int)session.getAttribute("userNo");
		String str = String.valueOf(userNo);
		
		if(str == null ) {
			response.sendRedirect("login");
		} else {
			String userName = request.getParameter("name");
			String birthday = request.getParameter("birthday");
			String userId = request.getParameter("userId");
			String password = request.getParameter("password");
			
			if (userName.isEmpty() || birthday.isEmpty() || userId.isEmpty() || password.isEmpty() ) {
				request.setAttribute("message","未項目の項目があります");
				RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/All/allNew.jsp");
				dis.forward(request, response);		
			}
			
			PMSDAO dao = new PMSDAO();
			try {
				String sql ="SELECT userId FROM PMS.UserLogin WHERE userId = '"+ userId +"'";
				String a = dao.userDAO(sql);
				if(a.equals(userId) ) {
				request.setAttribute("message","既に使用済みのuserIDです");
				RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/All/allNew.jsp");
				dis.forward(request, response);	
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		
			
			
			String postJob = request.getParameter("post");
			if(postJob.equals("課長")) {				
				RequestDispatcher dispatcher = request.getRequestDispatcher("add");
	      	    dispatcher.forward(request, response);
			}

			String affiliation = request.getParameter("affiliation");
			
			if(affiliation.equals("経理課")) {
				request.setAttribute("select","<option value='会計'>会計</option><br><option value='出納'>出納</option>");
			}else if(affiliation.equals("人事課")){
				request.setAttribute("select","<option value='新卒採用'>新卒採用</option><br><option value='中途採用'>中途採用</option>");	
			}else if(affiliation.equals("営業課")){
				request.setAttribute("select","<option value='仕入'>仕入</option><br><option value='販売'>販売</option><br><option value='倉庫'>倉庫</option>");	
			}
			
			request.setAttribute("name",request.getParameter("name"));
			request.setAttribute("gender",request.getParameter("gender"));
			request.setAttribute("birthday",request.getParameter("birthday"));
			request.setAttribute("post",postJob);
			request.setAttribute("affiliation",affiliation);
			request.setAttribute("userId",request.getParameter("userId"));
			request.setAttribute("password",request.getParameter("password"));
			
    		   RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/All/newjobSelect.jsp");
      	       dispatcher.forward(request, response);
      	       
		
 		
			}
    		}
		}
	

