package com.shinnosuke.net.beans;

/**
 * 二人チャット用待機情報
 * @author Shinnosuke
 *
 */
public class OneToOneBean {
	private int id;
	private String ownerUser;
	private String pairUser;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOwnerUser() {
		return ownerUser;
	}
	public void setOwnerUser(String ownerUser) {
		this.ownerUser = ownerUser;
	}
	public String getPairUser() {
		return pairUser;
	}
	public void setPairUser(String pairUser) {
		this.pairUser = pairUser;
	}

}
