package com.mycompany.crud_java_sena.service;

import com.mycompany.crud_java_sena.dao.UsuarioHibernate;
import com.mycompany.crud_java_sena.dao.UsuarioJDBC;
import com.mycompany.crud_java_sena.model.Usuario;

import org.mindrot.jbcrypt.BCrypt;

public class AutenticacionService {

    private final UsuarioJDBC usuarioJDBC = new UsuarioJDBC();
    private final UsuarioHibernate usuarioHibernate = new UsuarioHibernate();

    // ==========================================
    // REGISTRO DE USUARIOS
    // ==========================================

    public String registrarUsuario(Usuario usuario) {

        if (usuario == null) {
            return "No se recibieron los datos del usuario.";
        }

        if (usuario.getNombre() == null || usuario.getNombre().isBlank()
                || usuario.getApellido() == null || usuario.getApellido().isBlank()
                || usuario.getCorreo() == null || usuario.getCorreo().isBlank()
                || usuario.getContrasena() == null || usuario.getContrasena().isBlank()
                || usuario.getTelefono() == null || usuario.getTelefono().isBlank()
                || usuario.getDireccion() == null || usuario.getDireccion().isBlank()) {

            return "Todos los campos son obligatorios.";
        }

        usuario.setCorreo(usuario.getCorreo().trim().toLowerCase());

        Usuario usuarioExistente = usuarioJDBC.buscarPorCorreo(
                usuario.getCorreo()
        );

        if (usuarioExistente != null) {
            return "El correo electrónico ya se encuentra registrado.";
        }

        try {

            String contrasenaEncriptada = BCrypt.hashpw(
                    usuario.getContrasena(),
                    BCrypt.gensalt()
            );

            usuario.setContrasena(contrasenaEncriptada);

            boolean registrado = usuarioHibernate.registrarUsuario(usuario);

            if (registrado) {
                return "Usuario registrado correctamente.";
            }

            return "No fue posible registrar el usuario.";

        } catch (Exception e) {

            System.err.println(
                    "Error en el servicio de registro: " + e.getMessage()
            );

            return "Ocurrió un error durante el registro.";
        }
    }

    // ==========================================
    // INICIO DE SESION
    // ==========================================

    public Usuario iniciarSesion(String correo, String contrasena) {

        if (correo == null || correo.isBlank()
                || contrasena == null || contrasena.isBlank()) {

            return null;
        }

        correo = correo.trim().toLowerCase();

        try {

            Usuario usuario = usuarioJDBC.buscarPorCorreo(correo);

            if (usuario == null) {
                return null;
            }

            if (usuario.getEstadoUsuario() == null
                    || !usuario.getEstadoUsuario().equalsIgnoreCase("Activo")) {

                return null;
            }

            boolean contrasenaValida = BCrypt.checkpw(
                    contrasena,
                    usuario.getContrasena()
            );

            if (contrasenaValida) {

                System.out.println("======================================");
                System.out.println(" INICIO DE SESION EXITOSO");
                System.out.println(" Usuario: " + usuario.getNombre()
                        + " " + usuario.getApellido());
                System.out.println(" Correo: " + usuario.getCorreo());
                System.out.println(" ID Usuario: " + usuario.getIdUsuario());
                System.out.println("======================================");

                return usuario;
            }

            return null;

        } catch (Exception e) {

            System.err.println(
                    "Error durante el inicio de sesión: " + e.getMessage()
            );

            return null;
        }
    }

    // ==========================================
    // ACTUALIZACION DEL PERFIL
    // ==========================================

    public String actualizarPerfil(
            Usuario usuario,
            String nombre,
            String apellido,
            String telefono,
            String direccion) {

        // Validar que exista un usuario autenticado
        if (usuario == null) {
            return "No se encontró el usuario que desea actualizar.";
        }

        // Validar que todos los campos sean obligatorios
        if (nombre == null || nombre.isBlank()
                || apellido == null || apellido.isBlank()
                || telefono == null || telefono.isBlank()
                || direccion == null || direccion.isBlank()) {

            return "Todos los campos del perfil son obligatorios.";
        }

        // Guardar los datos anteriores por si ocurre un error
        String nombreAnterior = usuario.getNombre();
        String apellidoAnterior = usuario.getApellido();
        String telefonoAnterior = usuario.getTelefono();
        String direccionAnterior = usuario.getDireccion();

        try {

            // Asignar los nuevos valores al objeto Usuario
            usuario.setNombre(nombre.trim());
            usuario.setApellido(apellido.trim());
            usuario.setTelefono(telefono.trim());
            usuario.setDireccion(direccion.trim());

            // Actualizar los datos mediante JDBC
            boolean actualizado = usuarioJDBC.actualizarUsuario(usuario);

            if (actualizado) {

                System.out.println("======================================");
                System.out.println(" PERFIL ACTUALIZADO CORRECTAMENTE");
                System.out.println(" Usuario: " + usuario.getNombre()
                        + " " + usuario.getApellido());
                System.out.println(" ID Usuario: " + usuario.getIdUsuario());
                System.out.println("======================================");

                return "Los datos del perfil se actualizaron correctamente.";
            }

            // Si falla la actualización, recuperar los valores anteriores
            usuario.setNombre(nombreAnterior);
            usuario.setApellido(apellidoAnterior);
            usuario.setTelefono(telefonoAnterior);
            usuario.setDireccion(direccionAnterior);

            return "No fue posible actualizar los datos del perfil.";

        } catch (Exception e) {

            // Recuperar los valores anteriores si ocurre una excepción
            usuario.setNombre(nombreAnterior);
            usuario.setApellido(apellidoAnterior);
            usuario.setTelefono(telefonoAnterior);
            usuario.setDireccion(direccionAnterior);

            System.err.println(
                    "Error al actualizar el perfil: " + e.getMessage()
            );

            return "Ocurrió un error al intentar actualizar el perfil.";
        }
    }
    
    
    // ==========================================
    // ELIMINACION DE CUENTA
    // ==========================================

    public boolean eliminarCuenta(Usuario usuario) {

        if (usuario == null || usuario.getIdUsuario() <= 0) {
            return false;
        }

        try {

            boolean eliminado = usuarioJDBC.eliminarUsuario(
                    usuario.getIdUsuario()
            );

            if (eliminado) {

                System.out.println("======================================");
                System.out.println(" CUENTA ELIMINADA CORRECTAMENTE");
                System.out.println(" ID Usuario: " + usuario.getIdUsuario());
                System.out.println("======================================");

                return true;
            }

            return false;

        } catch (Exception e) {

            System.err.println(
                    "Error al eliminar la cuenta: " + e.getMessage()
            );

            return false;
        }
    }
}


