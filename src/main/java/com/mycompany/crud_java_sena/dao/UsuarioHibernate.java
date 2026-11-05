package com.mycompany.crud_java_sena.dao;

import com.mycompany.crud_java_sena.config.HibernateUtil;
import com.mycompany.crud_java_sena.model.Rol;
import com.mycompany.crud_java_sena.model.Usuario;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class UsuarioHibernate {

    public boolean registrarUsuario(Usuario usuario) {

        Transaction transaccion = null;

        try (Session sesion = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            transaccion = sesion.beginTransaction();

            // Asignar el rol Cliente al nuevo usuario
            Rol rolCliente = sesion.getReference(Rol.class, 2);

            usuario.setRol(rolCliente);

            // Establecer el estado inicial del usuario
            usuario.setEstadoUsuario("Activo");

            // Guardar el usuario en la base de datos
            sesion.persist(usuario);

            // Confirmar la transaccion
            transaccion.commit();

            System.out.println("Usuario registrado correctamente.");

            return true;

        } catch (Exception e) {

            if (transaccion != null && transaccion.isActive()) {
                transaccion.rollback();
            }

            System.err.println(
                    "Error al registrar el usuario: " + e.getMessage()
            );

            return false;
        }
    }
}
