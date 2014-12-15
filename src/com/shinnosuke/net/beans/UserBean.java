package com.shinnosuke.net.beans;

/**
 * ユーザ管理用Bean
 * @author Shinnosuke
 *
 */
public class UserBean {
	private String phoneId;
	private String userId;
	private String userName;
	private String sexId;
	private String sex;
	private short age;
	private int addressId;
	private String address;

	public String getPhoneId() {
		return phoneId;
	}
	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSexId() {
		return sexId;
	}
	public void setSexId(String sexId) {
		this.sexId = sexId;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public short getAge() {
		return age;
	}
	public void setAge(short age) {
		this.age = age;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
