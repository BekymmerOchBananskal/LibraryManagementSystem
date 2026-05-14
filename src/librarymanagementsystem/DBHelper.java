package librarymanagementsystem;

import java.sql.Connection;
import java.sql.Statement;
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
}