package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {

	private static final ThreadLocal<Connection> connection = new ThreadLocal<>();
	private static PreparedStatement stmt = null;
	private static ResultSet rs = null;

	private static final String URL = "jdbc:sqlserver://localhost;databaseName=LumaAppTest;encrypt=false;trustServerCertificate=true";

	public static synchronized Connection establishConnection() throws SQLException {
		
		Connection conn = connection.get();
		
		try {
			
			if (conn == null || conn.isClosed()) {
			conn = DriverManager.getConnection(URL, "mydb", "test@123");			
			connection.set(conn);
			System.out.println(connection.get());
			}
			
		} catch (Exception e) {			
			throw new SQLException("Database driver not found", e);
		}
		
		return connection.get();
	}

	public static synchronized String getTestDataForMethod(String methodName) throws SQLException {

		String testData = null;
	

		try {
			stmt = connection.get().prepareStatement("SELECT * FROM TestCases WHERE TestMethod = ? AND Run = 1");
			stmt.setString(1, methodName);
			rs = stmt.executeQuery();

			if (rs.next()) {
				testData = rs.getString("TestData");
			}
		} catch (SQLException e) {
			System.out.println(e);
			throw new SQLException("Error retrieving test data for method: " + methodName, e);

		} finally {
			// Close ResultSet and PreparedStatement manually to avoid memory leaks
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return testData;
	}

	public static synchronized void closeConnection() throws SQLException {
		try {
            Connection conn = connection.get();
            System.out.println(connection.get());
            System.out.println("conn: " + conn);
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	connection.remove();
        }
	}
}