package com.facebook.www.dto;

public class Fb_friendsDTO {
	private String fr_id_fk;
	private String fr_friendId;
	private int fr_confirm;
	private int fr_ask;
	public String getFr_id_fk() {
		return fr_id_fk;
	}
	public void setFr_id_fk(String fr_id_fk) {
		this.fr_id_fk = fr_id_fk;
	}
	public String getFr_friendId() {
		return fr_friendId;
	}
	public void setFr_friendId(String fr_friendId) {
		this.fr_friendId = fr_friendId;
	}
	public int getFr_confirm() {
		return fr_confirm;
	}
	public void setFr_confirm(int fr_confirm) {
		this.fr_confirm = fr_confirm;
	}
	public int getFr_ask() {
		return fr_ask;
	}
	public void setFr_ask(int fr_ask) {
		this.fr_ask = fr_ask;
	}
	
}
