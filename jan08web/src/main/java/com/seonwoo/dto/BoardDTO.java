package com.seonwoo.dto;

import com.seonwoo.util.Util;

public class BoardDTO {
	private String board_title,board_content,board_write,board_date,mid,mno,ip;
	private int board_no,board_count,comment,cdel;
	
	
	
	
	public String getIp() {
		return Util.ipMasking(ip);
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
	public int getComment() {
		return comment;
	}
	public void setComment(int comment) {
		this.comment = comment;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getBoard_write() {
		return board_write;
	}
	public void setBoard_write(String board_write) {
		this.board_write = board_write;
	}
	public String getBoard_date() {
		return board_date;
	}
	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public int getBoard_count() {
		return board_count;
	}
	public void setBoard_count(int board_count) {
		this.board_count = board_count;
	}
	public String getMno() {
		return mno;
	}
	public void setMno(String mno) {
		this.mno = mno;
	}
	
	
	
}
