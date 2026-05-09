package librarymanagementsystem;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBHelper {
	
	public static void createTable() {
		String users = "CREATE TABLE IF NOT EXISTS users("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "email TEXT NOT NULL UNIQUE,"
                + "password TEXT NOT NULL,"
                + "role TEXT NOT NULL"
                + ");";
		
		try (Connection conn = DBConnection.connect()) {
			 if (conn == null) {
	                System.out.println("Database connection failed!");
	                return;
	            }
			 
			 Statement stmt = conn.createStatement();

	         stmt.execute(users);
	         
	         System.out.println("Tables created successfully.");
			 
			
		}
		catch (Exception err) {
            err.printStackTrace();
        }
	}
	
	public static boolean checkLogin(String email, String password, String role) {

        String sql = "SELECT * FROM users WHERE email=? AND password=? AND role=?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.setString(3, role);

            ResultSet rs = pstmt.executeQuery();

            return rs.next();

        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
    }
}
