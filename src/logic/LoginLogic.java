package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginLogic {

	private final String DRIVER_NAME = "org.sqlite.JDBC";
	private final String JDBC_URL = "jdbc:sqlite:C:/DB2/sqlite/talkApp.db";

	public boolean execute(User user) {
		Connection conn = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL);
			String sql = "SELECT COUNT(*) AS countuser FROM user WHERE name = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user.getName());

			ResultSet rs = pStmt.executeQuery();
			if (rs.getInt("countuser") == 0) {
				return false;
			}
			String sql2 = "SELECT flg FROM user WHERE name = ?";
			PreparedStatement pStmt2 = conn.prepareStatement(sql2);
			pStmt2.setString(1, user.getName());

			ResultSet rs2 = pStmt2.executeQuery();
			if (rs2.getInt("flg") == 0) {
				return false;
			}
			String sql3 = "SELECT pass FROM user WHERE name = ?";
			PreparedStatement pStmt3 = conn.prepareStatement(sql3);
			pStmt3.setString(1, user.getName());

			ResultSet rs3 = pStmt3.executeQuery();
			if (!rs3.getString("pass").equals(user.getPass())) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}
}
