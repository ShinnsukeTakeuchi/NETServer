package com.shinnosuke.net.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

	public String searchInRoom(String userName) throws NamingException, SQLException {
		access.onOpen();
		Connection con = access.getConnection();

		PreparedStatement state = con.prepareStatement("select id from one_to_one where pair_user='' LIMIT 1");
		ResultSet result = state.executeQuery();

		if(result.next()) {//空室のチャットルームがある場合
			String roomId = result.getString("id");

			String updateSql = "update net_db.one_to_one set pair_user='"+userName+"' where id="+roomId+";";

			Statement updateState = con.createStatement();
			updateState.executeUpdate(updateSql);

			return result.getString("id");
		} else {//空室のチャットルームがない場合
			String insertSql = "insert into net_db.one_to_one(owner_user, pair_user) values('"+userName+"', '');";
			Statement insertState = con.createStatement();
			insertState.executeUpdate(insertSql);
			ResultSet insertRes = state.executeQuery();

			if(insertRes.next()) {
				return insertRes.getString("id");
			}
		}
		return "";
	}

	public boolean inRoom(String roomId, String userName) {
		boolean isCheck = false;
//		try {
////			int listCount = 0;
////			access.onOpen();
////			ResultSet res = access.setSqlQuery("select id from one_to_one where pair_user='' LIMIT 1");
////
////			while (res.next()) {
////				listCount++;
////			}
////
////			if (listCount == 0) {
////				access.setSqlUpdate("insert into one_to_one (owner_user, pair_user) values('user','')");
////			} else if (listCount > 0) {
////			}
////			access.onClose();
//		} catch (NamingException e) {
//			// エラー不具合あり
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// エラー不具合あり
//			e.printStackTrace();
//		}
		return isCheck;
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
