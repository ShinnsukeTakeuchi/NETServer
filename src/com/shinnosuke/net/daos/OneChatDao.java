package com.shinnosuke.net.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import com.shinnosuke.net.db.DBAccess;

/**
 * 二人チャット用のDAO
 *
 * @author Shinnosuke
 *
 */
public class OneChatDao {
	DBAccess access = new DBAccess();

	public OneChatDao() {}

	public String searchRoom() throws NamingException, SQLException {
		access.onOpen();
		Connection con = access.getConnection();

		PreparedStatement state = con.prepareStatement("select id from one_to_one where pair_user='' LIMIT 1");
		ResultSet result = state.executeQuery();

		if(result.next()) {
			return result.getString("id");
		}

		return "";
	}

	public ArrayList<String> inRoom(String id) {
		ArrayList<String> roomList = new ArrayList<String>();
		boolean isLoop = true;

		while (isLoop) {
			try {
				int listCount = 0;
				access.onOpen();
				ResultSet res = access.setSqlQuery("select id from one_to_one where pair_user='' LIMIT 1");

				while (res.next()) {
					roomList.add(res.getString("id"));
					listCount++;
				}

				if (listCount == 0) {
					access.setSqlUpdate("insert into one_to_one (owner_user, pair_user) values('user','')");
					continue;
				} else if (listCount > 0) {
					isLoop = false;
				}
				access.onClose();
			} catch (NamingException e) {
				// エラー不具合あり
				e.printStackTrace();
			} catch (SQLException e) {
				// エラー不具合あり
				e.printStackTrace();
			}
		}
		return roomList;
	}

	public void outRoom(String roomName) {
		try {
			access.onOpen();
			access.setSqlUpdate("delete from one_to_one where id='"+roomName+"'");
			access.onClose();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getRoomName(String userName) {
		String roomName = "";
		try {
			access.onOpen();
			ResultSet res = access.setSqlQuery("select id from one_to_one where owner_user='"+userName+"' or pair_user='"+userName+"'");
			while(res.next()) {
				roomName = res.getString("id");
			}

			access.onClose();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roomName;
	}
}
