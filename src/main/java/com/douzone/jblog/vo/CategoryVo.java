package com.douzone.jblog.vo;

public class CategoryVo {
	private long no;
	private String name;
	private String description;
	private String regDate;
	private long userNo;
	private long topPostNo;
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public long getUserNo() {
		return userNo;
	}
	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}
	public long getTopPostNo() {
		return topPostNo;
	}
	public void setTopPostNo(long topPostNo) {
		this.topPostNo = topPostNo;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", description=" + description + ", regDate=" + regDate
				+ ", userNo=" + userNo + ", topPostNo=" + topPostNo + "]";
	}
	
	
}
