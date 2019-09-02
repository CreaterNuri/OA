package com.sdut.oa.entity;
/**
 * 加班表
 * @author Nuri
 *
 */
public class Overtime {
	private int id;
	private int uid;
	private int year;
	private int month;
	private double overtimedays;
	private String power;
	private String approver;
	private String state;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
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
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public double getOvertimedays() {
		return overtimedays;
	}
	public void setOvertimedays(double overtimedays) {
		this.overtimedays = overtimedays;
	}
	public Overtime(int id, int uid, int year, int month, double overtimedays) {
		super();
		this.id = id;
		this.uid = uid;
		this.year = year;
		this.month = month;
		this.overtimedays = overtimedays;
	}
	public Overtime() {
		super();
	}
	public Overtime(int uid, int year, int month, double overtimedays) {
		super();
		this.uid = uid;
		this.year = year;
		this.month = month;
		this.overtimedays = overtimedays;
	}
	public Overtime(int uid, int year, int month, double overtimedays, String power) {
		super();
		this.uid = uid;
		this.year = year;
		this.month = month;
		this.overtimedays = overtimedays;
		this.power = power;
	}
	public Overtime(int uid, int year, int month, double overtimedays, String power, String approver) {
		super();
		this.uid = uid;
		this.year = year;
		this.month = month;
		this.overtimedays = overtimedays;
		this.power = power;
		this.approver = approver;
	}
	public Overtime(int uid, int year, int month, double overtimedays, String power, String approver, String state) {
		super();
		this.uid = uid;
		this.year = year;
		this.month = month;
		this.overtimedays = overtimedays;
		this.power = power;
		this.approver = approver;
		this.state = state;
	}
	
	
}
