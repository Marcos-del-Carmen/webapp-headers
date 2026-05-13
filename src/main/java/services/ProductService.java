package services;

import models.Categoria;
import models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Producto> listar();
    Optional<Producto> buscar(String nombre);
    Optional<Producto> porId(Long id);
    void guardar(Producto p);

    void eliminar(Long id);

    List<Categoria> listaCategoria();

    Optional<Categoria> porIdCategoria(Long id);
}
