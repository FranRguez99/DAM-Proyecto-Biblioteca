package safa.ad_biblioteca.model;

import java.sql.*;

public class Conexion {

    public Connection conexion;

    public Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/biblioteca", "root",
                    "root");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
