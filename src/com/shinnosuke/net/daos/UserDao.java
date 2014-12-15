package com.shinnosuke.net.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import com.shinnosuke.net.beans.UserBean;
import com.shinnosuke.net.db.DBAccess;

public class UserDao {
	DBAccess access = new DBAccess();

	public UserDao(){}

	public ArrayList<UserBean> findAll() {
		ArrayList<UserBean> list = new ArrayList<UserBean>();

		String sql = "select "
				+ "u.phone_id, u.user_id, u.user_name, s.sex_name, u.age, a.address_name "
				+ "from "
				+ "user as u JOIN sex as s ON u.sex_id=s.sex_id JOIN address as a ON u.address_id=a.address_id;";

		try {
			access.onOpen();
			ResultSet result = access.setSqlQuery(sql);
			while(result.next()) {
				UserBean userBean = new UserBean();

				userBean.setPhoneId(result.getString("phone_id"));
				userBean.setUserId(result.getString("user_id"));
				userBean.setUserName(result.getString("user_name"));
				userBean.setSex(result.getString("sex_name"));
				userBean.setAge(result.getShort("age"));
				userBean.setAddress(result.getString("address_name"));

				list.add(userBean);
			}
			access.onClose();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<UserBean> findSearchById(String userId) {
		ArrayList<UserBean> list = new ArrayList<UserBean>();

		String sql = "select "
				+ "u.phone_id, u.user_id, u.user_name, s.sex_name, u.age, a.address_name "
				+ "from "
				+ "user as u JOIN sex as s ON u.sex_id=s.sex_id JOIN address as a ON u.address_id=a.address_id "
				+ "where u.user_id='"+userId+"';";

		try {
			access.onOpen();
			ResultSet result = access.setSqlQuery(sql);
			while(result.next()) {
				UserBean userBean = new UserBean();

				userBean.setPhoneId(result.getString("phone_id"));
				userBean.setUserId(result.getString("user_id"));
				userBean.setUserName(result.getString("user_name"));
				userBean.setSex(result.getString("sex_name"));
				userBean.setAge(result.getShort("age"));
				userBean.setAddress(result.getString("address_name"));

				list.add(userBean);
			}
			access.onClose();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean insertUser(UserBean userBean) {
		boolean isCheck = false;

		String phoneId  = userBean.getPhoneId();
		String userId   = userBean.getUserId();
		String userName = userBean.getUserName();
		String sexId    = userBean.getSexId();
		short age       = userBean.getAge();
		int addressId   = userBean.getAddressId();

		String sql = "insert into net_db.user(phone_id, user_id, user_name, sex_id, age, address_id) "
				+ "values('"
				+phoneId+ "','"
				+userId+ "','"
				+userName+ "','"
				+sexId+ "',"
				+age+ ","
				+addressId+ ");";

		try {
			access.onOpen();
			int result = access.setSqlUpdate(sql);

			if (result!=0) {
				isCheck = true;
			}

			access.onClose();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isCheck;
	}
}
