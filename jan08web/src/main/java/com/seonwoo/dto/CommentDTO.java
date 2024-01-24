package com.seonwoo.dto;

public class CommentDTO {
	private int cno,board_no,mno,clike,cdel;
	private String ccomment,cdate,mid,mname,ip;
	
	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getCdel() {
		return cdel;
	}
	public void setCdel(int cdel) {
		this.cdel = cdel;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getClike() {
		return clike;
	}
	public void setClike(int clike) {
		this.clike = clike;
	}
	public String getCcomment() {
		return ccomment;
	}
	public void setCcomment(String ccomment) {
		this.ccomment = ccomment;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	
	
}
