package com.yc.weichat.entity;

public class Group implements Cloneable {
	private String id;
	private String name;
	private String admin;
	
	public Group() {
		super();
	}
	
	public Group(String id, String name, String admin) {
		this.id = id;
		this.name = name;
		this.admin = admin;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	
	@Override
	public Group clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (Group) super.clone();
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", admin=" + admin + "]";
	}
	
	
	

}
