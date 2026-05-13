package repositories;

import models.Usuarios;
import repositories.UsuarioRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    private Connection conn;

    public UsuarioRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Usuarios> listar() throws SQLException {
        String sql = "SELECT * FROM usuarios";
        List<Usuarios> usuarios = new ArrayList<>();
        try (
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Usuarios u = getUsuarios(rs);
                usuarios.add(u);
            }
        }
        return usuarios;
    }

    @Override
    public Usuarios porId(Long id) throws SQLException {
        String slq = "SELECT * FROM usuarios WHERE id_usuario = ?";

        Usuarios usuario = null;
        try (PreparedStatement stmt = conn.prepareStatement(slq)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = getUsuarios(rs);
                }
            }
        }

        return usuario;
    }

    @Override
    public List<Usuarios> porNombre(String nombre) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Usuarios usuarios) throws SQLException {
        String sql = "";

        if (usuarios.getIdUsuario() != null && usuarios.getIdUsuario() > 0) {
            sql = "UPDATE usuarios SET username = ?, password = ?, email = ? WHERE id_usuario = ?";
        } else {
            sql = "INSERT INTO usuarios (username, password, email) VALUES (?,?,?)";
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, usuarios.getUsername());
            stmt.setString(2, usuarios.getPassword());
            stmt.setString(3, usuarios.getEmail());

            if (usuarios.getIdUsuario() != null && usuarios.getIdUsuario() > 0) {
                stmt.setLong(4, usuarios.getIdUsuario());
            }

            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id_usuario=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Usuarios porUsername(String username) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE username=?";
        Usuarios user = null;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = getUsuarios(rs);
                }
            }
        }
        return user;
    }

    private static Usuarios getUsuarios(ResultSet rs) throws SQLException {
        Usuarios user;
        user = new Usuarios();
        user.setIdUsuario(rs.getLong("id_usuario"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        return user;
    }
}
