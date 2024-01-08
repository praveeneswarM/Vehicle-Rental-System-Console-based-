// package com.rent;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.SQLException;

// public class db {
//     public Connection getConnection() throws SQLException {
//         Connection con = null;
//         try {
//             Class.forName("com.mysql.cj.jdbc.Driver");
//             final String url = "jdbc:mysql://localhost:3306/vehicle";
//             final String user = "root";
//             final String password = "";
//             con = DriverManager.getConnection(url, user, password);
//             return con;
//         } catch (ClassNotFoundException | SQLException e) {
//             e.printStackTrace();
//             throw new SQLException("DB Connection failed.");
//         }
//     }

//     public void closeConnection(Connection con) {
//         try {
//             if (con != null) {
//                 con.close();
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }
//     public static void main(String[] args) throws Exception {
//     }
// }

package com.rent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    public static Connection getConnection() throws SQLException {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            final String url = "jdbc:mysql://localhost:3306/vehicle";
            final String user = "root";
            final String password = "";
            con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("DB Connection failed.");
        }
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
