
package com.mycompany.crud_java_sena.config;

import com.mycompany.crud_java_sena.model.Rol;
import com.mycompany.crud_java_sena.model.Usuario;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY =
            buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        try {

            System.out.println("Inicializando Hibernate...");

            SessionFactory factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Usuario.class)
                    .addAnnotatedClass(Rol.class)
                    .buildSessionFactory();

            System.out.println("Hibernate inicializado correctamente.");

            return factory;

        } catch (Throwable ex) {

            System.err.println("ERROR AL INICIALIZAR HIBERNATE:");
            ex.printStackTrace();

            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void cerrarSessionFactory() {

        if (SESSION_FACTORY != null && !SESSION_FACTORY.isClosed()) {
            SESSION_FACTORY.close();
        }
    }
}
