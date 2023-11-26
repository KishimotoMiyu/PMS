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

@WebServlet("/jobSelect")
public class JobSelect extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.sendRedirect("login");	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userNo = (int)session.getAttribute("userNo"); //自分のNo
		String str = String.valueOf(userNo);
		String updatePost = request.getParameter("post"); ///更新後のPost
		String nowPost = request.getParameter("updateUserPost"); //今のPost
		String nowAffiliation =request.getParameter("updateUserAffiliation"); //今の所属
		String name = request.getParameter("name");
		String affiliation = request.getParameter("affiliation"); //更新後の所属
		String userStr = request.getParameter("updateUserNo"); //更新する人のuserNo
		int user = Integer.parseInt(userStr);//更新する人のuserNo
		String sql = "";
		int postCount = 0;
		PMSDAO dao = new PMSDAO();
		if(name.isEmpty()) { //空白チェック
			
			
			 sql = "SELECT * FROM List WHERE userNo = " + user;
			try {
    			List <Info> info = dao.listDAO(sql);
    			request.setAttribute("info", info);
    			request.setAttribute("message", "未入力の項目があります。");
    			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/All/allUpdate.jsp");
       	        dispatcher.forward(request, response);
      	       
    		}catch(SQLException | ClassNotFoundException e) {
    			e.printStackTrace();
    		}
			
		}else { //nameがnullじゃなかったら
			
			sql= "SELECT COUNT( post = '課長' OR post = '係長' OR NULL  ) AS count FROM List WHERE affiliation = '" + nowAffiliation + "'" ;
			try {
				postCount = dao.postCountDAO(sql);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println(postCount);
			System.out.println("----------------------------------");
			
			//今が課長または係長で、他の役職または別部署の権限持ちに変更になった場合
			if((nowPost.equals("課長") || nowPost.equals("係長") ) && (nowAffiliation !=  affiliation) && (postCount <= 1) && (updatePost.equals("主任") || updatePost.equals("役職なし") )) {
				try {
					sql = "SELECT * FROM List WHERE userNo = " + user;
	    			List <Info> info = dao.listDAO(sql);
	    			request.setAttribute("info", info);
					request.setAttribute("message", nowAffiliation +"で編集権限をもった人が<br>いなくなるので変更できません。");

	    		   RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/All/allUpdate.jsp");
	      	       dispatcher.forward(request, response);
	      	       
	    		}catch(SQLException | ClassNotFoundException e) {
	    			e.printStackTrace();
	    		}
			}
		}
		
			if(str == null ) {
				response.sendRedirect("login");
			} else {
				
				if(updatePost.equals("課長")) {				
					request.setAttribute("name",request.getParameter("name"));
					request.setAttribute("gender",request.getParameter("gender"));
					request.setAttribute("birthday",request.getParameter("birthday"));
					request.setAttribute("managerPost","課長");
					request.setAttribute("affiliation",affiliation);
					request.setAttribute("userNo",userStr);
					RequestDispatcher dispatcher = request.getRequestDispatcher("update");
		      	    dispatcher.forward(request, response);
		      	   
				}

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
				request.setAttribute("post",updatePost);
				request.setAttribute("affiliation",affiliation);
				request.setAttribute("userNo",userStr);
				
	    		   RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/All/jobSelect.jsp");
	      	       dispatcher.forward(request, response);
				}			
		}
    }

	

