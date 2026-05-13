package services;

import models.Curso;
import models.Producto;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> listar();
    List<Curso> buscarNombre(String nombre);
    Optional<Curso> porId(Long id);
    void guardar(Curso c);

    void eliminar(Long id);
}
