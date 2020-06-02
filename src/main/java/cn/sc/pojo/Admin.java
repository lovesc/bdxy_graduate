package cn.sc.pojo;
/*
 * 
 * 辅导员信息
 * */
public class Admin {
	private Integer id;
	private String username;//辅导员的账号
	private String password;//辅导员的密码
	private int number;//辅导员所属院校
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
}
