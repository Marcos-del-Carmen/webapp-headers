package services;

import models.Usuarios;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Optional<Usuarios> login(String username, String password);
    List<Usuarios> listar();
    Optional<Usuarios> porId(Long id);
    void guardar(Usuarios u);

    void eliminar(Long id);

}
