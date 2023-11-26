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

@WebServlet("/update")
public class Update extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int userNo = (int)session.getAttribute("userNo");
		String str = String.valueOf(userNo);
		
		if( str == null) {
			response.sendRedirect("login");
		} else { 
			request.setAttribute("message", "下記の項目を入力してください");
			String userStr = request.getParameter("updateuserNo");
			int user = Integer.parseInt(userStr);
			PMSDAO dao = new PMSDAO();
			String sql = "SELECT * FROM List WHERE userNo = " + user;

			try {
    			List <Info> info = dao.listDAO(sql);
    			request.setAttribute("info", info);

    		   RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/All/allUpdate.jsp");
      	       dispatcher.forward(request, response);
      	       
    		}catch(SQLException | ClassNotFoundException e) {
    			e.printStackTrace();
    		}	
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		int myNo = (int)session.getAttribute("userNo");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		String post = request.getParameter("post");
		String affiliation = request.getParameter("affiliation");
		String job = "";
		String userStr = request.getParameter("userNo");
		String managerPost = (String)request.getAttribute("managerPost");
		
		if(managerPost != null) {
			name = (String)request.getAttribute("name");
			gender =(String) request.getAttribute("gender");
			birthday = (String)request.getAttribute("birthday");
			affiliation = (String)request.getAttribute("affiliation");
			userStr = (String)request.getAttribute("userNo");
			post = "課長";
			job = "課内全体管理";
			
		}else if(post.equals("役職なし")) {
			post = "一般社員";
			job = request.getParameter("job");
		}else {
			job = request.getParameter("job");
		}
		
		int updateUserNo = Integer.parseInt(userStr);
		
		PMSDAO dao = new PMSDAO();
		String sql = "UPDATE List SET post ='" + post +"',affiliation='" + affiliation + "',name='" + name + "',job='" + job + "',birthday='" + birthday + "',gender='" + gender + "'WHERE userNo=" + updateUserNo;
		
		try {
				dao.zeroDAO(sql);
		} catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database Connection Failed", e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("Generate hash Failed", e);
        }
		
		if( updateUserNo == myNo) {
			session.setAttribute("affiliation", affiliation);
			session.setAttribute("userPost", post);
		}
		
		String mypost = (String)session.getAttribute("userPost");
		request.setAttribute("message","情報を更新しました");
			 if(mypost.equals("課長") || mypost.equals("係長") ) { //役職ごとの画面遷移	         	
		         	RequestDispatcher dispatcher = request.getRequestDispatcher("/allList");
		              dispatcher.forward(request, response);	
		         }else if(mypost.equals("主任") || mypost.equals("一般社員") || mypost == null ) {
		         	RequestDispatcher dispatcher = request.getRequestDispatcher("/generalList");
		             dispatcher.forward(request, response);	
		         }
			}
	}

