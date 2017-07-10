package mysqltest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Connect {
	static final String MYSQLURL = "jdbc:mysql://localhost/andy";
	static final String USER = "andy";
	static final String PASS = "bobobo";
	static Properties pro;

	public static void main(String[] args) {
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    Statement stmt = null;
	    ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			pro = new Properties();
			pro.setProperty("user", USER);
			pro.setProperty("password", PASS);
			pro.setProperty("useSSL", "false");
			
			conn = DriverManager.getConnection(MYSQLURL, pro);
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			String sql = "truncate table test_1";
			stmt.executeUpdate(sql);
			
			sql = "insert into test_1 values(?, ?)";
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			pstmt.setInt(1, 3);
			pstmt.setString(2, "C");
			
			int rows = pstmt.executeUpdate();
			
			sql = "select * from test_1";
			
			rs = stmt.executeQuery(sql);
			rs.last();
			System.out.println(rs.getInt("id"));
			System.out.println(rs.getString("col1"));
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
				try {
					if (rs != null) {
						rs.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}
					if (stmt != null) {
						stmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
}
