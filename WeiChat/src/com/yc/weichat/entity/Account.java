package com.yc.weichat.entity;

import java.io.InputStream;

public class Account implements Cloneable {
	private String userId;
	private String password;
	private String phone;
	private String email;
	private String name;
	private String address;
	private String sex;
	private InputStream pic;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public InputStream getPic() {
		return pic;
	}
	public void setPic(InputStream pic) {
		this.pic = pic;
	}
	@Override
	public String toString() {
		return "Account [userId=" + userId + ", password=" + password
				+ ", phone=" + phone + ", email=" + email + ", name=" + name
				+ ", address=" + address + ", sex=" + sex + "]";
	}
	@Override
	public Account clone() throws CloneNotSupportedException {
		return (Account)super.clone();
	}
	
	
}
