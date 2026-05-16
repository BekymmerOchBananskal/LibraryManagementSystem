package librarymanagementsystem;

import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBHelper {

    public static void createTable() {

        String staff = "CREATE TABLE IF NOT EXISTS staff ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT NOT NULL,"
                + "surname TEXT NOT NULL,"
                + "email TEXT NOT NULL UNIQUE,"
                + "password TEXT NOT NULL"
                + ");";

        String members = "CREATE TABLE IF NOT EXISTS members ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "student_no TEXT NOT NULL UNIQUE,"
                + "name TEXT NOT NULL,"
                + "surname TEXT NOT NULL,"
                + "email TEXT UNIQUE,"
                + "password TEXT NOT NULL"
                + ");";

        String books = "CREATE TABLE IF NOT EXISTS books ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "title TEXT NOT NULL,"
                + "author TEXT NOT NULL,"
                + "category TEXT NOT NULL,"
                + "publisher TEXT,"
                + "page_count INTEGER,"
                + "status TEXT NOT NULL DEFAULT 'available'"
                + ");";

        String borrowings = "CREATE TABLE IF NOT EXISTS borrowings ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "book_id INTEGER NOT NULL,"
                + "member_id INTEGER NOT NULL,"
                + "borrow_date TEXT NOT NULL,"
                + "due_date TEXT NOT NULL,"
                + "return_date TEXT,"
                + "status TEXT NOT NULL DEFAULT 'borrowed',"
                + "FOREIGN KEY(book_id) REFERENCES books(id),"
                + "FOREIGN KEY(member_id) REFERENCES members(id)"
                + ");";

        try (Connection conn = DBConnection.connect()) {

            if (conn == null) {
                System.out.println("Database connection failed!");
                return;
            }

            Statement stmt = conn.createStatement();

            stmt.execute(staff);
            stmt.execute(members);
            stmt.execute(books);
            stmt.execute(borrowings);

            stmt.close();

            System.out.println("Tables created successfully.");

        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public static int checkLogin(String email, String password, String role) {

        String sql;

        if (role.equals("staff")) {
            sql = "SELECT id FROM staff WHERE email=? AND password=?";
        } else if (role.equals("member")) {
            sql = "SELECT id FROM members WHERE email=? AND password=?";
        } else {
            return -1;
        }

        try (Connection conn = DBConnection.connect()) {

            if (conn == null) {
                System.out.println("Database connection failed!");
                return -1;
            }

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");

                rs.close();
                pstmt.close();

                return id;
            }

            rs.close();
            pstmt.close();

        } catch (Exception err) {
            err.printStackTrace();
        }

        return -1;
    }
    public static void getAllMembers(DefaultTableModel model) {

        model.setRowCount(0);

        String sql = "SELECT * FROM members";

        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("student_no"),
                        rs.getString("email")
                };

                model.addRow(row);
            }

        } catch (Exception err) {
            err.printStackTrace();
        }
    }
    public static void insertStudent(String name,String surname,String studentno,String email,String password) {
		try (Connection conn = DBConnection.connect()) {

            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Database connection failed!");
                return;
            }
            
            String sql="INSERT INTO members(student_no,name,surname,email,password) VALUES(?,?,?,?,?)";
            PreparedStatement memberStmt = conn.prepareStatement(sql);
            
            memberStmt.setString(1, studentno);
            memberStmt.setString(2, name);
            memberStmt.setString(3, surname);
            memberStmt.setString(4, email);
            memberStmt.setString(5, password);
            
            memberStmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Member added successfully!");
            
		}
		catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Member could not be added!");
        }
    }
    public static void updateMember(
            int id,
            String name,
            String surname,
            String studentNo,
            String email,
            String password
    ) {

        String sql = "UPDATE members SET "
                + "name=?, "
                + "surname=?, "
                + "student_no=?, "
                + "email=?, "
                + "password=? "
                + "WHERE id=?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            pstmt.setString(3, studentNo);
            pstmt.setString(4, email);
            pstmt.setString(5, password);
            pstmt.setInt(6, id);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Member updated successfully!");

        } catch(Exception err){
            err.printStackTrace();
            JOptionPane.showMessageDialog(null, "Member could not be updated!");
        }
    }
    public static void deleteMember(int id) {

        String sql = "DELETE FROM members WHERE id=?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (Exception err) {
            err.printStackTrace();
        }
    }
    public static void getAllStaff(DefaultTableModel model) {

        model.setRowCount(0);

        String sql = "SELECT * FROM staff";

        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

            	Object[] row = {
            	        rs.getInt("id"),
            	        rs.getString("name"),
            	        rs.getString("surname"),
            	        rs.getString("email"),
            	        rs.getString("password")
            	};
                model.addRow(row);
                

                
            }

        } catch (Exception err) {
            err.printStackTrace();
            
        }
    }
    public static void insertStaff(String name,String surname,String email,String password) {
		try (Connection conn = DBConnection.connect()) {

            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Database connection failed!");
                return;
            }
            
            String sql="INSERT INTO staff(name,surname,email,password) VALUES(?,?,?,?)";
            PreparedStatement memberStmt = conn.prepareStatement(sql);
            
            
            memberStmt.setString(1, name);
            memberStmt.setString(2, surname);
            memberStmt.setString(3, email);
            memberStmt.setString(4, password);
            
            memberStmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Staff added successfully!");
            
		}
		catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Staff could not be added!");
        }
    }
    public static void updateStaff(
            int id,
            String name,
            String surname,
            
            String email,
            String password
    ) {

        String sql = "UPDATE staff SET "
                + "name=?, "
                + "surname=?, "
                
                + "email=?, "
                + "password=? "
                + "WHERE id=?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.setInt(5, id);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Staff updated successfully!");

        } catch(Exception err){
            err.printStackTrace();
            JOptionPane.showMessageDialog(null, "Staff could not be updated!");
        }
    }
    public static void deleteStaff(int id) {

        String sql = "DELETE FROM staff WHERE id=?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (Exception err) {
            err.printStackTrace();
        }
    }
            
}