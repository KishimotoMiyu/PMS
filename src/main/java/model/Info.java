package model;

public class Info{
	
	private int userNo;
	private String affiliation;
	private String post;
	private String name;	
	private String job;
	private String birthday;
	private String gender;
	
	public Info() {
	}

	
	public Info(int userNo ,String affiliation  ,String post ,String name , String job , String birthday , String gender ){
		this.userNo = userNo;
		this.affiliation = affiliation;
		this.post = post;
		this.name =  name;
		this.job = job ;
		this.birthday = birthday;
		this.gender = gender;
	}

	// getter
	
	public int getUserNo() {
		return this.userNo;
	}
	
	public String getAffiliation() {
		return this.affiliation;
	}
	
	public String getName() {
		return this.name;
	}
		
	public String getPost() {
		return this.post;
	}
	
	public String getJob() {
		return this.job;
	}
	
	public String getBirthday() {
		return this.birthday;
	}
	
	public String getGender() {
		return this.gender;
	}
	
	//setter
	
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}
	
	public void setName(String name) {
		this.name = name;
	}
		
	public void setPost(String post) {
		this.post = post;
	}
	
	public void setJob(String job) {
		this.job = job;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
}


