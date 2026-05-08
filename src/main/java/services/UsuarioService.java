package services;

import models.Usuarios;

import java.util.Optional;

public interface UsuarioService {
    Optional<Usuarios> login(String username, String password);
}
