/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tjvg4m34r13.library;
import java.sql.*;

/**
 * @author thomas.vaz
 */
public class Connection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://mysql.thomasjeferssonvaz.dev.br:3306"; //pointing to no database.
        String username = "library";
        String password = "$Jefersson3435";

        System.out.println("Connecting to server...");

        try (java.sql.Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Server connected!");
            Statement stmt = null;
            ResultSet resultset = null;

            try {
                stmt = connection.createStatement();
                resultset = stmt.executeQuery("SHOW DATABASES;");

                if (stmt.execute("SHOW DATABASES;")) {
                    resultset = stmt.getResultSet();
                }

                while (resultset.next()) {
                    System.out.println(resultset.getString("Database"));
                }
            } catch (SQLException ex) {
                // handle any errors
                ex.printStackTrace();
            } finally {
                // release resources
                if (resultset != null) {
                    try {
                        resultset.close();
                    } catch (SQLException sqlEx) {
                    }
                    resultset = null;
                }

                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException sqlEx) {
                    }
                    stmt = null;
                }

                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the server!", e);
        }
    }

}
