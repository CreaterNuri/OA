package com.sdut.oa.entity;

import java.util.Date;

/**
 * 报销单管理
 * @author Nuri
 *
 */
public class Account {
	private int id;
	private Date date;
	private String accounttype;
	private double money;
	private String reimbursement;
	private String state;
	private String approver;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getAccounttype() {
		return accounttype;
	}
	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getReimbursement() {
		return reimbursement;
	}
	public void setReimbursement(String reimbursement) {
		this.reimbursement = reimbursement;
	}
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
	public Account(Date date, String accounttype, double money, String reimbursement, String state, String approver) {
		super();
		this.date = date;
		this.accounttype = accounttype;
		this.money = money;
		this.reimbursement = reimbursement;
		this.state = state;
		this.approver = approver;
	}
	public Account() {
		super();
	}
	public Account(int id, Date date, String accounttype, double money, String reimbursement, String state,
			String approver) {
		super();
		this.id = id;
		this.date = date;
		this.accounttype = accounttype;
		this.money = money;
		this.reimbursement = reimbursement;
		this.state = state;
		this.approver = approver;
	}
	
}
