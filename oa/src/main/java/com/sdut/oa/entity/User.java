package com.sdut.oa.entity;
/**
 * 用户表对应实体类
 * @author Nuri
 *
 */
public class User {
	private int uid;
	private String uname;
	private String upwd;
	private int state;
	private String power;
	
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public User() {
		super();
	}
	public User(String uname, String upwd, int state, String power) {
		super();
		this.uname = uname;
		this.upwd = upwd;
		this.state = state;
		this.power = power;
	}
	public User(int uid, String uname, String upwd, int state, String power) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.upwd = upwd;
		this.state = state;
		this.power = power;
	}
	public User(int uid, String uname, String upwd) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.upwd = upwd;
	}
	public User(int uid, String uname, String upwd, int state) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.upwd = upwd;
		this.state = state;
	}
	public User(String uname, String upwd) {
		super();
		this.uname = uname;
		this.upwd = upwd;
	}
	public User(int uid) {
		super();
		this.uid = uid;
	}
	
}
