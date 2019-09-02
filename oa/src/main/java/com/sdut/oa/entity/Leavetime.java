package com.sdut.oa.entity;

import java.util.Date;

/**
 * 请假单管理
 * @author Nuri
 *
 */
public class Leavetime {
	private int id;
	private int uid;
	private String username;
	private String type;
	private String leavemsg;
	private double leavedays;
	private Date starttime;
	private Date endtime;
	private String approver;
	private String power;
	private String state;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLeavemsg() {
		return leavemsg;
	}
	public void setLeavemsg(String leavemsg) {
		this.leavemsg = leavemsg;
	}
	public double getLeavedays() {
		return leavedays;
	}
	public void setLeavedays(double leavedays) {
		this.leavedays = leavedays;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Leavetime(int uid, String username, String type, String leavemsg, double leavedays, Date starttime,
			Date endtime, String approver) {
		super();
		this.uid = uid;
		this.username = username;
		this.type = type;
		this.leavemsg = leavemsg;
		this.leavedays = leavedays;
		this.starttime = starttime;
		this.endtime = endtime;
		this.approver = approver;
	}
	public Leavetime() {
		super();
	}
	public Leavetime(int uid, String username, String type, String leavemsg, double leavedays, Date starttime,
			Date endtime, String approver, String power, String state) {
		super();
		this.uid = uid;
		this.username = username;
		this.type = type;
		this.leavemsg = leavemsg;
		this.leavedays = leavedays;
		this.starttime = starttime;
		this.endtime = endtime;
		this.approver = approver;
		this.power = power;
		this.state = state;
	}
	
}
