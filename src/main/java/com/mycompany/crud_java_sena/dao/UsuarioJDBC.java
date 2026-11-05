//package com.mycompany.crud_java_sena.dao;
//
//import com.mycompany.crud_java_sena.config.ConexionJDBC;
//import com.mycompany.crud_java_sena.model.Usuario;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UsuarioJDBC {
//
//    // Consultar un usuario mediante su correo
//    public Usuario buscarPorCorreo(String correo) {
//
//        String sql = "SELECT * FROM Usuario WHERE correo = ?";
//
//        try (Connection conexion = ConexionJDBC.conectar();
//             PreparedStatement consulta = conexion.prepareStatement(sql)) {
//
//            consulta.setString(1, correo);
//
//            try (ResultSet resultado = consulta.executeQuery()) {
//
//                if (resultado.next()) {
//
//                    Usuario usuario = new Usuario();
//
//                    usuario.setIdUsuario(resultado.getInt("idUsuario"));
//                    usuario.setNombre(resultado.getString("nombre"));
//                    usuario.setApellido(resultado.getString("apellido"));
//                    usuario.setCorreo(resultado.getString("correo"));
//                    usuario.setContrasena(resultado.getString("contrasena"));
//                    usuario.setTelefono(resultado.getString("telefono"));
//                    usuario.setDireccion(resultado.getString("direccion"));
//                    usuario.setEstadoUsuario(resultado.getString("estadoUsuario"));
//
//                    return usuario;
//                }
//            }
//
//        } catch (SQLException e) {
//            System.err.println("Error al buscar usuario: " + e.getMessage());
//        }
//
//        return null;
//    }
//
//    // Consultar todos los usuarios
//    public List<Usuario> listarUsuarios() {
//
//        List<Usuario> usuarios = new ArrayList<>();
//
//        String sql = "SELECT * FROM Usuario";
//
//        try (Connection conexion = ConexionJDBC.conectar();
//             PreparedStatement consulta = conexion.prepareStatement(sql);
//             ResultSet resultado = consulta.executeQuery()) {
//
//            while (resultado.next()) {
//
//                Usuario usuario = new Usuario();
//
//                usuario.setIdUsuario(resultado.getInt("idUsuario"));
//                usuario.setNombre(resultado.getString("nombre"));
//                usuario.setApellido(resultado.getString("apellido"));
//                usuario.setCorreo(resultado.getString("correo"));
//                usuario.setContrasena(resultado.getString("contrasena"));
//                usuario.setTelefono(resultado.getString("telefono"));
//                usuario.setDireccion(resultado.getString("direccion"));
//                usuario.setEstadoUsuario(resultado.getString("estadoUsuario"));
//
//                usuarios.add(usuario);
//            }
//
//        } catch (SQLException e) {
//            System.err.println("Error al listar usuarios: " + e.getMessage());
//        }
//
//        return usuarios;
//    }
//
//    // Eliminar un usuario mediante su identificador
//    public boolean eliminarUsuario(int idUsuario) {
//
//        String sql = "DELETE FROM Usuario WHERE idUsuario = ?";
//
//        try (Connection conexion = ConexionJDBC.conectar();
//             PreparedStatement consulta = conexion.prepareStatement(sql)) {
//
//            consulta.setInt(1, idUsuario);
//
//            return consulta.executeUpdate() > 0;
//
//        } catch (SQLException e) {
//            System.err.println("Error al eliminar usuario: " + e.getMessage());
//            return false;
//        }
//    }
//}






package com.mycompany.crud_java_sena.dao;

import com.mycompany.crud_java_sena.config.ConexionJDBC;
import com.mycompany.crud_java_sena.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioJDBC {

    // Consultar un usuario mediante su correo
    public Usuario buscarPorCorreo(String correo) {

        String sql = "SELECT * FROM Usuario WHERE correo = ?";

        try (Connection conexion = ConexionJDBC.conectar()) {

            if (conexion == null) {
                return null;
            }

            try (PreparedStatement consulta = conexion.prepareStatement(sql)) {

                consulta.setString(1, correo);

                try (ResultSet resultado = consulta.executeQuery()) {

                    if (resultado.next()) {

                        Usuario usuario = new Usuario();

                        usuario.setIdUsuario(resultado.getInt("idUsuario"));
                        usuario.setNombre(resultado.getString("nombre"));
                        usuario.setApellido(resultado.getString("apellido"));
                        usuario.setCorreo(resultado.getString("correo"));
                        usuario.setContrasena(resultado.getString("contrasena"));
                        usuario.setTelefono(resultado.getString("telefono"));
                        usuario.setDireccion(resultado.getString("direccion"));
                        usuario.setEstadoUsuario(resultado.getString("estadoUsuario"));

                        return usuario;
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar usuario: " + e.getMessage());
        }

        return null;
    }

    // Consultar todos los usuarios
    public List<Usuario> listarUsuarios() {

        List<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT * FROM Usuario";

        try (Connection conexion = ConexionJDBC.conectar()) {

            if (conexion == null) {
                return usuarios;
            }

            try (PreparedStatement consulta = conexion.prepareStatement(sql);
                 ResultSet resultado = consulta.executeQuery()) {

                while (resultado.next()) {

                    Usuario usuario = new Usuario();

                    usuario.setIdUsuario(resultado.getInt("idUsuario"));
                    usuario.setNombre(resultado.getString("nombre"));
                    usuario.setApellido(resultado.getString("apellido"));
                    usuario.setCorreo(resultado.getString("correo"));
                    usuario.setContrasena(resultado.getString("contrasena"));
                    usuario.setTelefono(resultado.getString("telefono"));
                    usuario.setDireccion(resultado.getString("direccion"));
                    usuario.setEstadoUsuario(resultado.getString("estadoUsuario"));

                    usuarios.add(usuario);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al listar usuarios: " + e.getMessage());
        }

        return usuarios;
    }

    // Eliminar un usuario mediante su identificador
    public boolean eliminarUsuario(int idUsuario) {

        String sql = "DELETE FROM Usuario WHERE idUsuario = ?";

        try (Connection conexion = ConexionJDBC.conectar()) {

            if (conexion == null) {
                return false;
            }

            try (PreparedStatement consulta = conexion.prepareStatement(sql)) {

                consulta.setInt(1, idUsuario);

                return consulta.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }

    // Actualizar los datos personales de un usuario
    public boolean actualizarUsuario(Usuario usuario) {

        String sql = "UPDATE Usuario SET "
                + "nombre = ?, "
                + "apellido = ?, "
                + "telefono = ?, "
                + "direccion = ? "
                + "WHERE idUsuario = ?";

        try (Connection conexion = ConexionJDBC.conectar()) {

            if (conexion == null) {
                return false;
            }

            try (PreparedStatement consulta = conexion.prepareStatement(sql)) {

                consulta.setString(1, usuario.getNombre());
                consulta.setString(2, usuario.getApellido());
                consulta.setString(3, usuario.getTelefono());
                consulta.setString(4, usuario.getDireccion());
                consulta.setInt(5, usuario.getIdUsuario());

                int filasActualizadas = consulta.executeUpdate();

                if (filasActualizadas > 0) {

                    System.out.println(
                            "Usuario actualizado correctamente. ID: "
                            + usuario.getIdUsuario()
                    );

                    return true;
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error al actualizar usuario: " + e.getMessage()
            );
        }

        return false;
    }
}

