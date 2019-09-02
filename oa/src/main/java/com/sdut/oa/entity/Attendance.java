package com.sdut.oa.entity;
/**
 * 考勤管理
 * @author Nuri
 *
 */
public class Attendance {
	private int id;
	private int uid;
	private double dutydays;
	private double requestdays;
	private double overtimedays;
	private double actualdays;
	private int year;
	private int month;
	private String power;
	
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
	public double getDutydays() {
		return dutydays;
	}
	public void setDutydays(double dutydays) {
		this.dutydays = dutydays;
	}
	public double getRequestdays() {
		return requestdays;
	}
	public void setRequestdays(double requestdays) {
		this.requestdays = requestdays;
	}
	public double getOvertimedays() {
		return overtimedays;
	}
	public void setOvertimedays(double overtimedays) {
		this.overtimedays = overtimedays;
	}
	public double getActualdays() {
		return actualdays;
	}
	public void setActualdays(double actualdays) {
		this.actualdays = actualdays;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public Attendance(int uid) {
		super();
		this.uid = uid;
	}
	public Attendance() {
		super();
	}
	public Attendance(int id, double overtimedays, double actualdays) {
		super();
		this.id = id;
		this.overtimedays = overtimedays;
		this.actualdays = actualdays;
	}
	public Attendance(int uid, double dutydays, double actualdays, int year, int month, String power) {
		super();
		this.uid = uid;
		this.dutydays = dutydays;
		this.actualdays = actualdays;
		this.year = year;
		this.month = month;
		this.power = power;
	}
	

}
