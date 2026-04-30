package services;

import models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Producto> listar();
    Optional<Producto> buscar(String nombre);
    Optional<Producto> porId(Long id);
}
