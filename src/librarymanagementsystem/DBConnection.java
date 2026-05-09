package librarymanagementsystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection connect() {
		Connection conn=null;
		
		try {
			String url="jdbc:sqlite:library_management.db";
			conn=DriverManager.getConnection(url);
		}
		catch(SQLException err) {
			err.printStackTrace();
			return null;
		}
		return conn;
	}
}
