package ra.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_sql";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    // Khoi tao doi tuong connection
    public static Connection openConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
        return conn;
    }
    public static void closeConnection (Connection conn, CallableStatement callSt) throws SQLException {
        if (callSt != null) {
            callSt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}
