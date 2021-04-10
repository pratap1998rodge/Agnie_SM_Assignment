package com.agnie.bean;

public class User {

	//take the private Variable as security purpose
	private int id;
	private String fname;
	private String lname;
	private String dob;
	private String city;
	private String mobile_no;

	
	//Setter(XXX)and Getter(XXX)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	//paraameterized constructor withoout id
	public User(String fname, String lname, String dob, String city, String mobile_no) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
		this.city = city;
		this.mobile_no = mobile_no;
	}//constructor
	//paraameterized constructor witho id
	public User(int id,String fname, String lname, String dob, String city, String mobile_no) {
		super();
		this.id=id;
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
		this.city = city;
		this.mobile_no = mobile_no;
	}//constructor

}//class
