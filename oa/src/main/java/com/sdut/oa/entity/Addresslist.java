package com.sdut.oa.entity;
/**
 * 通讯录管理
 * @author Nuri
 *
 */
public class Addresslist {
	private int id;
	private String name;
	private String phone;
	private String address;
	private String email;
	private String power;
	
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Addresslist(String name) {
		super();
		this.name = name;
	}
	public Addresslist(int id) {
		super();
		this.id = id;
	}
	public Addresslist() {
		super();
	}
	public Addresslist(String name, String phone, String address, String email, String power) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.power = power;
	}
	public Addresslist(int id, String name, String phone, String address, String email, String power) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.power = power;
	}
	
}
