package edu.cibertec.capitulo7.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlConecta {
    public Connection connection() {
        Connection cn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/appweb", "root", "root123");

        } catch (Exception e) { e.printStackTrace(); }

        return cn;
    }

}
