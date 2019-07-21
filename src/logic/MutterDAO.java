package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MutterDAO {

	private final String DRIVER_NAME = "org.sqlite.JDBC";
	private final String JDBC_URL = "jdbc:sqlite:C:/DB2/sqlite/talkApp.db";

	public List<Mutter> findAll() {
		Connection conn = null;
		List<Mutter> mutterList = new ArrayList<Mutter>();

		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL);
			String sql = "select name, mutter, time from mutter";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				String username = rs.getString("name");
				String text = rs.getString("mutter");
				String time = rs.getString("time");
				Mutter mutter = new Mutter(username, text, time);
				mutterList.add(mutter);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return mutterList;
	}

	public boolean insert(Mutter mutter) {
		Connection conn = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL);
			String sql = "insert into mutter(name,mutter,time) values(?, ?, ?)";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, mutter.getUserName());
			pStmt.setString(2, mutter.getText());
			pStmt.setString(3, mutter.getTime());
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

	public boolean delete(Mutter mutter) {
		Connection conn = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL);
			String sql = "DELETE FROM mutter WHERE name=? AND mutter=? AND time=?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, mutter.getUserName());
			pStmt.setString(2, mutter.getText());
			pStmt.setString(3, mutter.getTime());
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