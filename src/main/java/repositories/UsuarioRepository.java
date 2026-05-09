package repositories;

import models.Usuarios;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioRepository extends Repository<Usuarios> {
    Usuarios porUsername(String username) throws SQLException;
    List<Usuarios> listar() throws SQLException;
    Usuarios porId(Long id) throws SQLException;
    void guardar(Usuarios u) throws SQLException;
    void eliminar(Long id) throws SQLException;
}
