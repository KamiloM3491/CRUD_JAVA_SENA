//package com.mycompany.crud_java_sena.config;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class ConexionJDBC {
//
//    private static final String URL = "jdbc:mysql://localhost:3306/evidencia_java";
//    private static final String USUARIO = "root";
//    private static final String CONTRASENA = "";
//
//    public static Connection conectar() {
//
//        Connection conexion = null;
//
//        try {
//            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
//            System.out.println("Conexion exitosa con MySQL.");
//
//        } catch (SQLException e) {
//            System.out.println("Error al conectar con MySQL: " + e.getMessage());
//        }
//
//        return conexion;
//    }
//}





package com.mycompany.crud_java_sena.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionJDBC {

    // Datos de conexión con la base de datos MySQL
    private static final String URL =
            "jdbc:mysql://localhost:3306/evidencia_java";

    private static final String USUARIO = "root";
    private static final String CONTRASENA = "";

    // Método para establecer la conexión
    public static Connection conectar() {

        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(
                    URL,
                    USUARIO,
                    CONTRASENA
            );

            System.out.println("Conexion exitosa con MySQL.");

        } catch (SQLException e) {

            System.out.println(
                    "Error al conectar con MySQL: " + e.getMessage()
            );
        }

        return conexion;
    }
}


