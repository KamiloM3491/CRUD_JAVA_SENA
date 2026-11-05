
package com.mycompany.crud_java_sena;

import com.mycompany.crud_java_sena.config.ConexionJDBC;
import com.mycompany.crud_java_sena.config.HibernateUtil;
import com.mycompany.crud_java_sena.view.Login;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;

import javax.swing.SwingUtilities;

public class CRUD_JAVA_SENA {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println(" INICIANDO APLICACION CRUD JAVA SENA");
        System.out.println("======================================");

        // 1. Comprobar conexion mediante JDBC
        System.out.println("\nComprobando conexion JDBC...");

        try (Connection conexion = ConexionJDBC.conectar()) {

            if (conexion != null && !conexion.isClosed()) {
                System.out.println("JDBC: Conexion exitosa con MySQL.");
            } else {
                System.out.println("JDBC: No fue posible conectar con MySQL.");
            }

        } catch (SQLException e) {
            System.out.println("JDBC: Error de conexion: " + e.getMessage());
        }

        // 2. Comprobar conexion mediante Hibernate
        System.out.println("\nComprobando conexion Hibernate...");

        try (Session sesion = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            sesion.doWork(conexion -> {
                if (conexion.isValid(5)) {
                    System.out.println("Hibernate: Conexion exitosa con MySQL.");
                } else {
                    System.out.println("Hibernate: Conexion no valida.");
                }
            });

        } catch (Throwable e) {
            System.out.println("Hibernate: Error de conexion.");
            System.out.println("Detalle: " + e.getMessage());
        }

        // 3. Abrir la interfaz de Login
        System.out.println("\nIniciando interfaz de Login...");

        SwingUtilities.invokeLater(() -> {

            Login formulario = new Login();

            formulario.setLocationRelativeTo(null);
            formulario.setVisible(true);

            System.out.println("Formulario de Login abierto correctamente.");

        });
    }
}


