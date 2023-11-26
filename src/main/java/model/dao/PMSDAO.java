package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Info;

public class PMSDAO{
	
	
	//データベース接続
	public static Connection getConnection() throws SQLException , ClassNotFoundException{
		final String URL = "jdbc:mysql://localhost/PMS";
		final String USER = "root";
		final String PASS = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Connection con = DriverManager.getConnection(URL,USER,PASS);
		return con;
	}
	
	//一覧表示
			public List <Info> listDAO(String sql) throws SQLException, ClassNotFoundException {
				
				List <Info> info = new ArrayList<>();
				
				try(Connection connection = getConnection(); //データベースに接続する
			            PreparedStatement statement = connection.prepareStatement(sql)){ //発行したいSQLを生成
			            	ResultSet res = statement.executeQuery();
			            while (res.next()){ //resutlsの中身を次々↓を押して確認する      
			              //resultsの中のid をtitle:titleというセットでcoulmsへ
			            	int userNo = res.getInt("userNo");
							String affiliation = res.getString("affiliation");
							String name = res.getString("name");
							String post = res.getString("post");
							String job = res.getString("job");
							String birthday = res.getString("birthday");
							String gender = res.getString("gender");
							
							info.add(new Info(userNo,affiliation,post,name,job,birthday,gender));
			            }
			          }	
				return info;
			}
	
	
	
	
	//役職検索
	public String postDAO(String sql)  throws SQLException, ClassNotFoundException {
		String post = "";
		try(Connection connection = getConnection(); //データベースに接続する
	        PreparedStatement statement = connection.prepareStatement(sql)){ //発行したいSQLを生成
	            	ResultSet res = statement.executeQuery();
	            while (res.next()){ 
	            	post = res.getString("post");
	            }
	            return post;
		}
	}
	
	//ユーザー検索
		public String userDAO(String sql)  throws SQLException, ClassNotFoundException {
			String user = "";
			try(Connection connection = getConnection(); //データベースに接続する
		        PreparedStatement statement = connection.prepareStatement(sql)){ //発行したいSQLを生成
		            	ResultSet res = statement.executeQuery();
		            while (res.next()){ 
		            	user = res.getString("userId");
		            }
		            return user;
			}
		}
		
		//検索（int）
				public int searchIntDAO(String sql,String search)  throws SQLException, ClassNotFoundException {
					int t = 0;
					try(Connection connection = getConnection(); //データベースに接続する
				        PreparedStatement statement = connection.prepareStatement(sql)){ //発行したいSQLを生成
				            	ResultSet res = statement.executeQuery();
				            while (res.next()){ 
				            	t = res.getInt(search);
				            }
				            return t;
					}
				}
				
				//検索（String）
				public String searchStringDAO(String sql,String search)  throws SQLException, ClassNotFoundException {
					String t = null;
					try(Connection connection = getConnection(); //データベースに接続する
				        PreparedStatement statement = connection.prepareStatement(sql)){ //発行したいSQLを生成
				            	ResultSet res = statement.executeQuery();
				            while (res.next()){ 
				            	t = res.getString(search);
				            }
				            return t;
					}
				}
	
	//カウント検索
		public int countDAO(String sql)  throws SQLException, ClassNotFoundException {
			int count = 0;
			try(Connection connection = getConnection(); //データベースに接続する
		        PreparedStatement statement = connection.prepareStatement(sql)){ //発行したいSQLを生成
		            	ResultSet res = statement.executeQuery();
		            while (res.next()){ 
		            	count = res.getInt("count");
		            }
		            return count;
			}
		}
	
	//新規登録の際のuserId紐付け
		public int idDAO(String sql)  throws SQLException, ClassNotFoundException {
			int userNo = 0;
			try(Connection connection = getConnection(); //データベースに接続する
		        PreparedStatement statement = connection.prepareStatement(sql)){ //発行したいSQLを生成
		            	ResultSet res = statement.executeQuery();
		            while (res.next()){ 
		            	userNo = res.getInt("userNo");
		            }
		            return userNo;
			}
		}
	
	//カウントを0にする または削除する または更新する、または新規登録
	public void zeroDAO(String sql) throws SQLException,ClassNotFoundException{
    	try(Connection connection = getConnection();
   	        PreparedStatement statement = connection.prepareStatement(sql);){
            statement.executeUpdate();
    	} 
	}
	
	//POSTのカウント
	
	public int postCountDAO(String sql) throws SQLException,ClassNotFoundException{
		int i = 0;
    	try(Connection connection = getConnection();
   	        PreparedStatement statement = connection.prepareStatement(sql);){
    		ResultSet res = statement.executeQuery();
    		while (res.next()){ 
            	i = res.getInt("count");
            }
    	return i;
    	} 
	}
	
	

	
}

	
	
	