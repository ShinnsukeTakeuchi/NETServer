package com.shinnosuke.net.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 * DBアクセスクラス
 *
 * @author Shinnosuke
 *
 */
public class DBAccess {
	private InitialContext ctx;
	private DataSource ds;
	private Connection con;
	private Statement state;

	public DBAccess() {

	}

	public void onOpen() throws NamingException, SQLException {
		ctx = new InitialContext();
		ds = (DataSource) ctx.lookup("java:comp/env/jdbc/net_db");
		con = ds.getConnection();
	}

	public void onClose() throws SQLException {
		con.close();
	}

	public Connection getConnection() {
		return con;
	}

	public ResultSet setSqlQuery(String sql) throws SQLException {
		state = con.createStatement();
		ResultSet res = state.executeQuery(sql);

		return res;
	}

	public int setSqlUpdate(String sql) throws SQLException {
		state = con.createStatement();
		int res = state.executeUpdate(sql);

		return res;
	}
}
