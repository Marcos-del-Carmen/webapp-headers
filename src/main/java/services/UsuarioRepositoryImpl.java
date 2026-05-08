package services;

import models.Usuarios;
import repositories.UsuarioRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    private Connection conn;

    public UsuarioRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Usuarios> listar() throws SQLException {
        return null;
    }

    @Override
    public Usuarios porId(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<Usuarios> porNombre(String nombre) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Usuarios usuarios) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

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
