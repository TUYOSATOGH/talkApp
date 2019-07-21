package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegistLogic {

	private final String DRIVER_NAME = "org.sqlite.JDBC";
	private final String JDBC_URL = "jdbc:sqlite:C:/DB2/sqlite/talkApp.db";

	public boolean execute(User user) {
		Connection conn = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL);
			String sql = "insert into user(name, pass, flg) values(?, ?, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user.getName());
			pStmt.setString(2, user.getPass());
			pStmt.setString(3, "1");
			int result = pStmt.executeUpdate();

			if (result != 1) {
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
