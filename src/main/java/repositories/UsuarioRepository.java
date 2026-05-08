package repositories;

import models.Usuarios;

import java.sql.SQLException;

public interface UsuarioRepository extends Repository<Usuarios> {
    Usuarios porUsername(String username) throws SQLException;
}
