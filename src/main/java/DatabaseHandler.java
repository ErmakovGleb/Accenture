import java.sql.*;

public class DatabaseHandler {
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;

    public void createTable(String sql){
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            stmt = con.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try { if (con != null) con.close(); } catch (Exception e) {};
        }
    }

}
