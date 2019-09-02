package com.sdut.oa.entity;
/**
 * 公告 实体表
 * @author Nuri
 *
 */
public class Notice {
	private int id;
	private String noticemsg;
	private String publisher;
	private String releasetime;
	private int number;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNoticemsg() {
		return noticemsg;
	}
	public void setNoticemsg(String noticemsg) {
		this.noticemsg = noticemsg;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getReleasetime() {
		return releasetime;
	}
	public void setReleasetime(String releasetime) {
		this.releasetime = releasetime;
	}
	public Notice(int id, String noticemsg, String publisher, String releasetime) {
		super();
		this.id = id;
		this.noticemsg = noticemsg;
		this.publisher = publisher;
		this.releasetime = releasetime;
	}
	public Notice() {
		super();
	}
	public Notice(int id) {
		super();
		this.id = id;
	}
	public Notice(String noticemsg, String publisher, String releasetime) {
		super();
		this.noticemsg = noticemsg;
		this.publisher = publisher;
		this.releasetime = releasetime;
	}
	public Notice(int id, String publisher) {
		super();
		this.id = id;
		this.publisher = publisher;
	}
	public Notice(String noticemsg, String publisher, String releasetime, int number) {
		super();
		this.noticemsg = noticemsg;
		this.publisher = publisher;
		this.releasetime = releasetime;
		this.number = number;
	}
	public Notice(int id, String noticemsg, String releasetime, int number) {
		super();
		this.id = id;
		this.noticemsg = noticemsg;
		this.releasetime = releasetime;
		this.number = number;
	}
	public Notice(int id, String noticemsg, String publisher, String releasetime, int number) {
		super();
		this.id = id;
		this.noticemsg = noticemsg;
		this.publisher = publisher;
		this.releasetime = releasetime;
		this.number = number;
	}
	
	
}
