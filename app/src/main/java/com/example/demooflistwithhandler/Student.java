package com.example.demooflistwithhandler;

public class Student {

	private String Id;
	private String Sname,Email;
	public Student() {
	}
	
	public Student(String id,String sname,String email) {
		this.Id=id;
		this.Sname=sname;
		this.Email=email;
	}
	public Student(String sname,String email){
	
		this.Sname=sname;
		this.Email=email;
	}
	
	public String getId(){
		return Id;
	}
	public void SetId(String id){
		this.Id=id;
	}
	
	public String getSname(){
		return Sname;
	}
	
	public void SetSname(String sname){
		this.Sname=sname;
	}
	public String getEmail(){
		return Email;
	}
	public void SetEmail(String email){
		this.Email=email;
	}
}

